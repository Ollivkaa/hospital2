package database;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospitalDAO {

    public void insertStaff(MedicalStaff staff) {
        String sql = """
            INSERT INTO medical_staff (name, department, experience, role, spec_or_patients)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, staff.getName());
            ps.setString(2, staff.getDepartment());
            ps.setInt(3, staff.getExperienceYears());
            ps.setString(4, staff.getRole());

            if (staff instanceof Doctor d) {
                ps.setString(5, d.getSpecialization());
            } else if (staff instanceof Nurse n) {
                ps.setString(5, String.valueOf(n.getPatientsAssigned()));
            } else {
                ps.setString(5, null);
            }

            ps.executeUpdate();

            // get generated id
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int newId = rs.getInt(1);
                    staff.setStaffId(newId);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("DB insert error: " + e.getMessage(), e);
        }
    }

    public List<MedicalStaff> getAllStaff() {
        String sql = "SELECT * FROM medical_staff ORDER BY id";
        List<MedicalStaff> list = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("DB read error: " + e.getMessage(), e);
        }

        return list;
    }

    public MedicalStaff getStaffById(int id) {
        String sql = "SELECT * FROM medical_staff WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("DB getById error: " + e.getMessage(), e);
        }
        return null;
    }

    public void updateStaff(int id, String newName, String newDepartment) {
        String sql = "UPDATE medical_staff SET name = ?, department = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newName);
            ps.setString(2, newDepartment);
            ps.setInt(3, id);

            int updated = ps.executeUpdate();
            if (updated == 0) {
                System.out.println("Ничего не обновилось (ID не найден).");
            }

        } catch (SQLException e) {
            throw new RuntimeException("DB update error: " + e.getMessage(), e);
        }
    }

    public void deleteStaff(int id) {
        String sql = "DELETE FROM medical_staff WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int deleted = ps.executeUpdate();
            if (deleted == 0) {
                System.out.println("Ничего не удалилось (ID не найден).");
            }

        } catch (SQLException e) {
            throw new RuntimeException("DB delete error: " + e.getMessage(), e);
        }
    }

    public List<MedicalStaff> searchByName(String part) {
        String sql = "SELECT * FROM medical_staff WHERE LOWER(name) LIKE ? ORDER BY id";
        List<MedicalStaff> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + part.toLowerCase() + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("DB search error: " + e.getMessage(), e);
        }

        return list;
    }

    private MedicalStaff mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String dept = rs.getString("department");
        int exp = rs.getInt("experience");
        String role = rs.getString("role");
        String extra = rs.getString("spec_or_patients");

        if ("Doctor".equalsIgnoreCase(role)) {
            return new Doctor(id, name, dept, exp, extra == null ? "" : extra);
        }
        if ("Nurse".equalsIgnoreCase(role)) {
            int patients = 0;
            if (extra != null && !extra.isBlank()) {
                try { patients = Integer.parseInt(extra); } catch (NumberFormatException ignored) {}
            }
            return new Nurse(id, name, dept, exp, patients);
        }

        // fallback if role unknown
        return new Doctor(id, name, dept, exp, extra == null ? "" : extra);
    }
}

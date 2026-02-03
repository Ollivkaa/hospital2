package model;

public class Doctor extends MedicalStaff {
    private String specialization;

    public Doctor(int staffId, String name, String department, int experienceYears, String specialization) {
        super(staffId, name, department, experienceYears);
        setSpecialization(specialization);
    }

    public String getSpecialization() { return specialization; }

    public void setSpecialization(String specialization) {
        if (specialization == null || specialization.trim().isEmpty())
            throw new IllegalArgumentException("Specialization cannot be empty");
        this.specialization = specialization;
    }

    @Override
    public void work() {
        System.out.println(name + " is diagnosing patients...");
    }

    @Override
    public String getRole() {
        return "Doctor";
    }

    @Override
    public String toString() {
        return super.toString() + ", Specialization: " + specialization;
    }
}
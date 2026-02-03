import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5433/hospitaldb";
        String user = "postgres";
        String password = "1234"; // ← пароль

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("✅ CONNECTED SUCCESSFULLY!");
        } catch (Exception e) {
            System.out.println("❌ CONNECTION FAILED");
            e.printStackTrace();
        }
    }
}
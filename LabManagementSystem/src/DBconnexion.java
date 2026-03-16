import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBconnexion {
    static String Name = "root";
    static String password = "";  // Ensure this is correct, or set a password if necessary
    static String Con_string = "jdbc:mysql://localhost:3306/labo";  // Add port number
    static Connection con = null;

    // Method to connect to the database
    public static Connection ConnectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Ensure the correct driver is loaded
            con = DriverManager.getConnection(Con_string, Name, password);
            return con;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    // Method to check user credentials
    public static boolean checkUserCredentials(String user, String pass) {
        try {
            Connection conn = ConnectDB();
            String query = "SELECT * FROM user WHERE username = ? AND password = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, user);
            pst.setString(2, pass);
            ResultSet rs = pst.executeQuery();
            return rs.next();  // Returns true if a matching record is found
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    // For testing the connection in your main code:
    public static void testConnection() {
        Connection conn = ConnectDB();
        if (conn != null) {
            System.out.println("Connected to the database!");
        } else {
            System.out.println("Connection failed.");
        }
    }
}

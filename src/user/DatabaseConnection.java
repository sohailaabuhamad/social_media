package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Update "social_media_app" to whatever you named your database
    private static final String URL = "jdbc:mysql://localhost:3306/social_media";

    // Default XAMPP/WAMP credentials. Update if you set a specific MySQL password.
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
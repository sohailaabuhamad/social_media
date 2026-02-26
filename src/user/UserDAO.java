package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    // Method to check if the email and password match a user in the database
    public User authenticateUser(String email, String password) {
        // Only search by email
        String sql = "SELECT * FROM Users WHERE email = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password_hash");

                // Check if they match.
                // If you are using hashing, use: PasswordUtils.checkPassword(password, storedPassword)
                // If you are NOT hashing yet, use: password.equals(storedPassword)
                if (password.equals(storedPassword)) {
                    return new User(
                            rs.getInt("user_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            storedPassword
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean emailExists(String email) {
        String sql = "SELECT email FROM Users WHERE email = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            // If rs.next() is true, it means it found a matching email!
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // Method to insert a new user into the database
    public boolean registerUser(User user) {
        // The SQL query using ? as placeholders to prevent SQL Injection
        String sql = "INSERT INTO Users (name, email, password_hash) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Fill in the placeholders with the user's actual data
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPasswordHash());

            // Execute the query
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Returns true if the user was successfully added

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
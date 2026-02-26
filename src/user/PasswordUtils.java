package user;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {
    // Hashes a plain text password securely
    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    // Checks if an entered password matches the hashed password in the DB
    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
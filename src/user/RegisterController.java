package user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class RegisterController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    public void handleRegister(ActionEvent event) {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        // 1. Basic empty check
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            messageLabel.setText("❌ Please fill in all fields!");
            messageLabel.setTextFill(Color.web("#ff6b81")); // Soft red for dark theme
            return;
        }

        // 2. Email Format Check (Must have an @ and a .)
        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!email.matches(emailPattern)) {
            messageLabel.setText("❌ Invalid email format (e.g., user@mail.com)");
            messageLabel.setTextFill(Color.web("#ff6b81"));
            return;
        }

        // 3. Strong Password Check (8+ chars, 1 uppercase, 1 number)
        String passwordPattern = "^(?=.*[0-9])(?=.*[A-Z]).{8,}$";
        if (!password.matches(passwordPattern)) {
            messageLabel.setText("❌ Password needs 8+ chars, 1 uppercase, 1 number.");
            messageLabel.setTextFill(Color.web("#ff6b81"));
            return;
        }

        UserDAO userDAO = new UserDAO();

        // 4. Email Exists Check (Talks to the database)
        if (userDAO.emailExists(email)) {
            messageLabel.setText("❌ This email is already registered!");
            messageLabel.setTextFill(Color.web("#ff6b81"));
            return;
        }

        // 5. If it passes ALL checks, create the user!
        User newUser = new User(name, email, password);
        boolean isRegistered = userDAO.registerUser(newUser);

        if (isRegistered) {
            messageLabel.setText("🎉 Registration Successful!");
            messageLabel.setTextFill(Color.web("#2ed573")); // Soft green for dark theme
            nameField.clear();
            emailField.clear();
            passwordField.clear();
        } else {
            messageLabel.setText("❌ Database error. Could not register.");
            messageLabel.setTextFill(Color.web("#ff6b81"));
        }
    }
    @FXML
    public void goToLanding(ActionEvent event) throws java.io.IOException {
        javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/views/Landing.fxml"));
        javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new javafx.scene.Scene(root, 600, 400));
        stage.show();
    }
}
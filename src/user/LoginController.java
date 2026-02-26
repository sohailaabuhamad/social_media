package user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;


    @FXML
    public void handleLogin(ActionEvent event) throws IOException {
        String email = emailField.getText();
        String password = passwordField.getText();

        // 1. Basic empty check
        if (email.isEmpty() || password.isEmpty()) {
            messageLabel.setText("❌ Please fill in all fields!");
            messageLabel.setTextFill(Color.web("#ff6b81"));
            return;
        }

        // 2. Ask the database if this user exists
        UserDAO userDAO = new UserDAO();
        User loggedInUser = userDAO.authenticateUser(email, password);

        // 3. Check the result
        if (loggedInUser != null) {
            // 1. Load the MainFeed.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainFeed.fxml"));
            Parent root = loader.load();

            // 2. Pass the user data to the new controller
            MainFeedController feedController = loader.getController();
            feedController.setUser(loggedInUser);

            // 3. Switch the stage
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600)); // Larger size for the main feed
            stage.show();
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
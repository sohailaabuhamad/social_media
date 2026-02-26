package user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class LandingController {

    @FXML
    public void goToLogin(ActionEvent event) throws IOException {
        switchScene(event, "/views/Login.fxml");
    }

    @FXML
    public void goToRegister(ActionEvent event) throws IOException {
        switchScene(event, "/views/Register.fxml");
    }

    // Helper method to reduce repeated code
    private void switchScene(ActionEvent event, String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}
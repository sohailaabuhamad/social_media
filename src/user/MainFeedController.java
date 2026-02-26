package user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class MainFeedController {

    @FXML private Label welcomeLabel;
    @FXML private VBox postContainer;
    @FXML private TextField postInput;

    // We can use this to store the user who logged in
    private User currentUser;

    public void setUser(User user) {
        this.currentUser = user;
        welcomeLabel.setText("Hello, " + user.getName() + "!");
    }

    @FXML
    public void handlePost() {
        String content = postInput.getText();
        if (!content.isEmpty()) {
            Label newPost = new Label(currentUser.getName() + ": " + content);
            newPost.setStyle("-fx-text-fill: white; -fx-background-color: #485460; -fx-padding: 10; -fx-background-radius: 5;");
            newPost.setMaxWidth(Double.MAX_VALUE);
            postContainer.getChildren().add(0, newPost); // Adds post to the top
            postInput.clear();
        }
    }

    @FXML
    public void handleLogout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/Landing.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import user.DatabaseConnection;
import java.sql.Connection;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file you created in Scene Builder
        Parent root = FXMLLoader.load(getClass().getResource("/views/Landing.fxml"));

        // Set the window title and size
        primaryStage.setTitle("Social Media App - Registration");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        // First, check the database connection
        try {
            Connection conn = DatabaseConnection.getConnection();
            if (conn != null) {
                System.out.println("🎉 Successfully connected to the MySQL database!");
            }
        } catch (Exception e) {
            System.out.println("❌ Database connection failed.");
            e.printStackTrace();
        }

        // Then, launch the JavaFX GUI
        launch(args);
    }
}
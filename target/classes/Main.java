import controller.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainWindow.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        MainWindow mainWindow = loader.getController();
        mainWindow.setStage(primaryStage);
        mainWindow.setScene(scene);

        primaryStage.setResizable(true);

        primaryStage.setTitle("Crazy jet");
        primaryStage.setScene(scene);
        primaryStage.show();




    }

    public static void main(String[] args) {
        launch(args);
    }
}

import controller.GameWindow;
import controller.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
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
        mainWindow.MovimientoJugador(scene);

        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/fxml/gameWindow.fxml"));
        AnchorPane ancho = loader2.load();
        GameWindow gameWindow = loader2.getController();
        gameWindow.setScene(scene);

        primaryStage.setTitle("Crazy jet");
        primaryStage.setScene(scene);
        primaryStage.show();




    }

    public static void main(String[] args) {
        launch(args);
    }
}

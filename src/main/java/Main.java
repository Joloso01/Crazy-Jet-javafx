import controller.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Main extends Application {


    MediaPlayer mediaPlayer;

    @Override
    public void start(Stage primaryStage) throws Exception {

//        Media media = new Media("/src/main/java/fxml/song.mp3");
//        mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.setAutoPlay(true);
//        mediaPlayer.setVolume(0.1);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/mainWindow.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        MainWindow mainWindow = loader.getController();
        mainWindow.setStage(primaryStage);
        mainWindow.setScene(scene);
        scene.getStylesheets().add("css/temaMainMenu.css");
        System.out.println(primaryStage.getHeight() + " : "+primaryStage.getWidth());

        primaryStage.setResizable(true);

        primaryStage.setTitle("Crazy jet");
        primaryStage.setScene(scene);
        primaryStage.show();



    }

    public static void main(String[] args) {launch(args);}
}

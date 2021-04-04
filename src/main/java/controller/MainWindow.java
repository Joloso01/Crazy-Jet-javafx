package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class MainWindow implements Initializable {
    Stage stage;
    Scene scene;

    @FXML
    private Button play, score, salir;
    @FXML
    private AnchorPane pane1, pane2;
    @FXML
    private VBox vbox1;

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;

    }

    public void jugar() {
        vbox1.getChildren().remove(pane1);
        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/gameWindow.fxml"));
            AnchorPane ancho = loader.load();
            GameWindow gameWindow = loader.getController();
            gameWindow.setScene(scene);
            vbox1.getChildren().add(ancho);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showScore(ActionEvent actionEvent) {

    }

    public void exitGame(ActionEvent actionEvent) {
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void menuItemCloseAction(ActionEvent actionEvent) {
        stage.close();
    }

}

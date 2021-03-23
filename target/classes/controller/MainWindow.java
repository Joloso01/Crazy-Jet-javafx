package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class MainWindow {
    Stage stage;
    Scene scene;

    @FXML
    private Button play, score, salir;

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void jugar() throws IOException {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/gameWindow.fxml"));
            Parent root = loader.load();
            stage = new Stage();
            scene = new Scene(root);
            stage.setScene(scene);

            stage.show();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showScore(ActionEvent actionEvent) {

    }

    public void exitGame(ActionEvent actionEvent) {
        stage.close();
    }
}

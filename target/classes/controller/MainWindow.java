package controller;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindow {
    Stage stage;
    Scene scene;

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void jugar(ActionEvent actionEvent) {

    }

    public void showScore(ActionEvent actionEvent) {

    }

    public void exitGame(ActionEvent actionEvent) {
        stage.close();
    }
}

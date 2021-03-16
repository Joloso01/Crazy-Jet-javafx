package controller;

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
}

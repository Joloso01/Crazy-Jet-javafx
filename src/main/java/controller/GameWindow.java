package controller;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameWindow {

    private static final int height = 800;
    private static final int width = 500;
    private AnchorPane pane;
    private Stage stage;
    private Scene scene;

    public GameWindow(){
        pane = new AnchorPane();
        scene = new Scene(pane, height, width);
        stage = new Stage();
        stage.setScene(scene);
    }

}

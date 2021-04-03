package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GameWindow implements Initializable {

    private static final int height = 800;
    private static final int width = 500;
    private AnchorPane pane;
    private Stage stage;
    private Scene scene;
    private Enemigo enemigo;

    @FXML
    ImageView background;

    public GameWindow(){
        pane = new AnchorPane();
        scene = new Scene(pane, height, width);
        stage = new Stage();
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        enemigo = new Enemigo(new Image("/fxml/sprites.jets/JU-87B2/Type_1/JU87B2-progress_4.png"));
        background.setImage(new Image("/fxml/images/mar.gif"));
    }
}

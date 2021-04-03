package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
    private GraphicsContext gc;
    private Stage stage;
    private Scene scene;
    private Enemigo enemigo;
    private Jet jetPlayer;

    @FXML
    ImageView background;

    @FXML
    Canvas playerJet;

//    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.0017), new EventHandler<ActionEvent>(){
//        @Override
//        public void handle(ActionEvent event) {
//            jetPlayer.clear(gc);
//            jetPlayer.move();
//            if(jetPlayer.getBoundary().intersects(jetPlayer.getBoundary())) {
//                jetPlayer.setY(150);
//            }
//            jetPlayer.render(gc);
//
//        }
//    })
//    );

    public GameWindow(){
        pane = new AnchorPane();
        scene = new Scene(pane, height, width);
        stage = new Stage();
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        background.setImage(new Image("fxml/images/mar.gif"));
        jetPlayer = new Jet(new Image("fxml/sprites/jets/BF-109E/Type-3/Type2_2.png"));
        enemigo = new Enemigo(new Image("fxml/sprites/jets/JU-87B2/Type_1/JU87B2 -progress_4.png"));
        gc = playerJet.getGraphicsContext2D();
        jetPlayer.render(gc);

//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.play();
    }

    public void setScene(Scene sc) {
        scene = sc;
        scene.setOnKeyPressed(keyEvent -> {
            jetPlayer.clear(gc);
            jetPlayer.move(keyEvent.getCode().toString());
            jetPlayer.render(gc);

        });
    }
}

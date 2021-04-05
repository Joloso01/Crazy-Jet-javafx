package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.EnemyJet;
import model.Jet;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameWindow implements Initializable {

    private GraphicsContext gc;
    private Scene scene;
    private EnemyJet enemigo;
    private Jet jetPlayer;

    @FXML
    ImageView background;

    @FXML
    Canvas playerJet;

    @FXML
    AnchorPane anchor0;

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.0017), new EventHandler<>() {
        @Override
        public void handle(ActionEvent event) {
            enemigo.clear(gc);
            enemigo.move();
            if (jetPlayer.getBoundary().intersects(enemigo.getBoundary())) {
                enemigo.setY(0);
                if (jetPlayer.comprobarVida() !=0){
                    jetPlayer.golpeado();
                }else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/gameOverWindow.fxml"));
                    AnchorPane gameOverPane = null;
                    try {
                         gameOverPane = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    jetPlayer.clear(gc);
                    enemigo.clear(gc);
                    anchor0.getChildren().remove(background);
                    anchor0.getChildren().remove(playerJet);
                    anchor0.getChildren().add(gameOverPane);

                }

                enemigo.setY(150);
            }
            enemigo.render(gc);

        }
    })
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        background.setImage(new Image("fxml/images/mar.gif"));
        jetPlayer = new Jet(new Image("fxml/sprites/jets/playerJet_recto.png"));
        enemigo = new EnemyJet(new Image("fxml/sprites/jets/enemigo1.png"));
        gc = playerJet.getGraphicsContext2D();
        jetPlayer.render(gc);

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void setScene(Scene sc) {
        scene = sc;
        System.out.println("me muevo papi");
        scene.setOnKeyPressed(keyEvent -> {
            jetPlayer.clear(gc);
            jetPlayer.move(keyEvent.getCode().toString());
            jetPlayer.render(gc);

        });
    }

    public void comenzarPartida() {
        jetPlayer.empezarPartida();
    }
}

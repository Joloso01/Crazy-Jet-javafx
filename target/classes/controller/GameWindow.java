package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.EnemyJet;
import model.Jet;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameWindow implements Initializable {

    private GraphicsContext gc;
    private Scene scene;
    private ArrayList<EnemyJet> listaEnemigos = new ArrayList<>();
    private Jet jetPlayer;
    private Stage stage;
    private int temporizadorAumento=0;

    @FXML
    ImageView background;

    @FXML
    Canvas playerJet;

    @FXML
    AnchorPane anchor0;

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.0017), new EventHandler<>() {
        @Override
        public void handle(ActionEvent event) {
            for (EnemyJet enemigo : listaEnemigos){
                enemigo.clear(gc);
                enemigo.move();


                if (enemigo.getPosY() > stage.getHeight()){
                    listaEnemigos.remove(enemigo);
                    enemigo.clear(gc);
                    listaEnemigos.add(new EnemyJet());
                }

                if (jetPlayer.getBoundary().intersects(enemigo.getBoundary())) {
                    if (jetPlayer.comprobarVida() !=0){
                        jetPlayer.golpeado();
                    }else {
                        FXMLLoader loader2=new FXMLLoader(getClass().getResource("/fxml/mainWindow.fxml"));
                        try {
                            Parent parent = loader2.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        MainWindow mainWindow = loader2.getController();
                        mainWindow.pararMusica();


                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/gameOverWindow.fxml"));
                        AnchorPane gameOverPane = null;
                        GameOverWindow gameOverWindow = null;
                        try {
                            gameOverPane = loader.load();
                            gameOverWindow = loader.getController();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        gameOver(gameOverPane,gameOverWindow);
                    }

                    listaEnemigos.remove(enemigo);
                }

                enemigo.render(gc);
            }
            if (background.getLayoutY() > 1){
                background.setLayoutY(-background.getImage().getHeight()+640);
            }
            if (temporizadorAumento==5000){
                for (int i = 0; i < 10; i++) {
                    listaEnemigos.add(new EnemyJet());
                }
                temporizadorAumento=0;
            }else {
                temporizadorAumento++;
            }
            background.setLayoutY(background.getLayoutY()+0.1);
        }
    })
    );

    private void gameOver(AnchorPane gameOverPane, GameOverWindow gameOverWindow) {
        jetPlayer.clear(gc);
        listaEnemigos.clear();

        anchor0.getChildren().remove(0);
        anchor0.getChildren().add(gameOverPane);
        gameOverWindow.setStage(stage);
        gameOverWindow.cambiarDimensiones();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        background.setImage(new Image("fxml/images/mapa.png"));
        jetPlayer = new Jet(new Image("fxml/sprites/jets/playerJet_recto.png"));
        jetPlayer.setX(210.0);
        jetPlayer.setY(620.0);
        background.setLayoutY(-background.getImage().getHeight()+640);
        gc = playerJet.getGraphicsContext2D();
        for (int i = 0; i < 10; i++) {
            listaEnemigos.add(new EnemyJet());

        }
        jetPlayer.render(gc);

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void cambiarDimension(){
        stage.setWidth(450f);
        stage.setHeight(700f);
    }

    public void setScene(Scene sc) {
        scene = sc;

        scene.setOnKeyPressed(keyEvent -> {
            jetPlayer.clear(gc);
            jetPlayer.move(keyEvent.getCode().toString());
            jetPlayer.render(gc);

        });
    }

    public void comenzarPartida() {
        jetPlayer.empezarPartida();
    }

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }
}

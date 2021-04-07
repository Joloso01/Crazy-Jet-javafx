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
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Bala;
import model.EnemyJet;
import model.Jet;
import model.Jugador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class GameWindow implements Initializable {

    private GraphicsContext gc;
    private Scene scene;

    private Jugador jugador;
    private Estadisticas estadisticas = new Estadisticas();
    private ArrayList<EnemyJet> listaEnemigos = new ArrayList<>();
    private ArrayList<Bala> listaBalas = new ArrayList<>();
    private Jet jetPlayer;
    private Stage stage;
    private int puntosJugador;
    private int tiempoJugador;

    private String s;
    private Media sound;
    private MediaPlayer audioClip;

    private int temporizadorAumento=0;
    Font font;

    @FXML
    ImageView background;

    @FXML
    Canvas playerJet;

    @FXML
    AnchorPane anchor0;

    @FXML
    Label tiempoPartida,puntosPartida,vidasPartida;

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.0020), new EventHandler<>() {
        @Override
        public void handle(ActionEvent event) {

            if (jetPlayer.haDisparado){
                listaBalas.add(new Bala(jetPlayer.getPosX(),jetPlayer.getPosY()-50));
                jetPlayer.haDisparado=false;
            }

            for (Bala bala:listaBalas){
                bala.clear(gc);
                bala.update();

                for (EnemyJet enemyJet1 : listaEnemigos){
                    if (bala.getBoundary().intersects(enemyJet1.getBoundary())){
                        enemyJet1.clear(gc);
                        enemyJet1.setY(-700);
                        enemyJet1.render(gc);
                        bala.setY(-900);
                        listaBalas.remove(bala);
                        puntosJugador+=100;
                    }
                }

                bala.render(gc);
            }

            listaBalas.removeIf(bala -> (bala.getPosY() > stage.getHeight()));

            for (EnemyJet enemigo : listaEnemigos){
                enemigo.clear(gc);
                enemigo.move();
                if (enemigo.getPosY() > stage.getHeight()){
                    enemigo.setY(-stage.getHeight());
                }

                if (jetPlayer.getBoundary().intersects(enemigo.getBoundary())) {
                    if (jetPlayer.comprobarVida() !=1){
                        enemigo.setY(-700);
                        jetPlayer.golpeado();
                        vidasPartida.setText(String.valueOf(jetPlayer.comprobarVida()));
                    }else {
                        enemigo.setY(stage.getHeight()-600);

                        pararMusica();

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
                        timeline.stop();
                    }
                }

                enemigo.render(gc);
            }
            if (background.getLayoutY() > 1){
                background.setLayoutY(-background.getImage().getHeight()+640);
            }
            if (temporizadorAumento==3000){
                for (int i = 0; i < 10; i++) {
                    listaEnemigos.add(new EnemyJet());
                }
                temporizadorAumento=0;
            }else {
                temporizadorAumento++;
            }
            background.setLayoutY(background.getLayoutY()+0.1);
            puntosPartida.setText(String.valueOf(puntosJugador));
        }
    })
    );

    Timeline contador = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<>(){

        @Override
        public void handle(ActionEvent actionEvent) {
            tiempoJugador++;
            tiempoPartida.setText(tiempoJugador + "s");
        }
    }));


    private void gameOver(AnchorPane gameOverPane, GameOverWindow gameOverWindow) {
        jetPlayer.clear(gc);
        listaEnemigos.clear();

        anchor0.getChildren().remove(0);
        anchor0.getChildren().add(gameOverPane);
        gameOverWindow.setStage(stage);
        gameOverWindow.setPuntuacion(puntosJugador);
        gameOverWindow.cambiarDimensiones();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        s = getClass().getClassLoader().getResource("fxml/sounds/song.mp3").toExternalForm();
        sound = new Media(s);
        audioClip = new MediaPlayer(sound);
        audioClip.setVolume(0.1);
        audioClip.setCycleCount(MediaPlayer.INDEFINITE);
        audioClip.play();

        background.setImage(new Image("fxml/images/mapa.png"));
        jetPlayer = new Jet(new Image("fxml/sprites/jets/playerJet_recto.png"));
        jetPlayer.setX(210.0);
        jetPlayer.setY(610.0);
        background.setLayoutY(-background.getImage().getHeight()+640);
        gc = playerJet.getGraphicsContext2D();
        for (int i = 0; i < 10; i++) {
            listaEnemigos.add(new EnemyJet());

        }
        jetPlayer.render(gc);

        TextInputDialog dialog = new TextInputDialog("jugador1");
        dialog.setTitle("Nueva partida");
        dialog.setHeaderText("Introduzca su nombre");
        dialog.setContentText("nombre:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(s -> estadisticas.setPlayerName(s));
        jugador = new Jugador(result.get());

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        contador.setCycleCount(Timeline.INDEFINITE);
        contador.play();
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
            System.out.println(keyEvent.getCode().toString());

        });
    }

    public void pararMusica(){
        audioClip.stop();
        audioClip.pause();
        audioClip.setVolume(0.0);
    }

    public void ponerEstilo(){
        scene.getStylesheets().add("css/temaGameScreen.css");
    }

    public void comenzarPartida() {
        jetPlayer.empezarPartida();
    }

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }
}
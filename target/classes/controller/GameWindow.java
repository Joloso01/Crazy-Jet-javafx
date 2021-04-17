package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Bala;
import model.EnemyJet;
import model.Jet;
import model.Jugador;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class GameWindow implements Initializable {

    private GraphicsContext gc;
    private Scene scene;

    private Jugador jugador;
    private Estadisticas estadisticas;

    private final ArrayList<EnemyJet> listaEnemigos = new ArrayList<>();
    private final ArrayList<Bala> listaBalas = new ArrayList<>();
    private Optional<String> result;

    private Jet jetPlayer;
    private Stage stage;
    private int puntosJugador;
    private int tiempoJugador;

    private String s;
    private Media sound;
    private MediaPlayer audioClip;

    private int temporizadorAumento=0;
    private int tiempoDisparo=750;


    @FXML
    ImageView background;

    @FXML
    Canvas playerJet;

    @FXML
    AnchorPane anchor0;

    @FXML
    Label tiempoPartida,puntosPartida,vidasPartida;

    //Buecle principal del juago
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.0020), new EventHandler<>() {
        @Override
        public void handle(ActionEvent event) {
            //Con este condicional se mira si el jugador ha pulsado el espacio y si ha pasado el tiempo de recarga para instanciar una nueva bala
            if (jetPlayer.haDisparado && tiempoDisparo >= 750){
                listaBalas.add(new Bala(jetPlayer.getPosX(),jetPlayer.getPosY()-50));
                jetPlayer.haDisparado=false;
                tiempoDisparo = 0;
            }else jetPlayer.haDisparado=false;
            tiempoDisparo++;

            //con este bucle se actualiza la posicion de las balas en la pantalla
            for (Bala bala:listaBalas){
                bala.clear(gc);
                bala.update();

                //con este bucle se reposiciona a los enemigos que han colisionado con la bala disparada por el jugador
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

            //Si la bala ha sobrepasado el alto de la pantalla se borra
            listaBalas.removeIf(bala -> (bala.getPosY() > stage.getHeight()));

            //En este bucle se hace lo relacionado con los aviones enemigos
            for (EnemyJet enemigo : listaEnemigos){
                //primero se actualiza la posicion
                enemigo.clear(gc);
                enemigo.move();
                //y en caso de que el avion enemigo haya sobrepasado el alto de la pantalla se recolocan en la parte superior
                if (enemigo.getPosY() > stage.getHeight()){
                    enemigo.setY(-stage.getHeight());
                }
                
                //si el jugado colisiona con un avion enemigo este se recoloca en la posicion inical y se ejecuta el metodo golpeado para restarle una vida al jugador,
                //esto siempre que el jugador tenga mas de 1 vida
                //si el jugador tiene 1 vida se para la musica y se cambia a la ventana de Game Over
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
            //Cuando la imagen de fondo llega ha su fin se restablece en la posicion inical
            if (background.getLayoutY() > 1){
                background.setLayoutY(-background.getImage().getHeight()+640);
            }
            //aqui se aumenta el nivel de dificultad de la partida añadiendo mas aviones para esquivar y derribar
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
            tiempoDisparo++;

        }
    })
    );

    //Aqui se calcula el tiempo de la partida del jugador
    Timeline contador = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<>(){

        @Override
        public void handle(ActionEvent actionEvent) {
            tiempoJugador++;
            tiempoPartida.setText(tiempoJugador + "s");
        }
    }));




    //Este metodo se ejecuta cuando la partida llega a su fin
    //limpia los elementos de la pantalla, cambia su tamaño, añade los elementos del fxml de gameOver
    //y  pasa a la pantalla game over la informacion de la partida del jugador
    private void gameOver(AnchorPane gameOverPane, GameOverWindow gameOverWindow) {
        jetPlayer.clear(gc);
        listaEnemigos.clear();
        timeline.stop();
        contador.stop();

        anchor0.getChildren().remove(0);
        anchor0.getChildren().add(gameOverPane);

        anchor0.getChildren().remove(0);
        anchor0.getChildren().remove(0);
        anchor0.getParent().prefWidth(600f);
        anchor0.setMaxWidth(420);
        anchor0.setMaxHeight(600);
        stage.setWidth(600f);
        stage.setHeight(420f);
        stage.setMaxWidth(600f);
        stage.setMaxHeight(420f);

        System.out.println(anchor0.getChildren());

        gameOverWindow.setStage(stage);
        gameOverWindow.setScene(scene);
        gameOverWindow.setPuntuacion(puntosJugador);
        gameOverWindow.setTiempo(tiempoJugador);
        gameOverWindow.setEstadisticas(estadisticas);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Aqui se inicializa la musica de la pantalla de juego
        s = getClass().getClassLoader().getResource("fxml/sounds/song.mp3").toExternalForm();
        sound = new Media(s);
        audioClip = new MediaPlayer(sound);
        audioClip.setVolume(0.1);
        audioClip.setCycleCount(MediaPlayer.INDEFINITE);
        audioClip.play();

        //Se pone la imagen de fondo, el sprite del jugador y  se le coloca en la posicion inicial
        background.setImage(new Image("fxml/images/mapa.png"));
        jetPlayer = new Jet(new Image("fxml/sprites/jets/playerJet_recto.png"));
        jetPlayer.setX(210.0);
        jetPlayer.setY(610.0);
        background.setLayoutY(-background.getImage().getHeight()+665);

        gc = playerJet.getGraphicsContext2D();

        //se ponen unos enemigos para iniciar el juego
        for (int i = 0; i < 10; i++) {
            listaEnemigos.add(new EnemyJet());
        }
        jetPlayer.render(gc);

        //se inician el bucle del juego y el contador de tiempo de la partida del jugador
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        contador.setCycleCount(Timeline.INDEFINITE);
        contador.play();
    }

    //Metodo para cambiar las dimensiones de la pantalla
    public void cambiarDimension(){
            stage.setWidth(450f);
            stage.setHeight(700f);
            stage.setMaxWidth(450f);
            stage.setMaxHeight(700f);
            anchor0.setMaxWidth(450f);
            anchor0.setMaxHeight(711f);
    }

    //metodo para establecer la escena y añadir el listener para detectar las teclas
    public void setScene(Scene sc) {
        scene = sc;

        scene.setOnKeyPressed(keyEvent -> {
            jetPlayer.clear(gc);
            jetPlayer.move(keyEvent.getCode().toString());
            jetPlayer.render(gc);
        });
    }

    //Este metodo sirve para parar la musica cuando acaba la partida
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

    public void setEstadisticas(Estadisticas estadisticas) {
        this.estadisticas = estadisticas;
    }

    public Estadisticas getEstadisticas() {
        return estadisticas;
    }

}
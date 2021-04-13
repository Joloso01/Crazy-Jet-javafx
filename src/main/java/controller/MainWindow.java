package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Jugador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {
    Stage stage;
    Scene scene;
    String nombreDelJugador;
    private Estadisticas estadisticas = new Estadisticas();

    @FXML
    private AnchorPane pane1;

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public void setScene(Scene scene) { this.scene = scene; }

    public void jugar() {
        pane1.getChildren().clear();
        try{
            System.out.println("w: "+stage.getWidth()+ " h: "+stage.getHeight());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/gameWindow.fxml"));
            AnchorPane ancho = loader.load();
            GameWindow gameWindow = loader.getController();
            gameWindow.setScene(scene);
            gameWindow.setStage(stage);
            gameWindow.setEstadisticas(estadisticas);
            gameWindow.ponerEstilo();
            gameWindow.cambiarDimension();

            pane1.getChildren().add(ancho);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showScore(ActionEvent actionEvent) {
        TableView<Jugador> puntuacionesLista = null;

        try {
            puntuacionesLista = FXMLLoader.load(getClass().getResource("/fxml/estadisticas.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage2 = new Stage();
        stage2.setTitle("Puntuacion de los jugadores");
        stage2.setScene(new Scene(puntuacionesLista));
        stage2.show();

        puntuacionesLista.getItems().clear();
        puntuacionesLista.getColumns().clear();

        puntuacionesLista.getColumns().addAll(estadisticas.jugador,
                estadisticas.puntos,
                estadisticas.tiempo,
                estadisticas.fecha);

        puntuacionesLista.getItems().add(new Jugador("nombre"));
        puntuacionesLista.getItems().addAll(estadisticas.getPuntuacionesLista());

    }

    public void exitGame(ActionEvent actionEvent) {
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public Estadisticas getEstadisticas() {
        return estadisticas;
    }

    public void setEstadisticas(Estadisticas estadisticas) {
        this.estadisticas = estadisticas;
    }

    //    public void cambiarDimensiones(){
//        stage.setHeight(600f);
//        stage.setWidth(420f);
//    }


}

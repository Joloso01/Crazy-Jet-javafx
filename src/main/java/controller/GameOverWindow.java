package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Jugador;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class GameOverWindow implements Initializable {


    @FXML
    AnchorPane anchor0;

    @FXML
    Label puntuacionText;

    @FXML
    TextField usernameInput;

    private Stage st;
    private Scene scene;
    private int puntuacion;
    private int tiempo;
    private Estadisticas estadisticas= new Estadisticas();
    private Optional<String> result;

    public void volverAjugar(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/gameWindow.fxml"));
        AnchorPane ancho = null;
        try {
            ancho = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GameWindow gameWindow = loader.getController();
        gameWindow.setScene(scene);
        gameWindow.setStage(st);
        gameWindow.ponerEstilo();
        gameWindow.cambiarDimension();
        anchor0.getChildren().clear();
        anchor0.getChildren().add(ancho);
        String name = usernameInput.getText();
        estadisticas.statsJugador(name, tiempo, puntuacion);
        gameWindow.setEstadisticas(estadisticas);

    }

    public void volverMenu(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainWindow.fxml"));
        Parent root = null;
        try {
            root=loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainWindow mainWindow = loader.getController();
        anchor0.getChildren().clear();
        anchor0.getChildren().add(root);
        mainWindow.setScene(scene);
        mainWindow.setStage(st);
        String name = usernameInput.getText();
        estadisticas.statsJugador(name, tiempo, puntuacion);
        mainWindow.setEstadisticas(estadisticas);
    }

    public void setStage(Stage stage) {
        st = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setPuntuacion(int p){
        puntuacion=p;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public Estadisticas getEstadisticas() {
        return estadisticas;
    }

    public void setEstadisticas(Estadisticas estadisticas) {
        this.estadisticas = estadisticas;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        puntuacionText.setText(String.valueOf(puntuacion));
    }

    public void partidaFinalizada(){

        TextInputDialog dialog = new TextInputDialog("jugador1");
        dialog.setTitle("Nueva partida");
        dialog.setHeaderText("Introduzca su nombre");
        dialog.setContentText("nombre:");

        result = dialog.showAndWait();
        result.ifPresent(s -> estadisticas.setPlayerName(s));

    }

}

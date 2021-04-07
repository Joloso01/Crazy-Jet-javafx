package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameOverWindow implements Initializable {

    @FXML
    AnchorPane anchor0;

    @FXML
    Label puntuacionText;

    private Stage st;
    private int puntuacion;


    public void volverAjugar(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/gameWindow.fxml"));
        Parent root = null;
        try {
            root=loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        anchor0.getChildren().remove(0);
        anchor0.getChildren().add(root);
        GameWindow gameWindow = loader.getController();
        gameWindow.comenzarPartida();
    }

    public void volverMenu(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainWindow.fxml"));
        Parent root = null;
        try {
            root=loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setStage(Stage stage) {
        st = stage;
    }

    public void setPuntuacion(int p){
        puntuacion=p;
    }

    public void cambiarDimensiones(){
        st.setHeight(446f);
        st.setWidth(600f);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        puntuacionText.setText(String.valueOf(puntuacion));
    }
}

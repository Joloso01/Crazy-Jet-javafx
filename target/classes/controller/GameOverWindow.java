package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOverWindow {

    @FXML
    AnchorPane anchor0;
    private Stage st;

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

    public void cambiarDimensiones(){
        st.setHeight(400f);
        st.setWidth(640f);
    }
}

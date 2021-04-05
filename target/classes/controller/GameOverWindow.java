package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GameOverWindow {
    Scene scene;

    @FXML
    AnchorPane anchor0;

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
        gameWindow.setScene(scene);
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

    public void setScene(Scene sc){
        scene =sc;
    }
}

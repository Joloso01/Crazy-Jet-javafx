package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SelectJetWindow {

    public static Image spriteSelec;
    private Scene scene;

    @FXML
    private AnchorPane selectPane;

    @FXML
    ImageView jet1Image, jet2Image;

    public void jet2Selected(MouseEvent mouseEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/gameWindow.fxml"));
        AnchorPane ancho = loader.load();
        selectPane.getChildren().remove(0);
        selectPane.getChildren().add(ancho);
        spriteSelec = jet2Image.getImage();
    }

    public void jet1Selected(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/gameWindow.fxml"));
        AnchorPane ancho = loader.load();
        selectPane.getChildren().remove(0);
        selectPane.getChildren().add(ancho);
        spriteSelec= jet1Image.getImage();
    }

    public void setScene(Scene sc){
        scene = sc;
    }
}

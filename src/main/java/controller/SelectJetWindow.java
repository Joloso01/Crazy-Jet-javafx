package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SelectJetWindow {

    public static String spriteSelec;

    @FXML
    private AnchorPane selectPane;

    public void jet2Selected(MouseEvent mouseEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/gameWindow.fxml"));
        AnchorPane ancho = loader.load();
        selectPane.getChildren().remove(0);
        selectPane.getChildren().add(ancho);
        spriteSelec = "@sprites/jets/Hawker_Tempest_MKII/Type_1/Hawker_type1.png";
    }

    public void jet1Selected(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/gameWindow.fxml"));
        AnchorPane ancho = loader.load();
        selectPane.getChildren().remove(0);
        selectPane.getChildren().add(ancho);
        spriteSelec="@sprites/jets/BF-109E/Type-3/Type2_2.png";
    }
}

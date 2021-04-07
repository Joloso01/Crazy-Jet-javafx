package model;

import javafx.scene.image.Image;

public class Bala extends Sprite{

    private float vY;

    private final String imagen = "fxml/images/bala/bala1.png";

    public Bala(double x, double y) {
        setImage(new Image(imagen));
        setX(x);
        setY(y);
    }

    public void update(){
        setY(getPosY()-0.1);
    }


}

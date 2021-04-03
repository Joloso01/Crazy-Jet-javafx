package controller;

import javafx.scene.image.Image;

public class Jet extends Sprite {

    public Jet(Image image) {
        super(image);
    }

    @Override
    public void move(String direction) {
        switch (direction) {
            case "RIGHT":
                setX(getPosX() + 4);
                break;
            case "LEFT":
                setX(getPosX() - 4);
                break;
        }
    }

}

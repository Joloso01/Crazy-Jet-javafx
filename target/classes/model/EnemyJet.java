package model;

import javafx.scene.image.Image;

public class EnemyJet extends Sprite{
    private double velX, velY;
    private int dirX, dirY;

    public EnemyJet(Image image) {
        super(image);
        setX(Math.random()*300);
        setY(getHeight()-400);
        this.velX = 5.0f;
        this.velY = 0.1f;
        this.dirX = 1;
        this.dirY = 1;
    }

    @Override
    public void move() {
        if (getPosY() == getHeight()){
            setY(getHeight());
        }else {
            setY(getPosY() + velY);
        }

    }
}

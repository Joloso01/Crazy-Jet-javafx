package model;

import javafx.scene.image.Image;

public class EnemyJet extends Sprite{
    private final double velX;
    private final double velY;
    private final int tipoEnemigo;
    private final String[] spritesEnemigo = {"fxml/sprites/jets/enemigo1.png","fxml/sprites/jets/enemigo2.png","fxml/sprites/jets/enemigo3.png","fxml/sprites/jets/enemigo4.png"};

    public EnemyJet() {
        setX(Math.random()*400);
        setY(getHeight()-600);
        this.velX = (Math.random()*0.5f)+0.2;
        this.velY = (Math.random()*0.5f)+0.2;
        tipoEnemigo = (int) (Math.random()*3);
        setImage(new Image(spritesEnemigo[tipoEnemigo]));
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

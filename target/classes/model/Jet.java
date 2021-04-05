package model;

import javafx.scene.image.Image;

public class Jet extends Sprite {

    private int vida =3;
    private String spriteMovimiento_derecha="fxml/sprites/jets/playerJet_derecha.png";
    private String spriteMovimiento_izquierda="fxml/sprites/jets/playerJet_izquierda.png";
    private String spriteMovimiento_recto="fxml/sprites/jets/playerJet_recto.png";

    public Jet(Image image) {
        super(image);
    }

    @Override
    public void move(String direction) {
        switch (direction) {
            case "RIGHT":
                if (getPosX() <420){
                    setX(getPosX() + 10);
                    setImage(new Image(spriteMovimiento_derecha));
                }

                break;
            case "LEFT":
                if (getPosX() > 1){
                    setX(getPosX() - 10);
                    setImage(new Image(spriteMovimiento_izquierda));
                }

                break;
            case "UP":
                if (getPosY() > 1){
                    setY(getPosY()-10);
                    setImage(new Image(spriteMovimiento_recto));
                }

                break;
            case "DOWN":
                if (getPosY() < 700){
                    setY(getPosY()+10);
                    setImage(new Image(spriteMovimiento_recto));
                }
                break;

            default:
                setImage(new Image(spriteMovimiento_recto));
                break;
        }
    }

    public void golpeado() {
        vida--;
    }

    public int comprobarVida(){
        return vida;
    }

    public void empezarPartida(){
        vida = 3;
    }
}

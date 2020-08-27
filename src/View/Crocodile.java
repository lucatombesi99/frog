package View;

import View.Entity;
import javafx.scene.image.Image;
import View.Main;

import java.io.File;


public class Crocodile extends Entity {

    private final double speed;
    Image crocodile1Right;
    Image crocodile2Right;
    Image crocodile1Left;
    Image crocodile2Left;
    private boolean hungry=false;

    public Crocodile(int xPos,int yPos,double speed){
        this.speed=speed;
        crocodile1Right = new Image(new File(Main.IMAGE_PATH + "crocodile1Right.png").toURI().toString(),90, 30, true, true);
        crocodile2Right = new Image(new File(Main.IMAGE_PATH+ "crocodile2Right.png").toURI().toString(), 90, 30,true, true);
        crocodile1Left = new Image(new File(Main.IMAGE_PATH + "crocodile1Left.png").toURI().toString(), 90, 30,true, true);
        crocodile2Left = new Image(new File(Main.IMAGE_PATH + "crocodile2Left.png").toURI().toString(), 90, 30,true, true);
        setX(xPos);
        setY(yPos);
        setImage(crocodile1Right);
    }

    public double getSpeed(){
        return this.speed;
    }

    @Override
    public void movement(Long now) {
        move(speed,0);
        if(speed>0){
            if ((now/3/ 900000000 +((int)this.getY()/100) ) % 2 == 1) {
                setImage(crocodile1Right);
                hungry=true;
            }
            else if ((now/3/ 900000000 +((int)this.getY()/100) )% 2 == 0) {
                setImage(crocodile2Right);
                hungry=false;
            }


        }else{
            if ((now/3/ 900000000 +((int)this.getY()/100) )% 2 == 1) {
                setImage(crocodile1Left);
                hungry=true;

            }else if ((now/3/ 900000000 +((int)this.getY()/100) ) % 2 == 0) {
                setImage(crocodile2Left);
                hungry=false;
            }
        }
        if (getX()>500 && speed>0)
            setX(-180);
        if (getX()<-50 && speed<0)
            setX(700);

    }

    public boolean isHungry(){return hungry;}
}

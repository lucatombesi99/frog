package Logic;

import View.Main;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.File;

public class LogicSnake extends LogicEntities{
    private  double speed;


    public LogicSnake(double xPos,double yPos,double speed){
        this.speed=speed;
        setX(xPos);
        setY(yPos);
        setWidth(70);
        setHeight(26.25);
        setFill(Color.GREEN);

    }

    @Override
    public void movement(Long now) {
        move(speed,0);
        if(getX()>=280 && speed>0)
            speed=-speed;
        else if(getX()<=10 && speed<0)
            speed=-speed;

    }
}

package Logic;

import View.Main;
import View.Variables;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.File;

public class LogicBonus extends LogicEntities{
    private double xPos = -100;

    public LogicBonus(){
        setX(500);
        setY(107);
        setWidth(25);
        setHeight(16.21);
        setFill(Color.BLACK);
    }


    @Override
    public void movement(Long now) {
        if (now/900000000 % 4 == 1)
            xPos = RandomBonus.visiblePos();
        if (now/900000000 %4 == 2) {
            setX(xPos + 2);
            Variables.setBonus(xPos+2);
        }
        if (now/900000000 %4 == 3) {
            setX(-100);
            Variables.setBonus(-100);
        }
    }
}

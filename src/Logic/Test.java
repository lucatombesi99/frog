package Logic;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import View.Main;

import java.io.File;



public class Test extends ImageView {

    Image zero;
    Image one;
    Image two;
    Image three;

    public Test(int size, int x, int y){
        setX(x);
        setY(y);
        zero=new Image(new File(Main.IMAGE_PATH + "0.png").toURI().toString(), size, 30, true, true);
        one=new Image(new File(Main.IMAGE_PATH + "1.png").toURI().toString(), size, 30, true, true);
        two=new Image(new File(Main.IMAGE_PATH + "2.png").toURI().toString(), size, 30, true, true);
        three=new Image(new File(Main.IMAGE_PATH + "3.png").toURI().toString(), size, 30, true, true);

        setImage(zero);

    }
    public void setOne(){
        setImage(one);
    }
    public void setTwo(){
        setImage(two);
    }
    public void setZero(){
        setImage(zero);
    }
    public void setThree(){
        setImage(three);
    }
}


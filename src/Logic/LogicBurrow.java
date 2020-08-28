package Logic;


import javafx.scene.paint.Color;



public class LogicBurrow extends LogicEntities{

    private boolean full=false;

    public LogicBurrow(double x,double y){
        setX(x);
        setY(y);
        setHeight(31);
        setWidth(31);
        setFill(Color.RED);
    }

    public void setFrogEnd(){
        full= true;
    }


    public boolean isFull(){
        return full;
    }

    @Override
    public void movement(Long now) {


    }
}

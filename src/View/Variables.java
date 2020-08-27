package View;

import Logic.*;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static View.GameScene.*;
import static View.GameScene.root;

public class Variables {

    public static LogicFrog fr;

    public static double[] getVariables(){
        double[] allVar=new double[9];
        allVar[0]=GameScene.timeLeft;
        allVar[1]=GameScene.timeMax;
        allVar[2]=GameScene.points;
        allVar[3]=GameScene.diffMult;
        allVar[4]=GameScene.FROGGER_LIVES;
        allVar[5]=GameScene.burrowCounter;
        allVar[6]=GameScene.difficulty;
        return allVar;
    }
    public static boolean isLifeLost(){
        return GameScene.lifelost;
    }
    public static void setVariables(double[] allVar){
        GameScene.timeLeft= (int) allVar[0];
        GameScene.timeMax= (int) allVar[1];
        GameScene.points= (int) allVar[2];
        GameScene.diffMult= (int) allVar[3];
        GameScene.FROGGER_LIVES= (int) allVar[4];
        GameScene.burrowCounter= (int) allVar[5];
        Frog.position= (int) allVar[6];
        Frog.xpos= (int) allVar[7];
        Frog.ypos= (int) allVar[8];

    }
    public static void setLifeLost(boolean b){
         GameScene.lifelost=b;
    }
    public static Scene getScene(){
        return GameScene.scene;
    }

    public static <T extends LogicEntities>  List<T> getLogicElements( List<Entity> interceptable){
        ArrayList<T> someArray = new ArrayList<>();
        for(Entity entity:interceptable){
            if(Log.class.isInstance(entity)){
                Log isLog=(Log)entity;
                LogicLog log=new LogicLog(isLog.getType(),isLog.getX(),isLog.getY(),isLog.getSpeed());
                someArray.add((T)log);
            }else if(Vehicle.class.isInstance(entity)){
                Vehicle isVehicle=(Vehicle)entity;
                LogicVehicle car=new LogicVehicle(isVehicle.getType(),isVehicle.getX(),isVehicle.getY(),isVehicle.getSpeed());
                someArray.add((T)car);
            }else if(Turtle.class.isInstance(entity)){
                Turtle isTurtle=(Turtle)entity;
                LogicTurtle turt=new LogicTurtle(isTurtle.getX(),isTurtle.getY(),isTurtle.getSpeed());
                someArray.add((T)turt);
            }else if(Snake.class.isInstance(entity)){
                Snake isSnake=(Snake)entity;
                LogicSnake snake=new LogicSnake(isSnake.getX(),isSnake.getY(),isSnake.getSpeed());
                someArray.add((T)snake);
            }else if(Crocodile.class.isInstance(entity)){
                Crocodile isCroc=(Crocodile)entity;
                LogicCrocodile croc=new LogicCrocodile(isCroc.getX(),isCroc.getY(),isCroc.getSpeed());
                someArray.add((T)croc);
            }else if(Burrow.class.isInstance(entity)){
                Burrow isBurr=(Burrow)entity;
                LogicBurrow burr=new LogicBurrow(isBurr.getX(),isBurr.getY());
                someArray.add((T)burr);
            }else if(Bonus.class.isInstance(entity)){
                LogicBonus bonus=new LogicBonus();
                someArray.add((T)bonus);
            }


        }



        return someArray;
    }
    public static void setBonus(double x) {
        Bonus.bonusX = x;
    }


    public static void setBurrow(double xPos) {
        for(Entity entity:interceptable)
            if(Burrow.class.isInstance(entity))
                if(entity.getX()==xPos){
                    Burrow isBurr=(Burrow)entity;
                    isBurr.setFrogEnd();
                    entity=isBurr;
                }
    }


    public static void startLogic(long now){
        if(!isStarted) {
            logicEntities = Variables.getLogicElements(interceptable);
            Stage stage=new Stage();
            AnchorPane root = new AnchorPane();
            root.getChildren().addAll(logicEntities);
            Scene scene=new Scene(root,350,505);
            fr=new LogicFrog(logicEntities,scene);
            root.getChildren().addAll(fr);
            stage.setScene(scene);
            stage.show();
            isStarted=true;
        }
        for(LogicEntities logicEntity:logicEntities)
            logicEntity.movement(now);

        fr.movement(now);

    }
}

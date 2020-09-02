package View;

import Logic.*;
import javafx.scene.Scene;



import java.util.ArrayList;
import java.util.List;

import static View.AudioEffects.frogGoal;
import static View.GameScene.*;
import static View.Main.autoPlay;

public class Variables {

    public static LogicFrog fr;
    public static List<LogicEntities> logicEntities;


//getDifficulty(),isLifeLost() e setScene() servono per inizializzare corretrtamente le variabili statiche della logicFrog
    public static int getDifficulty(){
        return GameScene.difficulty;
    }

    public static boolean isLifeLost(){
        return GameScene.lifelost;
    }
    public static Scene getScene(){
        return GameScene.scene;
    }

    //setVariables e setLifeLost servono per aggiornare le variabili statiche di gameSystem che controllano lo stato
    // della partita(e.g. burrowCounter,points) che vengono modificate in logicFrog
    public static void setVariables(double[] allVar){
        GameScene.timeLeft= (int) allVar[0];
        GameScene.points= (int) allVar[1];
        GameScene.FROGGER_LIVES= (int) allVar[2];
        GameScene.burrowCounter= (int) allVar[3];
        Frog.position= (int) allVar[4];
        Frog.xpos= (int) allVar[5];
        Frog.ypos= (int) allVar[6];

    }
    public static void setLifeLost(boolean b){
         GameScene.lifelost=b;
    }
    

//getLogicElements è utilizzato per creare la lista di entità logiche che corrisponde alla lista di entità presenti in gameScene
@SuppressWarnings("unchecked")
    public static <T extends LogicEntities>  List<T> getLogicElements( List<Entity> interceptable){
        ArrayList<T> someArray = new ArrayList<>();
        for(Entity entity:interceptable){
            if(entity instanceof Log){
                Log isLog=(Log)entity;
                LogicLog log=new LogicLog(isLog.getType(),isLog.getX(),isLog.getY(),isLog.getSpeed());
                someArray.add((T) log);
            }else if(entity instanceof Vehicle){
                Vehicle isVehicle=(Vehicle)entity;
                LogicVehicle car=new LogicVehicle(isVehicle.getType(),isVehicle.getX(),isVehicle.getY(),isVehicle.getSpeed());
                someArray.add((T)car);
            }else if(entity instanceof Turtle){
                Turtle isTurtle=(Turtle)entity;
                LogicTurtle turt=new LogicTurtle(isTurtle.getX(),isTurtle.getY(),isTurtle.getSpeed());
                someArray.add((T)turt);
            }else if(entity instanceof Snake){
                Snake isSnake=(Snake)entity;
                LogicSnake snake=new LogicSnake(isSnake.getX(),isSnake.getY(),isSnake.getSpeed());
                someArray.add((T)snake);
            }else if(entity instanceof Crocodile){
                Crocodile isCroc=(Crocodile)entity;
                LogicCrocodile croc=new LogicCrocodile(isCroc.getX(),isCroc.getY(),isCroc.getSpeed());
                someArray.add((T)croc);
            }else if(entity instanceof Burrow){
                Burrow isBurr=(Burrow)entity;
                LogicBurrow burr=new LogicBurrow(isBurr.getX(),isBurr.getY());
                someArray.add((T)burr);
            }else if(entity instanceof Bonus){
                LogicBonus bonus=new LogicBonus();
                someArray.add((T)bonus);
            }


        }



        return someArray;
    }

    //metodo utilizzato per spostare l'immagine del bonus
    public static void setBonus(double x) {
        Bonus.bonusX = x;
    }

    //metodo utilizzato per cambiare l'immagine della tana da vuota a piena
    public static void setBurrow(double xPos) {
        for(Entity entity:interceptable)
            if(entity instanceof Burrow)
                if(entity.getX()==xPos){
                    Burrow isBurr=(Burrow)entity;
                    isBurr.setFrogEnd();
                    entity=isBurr;
                }
        if(autoPlay)
        frogGoal.play(20);
    }

//metodo utilizzato per far iniziare la parte logica del programma
    public static void startLogic(long now){
        if(!isStarted) {
            logicEntities = Variables.getLogicElements(interceptable);
           // Stage stage=new Stage(); //
           // AnchorPane root = new AnchorPane(); //
           // root.getChildren().addAll(logicEntities); //
           // Scene scene=new Scene(root,350,505); //
            fr=new LogicFrog(logicEntities,scene);
          //  root.getChildren().addAll(fr); //
           // stage.setScene(scene); //
            //stage.setOpacity(0.5); //
           // stage.show(); //
            isStarted=true;
        }
        for(LogicEntities logicEntity:logicEntities)
            logicEntity.movement(now);

        fr.movement(now);

    }
}

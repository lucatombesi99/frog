package Logic;

import View.*;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.List;

public class LogicFrog extends LogicEntities {


    List<LogicEntities> logicEntities;

    public static int position=1;
    int difficulty;

    double movementV = 31.2;
    double movementH = 15;
    double crocSpeed=0; //serve per capire se si muove verso destra o sinistra

    Scene game;

    public static boolean isAFK=false;//per quando stai afk e il tempo finisce
    boolean  isDeath = true;//per evitare che i key pressed/realesed in eccesso spostino l'animazione della morte
    boolean noMove=false;//per evitare che la rana continui a spostarsi se morta
    boolean carDeath=false;//per continuare a rimanere nell' if anche se finisce collisione
    private boolean singleClick = true;//per continuare a rimanere nell' if anche se finisce collisione
    public static boolean death = false;
    int timeLeft;
    int timeMax;
    public static int points;
    public static int diffMult;
    public static int froggerLives;
    public static boolean lifeLost;
    int burrowCounter;
    double[] allVar;
    private long lastUpdate = 0 ;//per aggiornare il timer ogni secondo



///////
    int size = 30;//serve a fare lo scaling della rana


    public LogicFrog(List<LogicEntities> interceptable,Scene scene) {

        setX(135);
        setY(475);
        setWidth(30);
        setHeight(27.857);
        setFill(Color.BLACK);


        this.logicEntities = interceptable;
       // game=Variables.getScene();
        game=scene;
        allVar=Variables.getVariables();
        timeLeft= (int) allVar[0];
        timeMax= (int) allVar[1];
        points= (int) allVar[2];
        diffMult= (int) allVar[3];
        froggerLives= (int) allVar[4];
        burrowCounter= (int) allVar[5];
        difficulty=(int)allVar[6];
        lifeLost=Variables.isLifeLost();



    }

    @Override
    public void movement(Long now) {

        control();

        if(now - lastUpdate >= 1_000_000_000) {
            timeLeft--;
            lastUpdate=now;
        }
        allVar[0]=timeLeft;
        allVar[1]=timeMax;
        allVar[2]=points;
        allVar[3]=diffMult;
        allVar[4]=froggerLives;
        allVar[5]=burrowCounter;
        allVar[6]=position;
        allVar[7]=getX();
        allVar[8]=getY();
        Variables.setVariables(allVar);
        Variables.setLifeLost(lifeLost);

        if(getX()<0 || getX()>340 || getY()>505){
            carDeath=true;
            death = true;
            isDeath = false;
            isDeath = Death.carDeath(now, this);
            timeLeft=timeMax;

        }

        if(!isAFK)
            if(getY()==475 && getX()==135 ){
                death=false;
                noMove=false;
                carDeath=false;
                lifeLost=false;
                isAFK=false;
            }

        if(timeLeft==0) {
            carDeath=true;
            death = true;
            isDeath = false;
            if(getY()==475 && getX()==135)
                isAFK=true;
            isDeath = Death.carDeath(now, this);

            timeLeft=timeMax;


        }

        if(getY()>260)
            if (Collision.specificCollision(logicEntities, this, LogicVehicle.class) || Collision.specificCollision(logicEntities, this, LogicSnake.class) || carDeath) {
                carDeath=true;
                death = true;
                isDeath = false;
                isDeath = Death.carDeath(now, this);
                timeLeft=timeMax;
            }


        //ACQUA
        if (getY() < 260 && getY() > 107) {
            if(Collision.specificCollision(logicEntities, this, LogicTurtle.class) && !noMove) {

                LogicTurtle turtle = Collision.getOne(logicEntities, this, LogicTurtle.class);
                if(!turtle.isWet())
                    this.move(turtle.getSpeed(), 0);
                else{
                    death = true;
                    isDeath = false;
                    noMove=true;
                    isDeath = Death.waterDeath(now, this);
                    timeLeft=timeMax;
                }

            }else if (Collision.specificCollision(logicEntities, this, LogicLog.class) && !noMove) {
                LogicLog log = Collision.getOne(logicEntities, this, LogicLog.class);
                this.move(log.getSpeed(), 0);

            }else  if(Collision.specificCollision(logicEntities, this, LogicCrocodile.class) && !noMove){
                LogicCrocodile croc=Collision.getOne(logicEntities, this, LogicCrocodile.class);
                crocSpeed=croc.getSpeed();
                this.move(crocSpeed,0);
                if(croc.isHungry())
                    if((this.getX()>=croc.getX()+55 && crocSpeed>0) || (this.getX()<=croc.getX()+30 && crocSpeed<0)){
                        death = true;
                        isDeath = false;
                        noMove=true;
                        isDeath = Death.waterDeath(now, this);
                        timeLeft=timeMax;
                    }


            }else {
                death = true;
                isDeath = false;
                noMove=true;
                isDeath = Death.waterDeath(now, this);
                timeLeft=timeMax;
            }

        }



        //ZONA VITTORIA
        if (getY() < 107) {
            if (Collision.specificCollision(logicEntities, this, LogicBurrow.class)) {
                LogicBurrow b = Collision.getOne(logicEntities, this, LogicBurrow.class);
                if (!b.isFull()) {
                    if (Collision.specificCollision(logicEntities, this, LogicBonus.class)) {
                        points += 1000 * diffMult;

                    }
                    this.setX(135);
                    this.setY(475);
                    b.setFrogEnd();
                    Variables.setBurrow(b.getX());
                    burrowCounter++;
                    points+=800*diffMult;
                    timeLeft=timeMax;
                    RandomBonus.removePos((int) b.getX());
                } else {
                    death = true;
                    isDeath = false;
                    isDeath = Death.waterDeath(now, this);

                }


            }else{
                death = true;
                isDeath = false;
                isDeath = Death.waterDeath(now, this);
            }
        }



    }

    //gestisce i movimenti della rana
    public void control(){
        if (!death) {


            game.setOnKeyPressed(event -> {

                if ((event.getCode() == KeyCode.W ||event.getCode()== KeyCode.UP  ) && singleClick && getY() > 120) {
                    singleClick = false;
                    if (isDeath) {
                        move(0, -movementV);
                        position=5;
                    }

                } else if ((event.getCode() == KeyCode.A ||event.getCode()== KeyCode.LEFT  )  && singleClick ) {
                    singleClick = false;
                    if (isDeath) {
                        move(-movementH, 0);
                        position=6;
                    }

                } else if ((event.getCode() == KeyCode.S ||event.getCode()== KeyCode.DOWN  )  && singleClick) {
                    singleClick = false;
                    if (isDeath) {
                        move(0, movementV);
                        position=7;
                    }

                } else if ((event.getCode() == KeyCode.D ||event.getCode()== KeyCode.RIGHT  )  && singleClick) {
                    singleClick = false;
                    if (isDeath) {
                        move(movementH, 0);
                        position=8;
                    }
                }
            });
        }

        if (!death) {


            game.setOnKeyReleased(event -> {

                if (event.getCode() == KeyCode.W ||event.getCode()== KeyCode.UP  ) {
                    if (isDeath) {
                        singleClick = true;
                        position=1;
                        points+=5*diffMult;
                    }
                } else if (event.getCode() == KeyCode.A ||event.getCode()== KeyCode.LEFT ) {
                    if (isDeath) {
                        singleClick = true;
                        position=2;
                        points+=5*diffMult;
                    }
                } else if (event.getCode() == KeyCode.S ||event.getCode()== KeyCode.DOWN ) {
                    if(isDeath) {
                        singleClick = true;
                        position=3;
                        points+=5*diffMult;
                    }
                } else if (event.getCode() == KeyCode.D ||event.getCode()== KeyCode.RIGHT ) {
                    if(isDeath) {

                        singleClick = true;
                        position=4;
                        points+=5*diffMult;
                    }
                }
            });
        }
    }
}

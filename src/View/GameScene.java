package View;


import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static View.Main.autoPlay;
import static View.RankingTable.enableAddButton;


public class GameScene {


    public AnimationTimer timer;
    Frog frog;


    Media media;
    public static int  timeLeft;//da modificare con le scene
    public static int points;
    public static int difficulty;
    public static Scene scene;

    public static int FROGGER_LIVES; //da modificare con le scene
    public static boolean lifelost=false;
    EasyScene easy=new EasyScene();
    MediumScene medium=new MediumScene();
    HardScene hard=new HardScene();
    public static boolean isStarted=false;



    public static Button pauseButton;

    Label timeLabel;
    Label scoreLabel;
    static AnchorPane root;

    public static MediaPlayer mediaPlayer;
    ImageView win;
    ImageView lost;
    Image w = new Image(new File(Main.IMAGE_PATH + "win.png").toURI().toString(), 350, 500, true, true, false);
    Image l = new Image(new File(Main.IMAGE_PATH + "gameover.png").toURI().toString(), 350, 500, true, true, false);
    public static int burrowCounter=0;
    public static List<Entity> interceptable;


    public  void startGame( int difficulty) {
        GameScene.difficulty=difficulty;
        lifelost=false;
        isStarted=false;


        win=new ImageView(w);
        lost=new ImageView(l);
        AnchorPane.setTopAnchor(win,220.0);
        AnchorPane.setTopAnchor(lost,220.0);


        //MUSICA di SOTTOFONDO
        media = new Media(new File(Main.AUDIO_PATH + "theme.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        if(autoPlay)
            mediaPlayer.play();
        else
            mediaPlayer.pause();



       if(difficulty==0)
            root=easy.setScene();
        else if(difficulty==1)
            root=medium.setScene();
        else
            root=hard.setScene();


        //Bottone
        pauseButton = new Button("||");
        AnchorPane.setTopAnchor(pauseButton, 7.0);
        AnchorPane.setLeftAnchor(pauseButton, 0.0);
        //Etiichetta tempo
        timeLabel=new Label("Time: "+timeLeft);
        timeLabel.setFont(new Font("Calibri", 20));
        AnchorPane.setTopAnchor(timeLabel, 10.0);
        AnchorPane.setLeftAnchor(timeLabel, 185.0);

        //Etichetta Punteggio
        scoreLabel = new Label("Score: "+ points);
        scoreLabel.setFont(new Font("Calibri", 20));
        AnchorPane.setTopAnchor(scoreLabel, 10.0);
        AnchorPane.setLeftAnchor(scoreLabel, 30.0);


       root.getChildren().addAll(timeLabel,pauseButton,scoreLabel);

        frog=new Frog(Main.IMAGE_PATH + "froggerUp.png");


        Rectangle r = new Rectangle();

        r.setX(130);
        r.setY(450);
        r.setWidth(100);
        r.setHeight(100);
        r.setFill(Color.BLACK);



        Main.primaryStage.setScene(scene);




        interceptable=getEntity(Entity.class);

         scene=new Scene(root,350,505);

        Main.primaryStage.setScene(scene);

        root.getChildren().addAll(frog);

        startMoving();
        timer.start();



//scene




        pauseButton.setOnAction(e ->{
            pauseButton.setDisable(true);
            PauseClass.pause(timer,pauseButton);});

    }

    public void startMoving() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                List<Entity> objects = getEntity(Entity.class);
                for (Entity object : objects) {
                    object.movement(now);
                }

                    Variables.startLogic(now);




                scoreLabel.setText("Score: " + points);
                timeLabel.setText("Time: " + timeLeft);


                if (lifelost)
                    root.getChildren().remove(root.getChildren().size() - 6);

                if (FROGGER_LIVES == 0 || burrowCounter == 5) {
                    enableAddButton = true;
                    pauseButton.setDisable(true);
                    mediaPlayer.pause();
                    timer.stop();
                    try {
                        RankingTable.scoreRecord();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (FROGGER_LIVES == 0)
                        root.getChildren().add(lost);
                    else
                        root.getChildren().add(win);
                }


            }

            };

        }
        @SuppressWarnings("unchecked")
//per togliere il warning del cast,infatti ogni nodo che passa il controllo è sicuramente un'entità
        public <T extends Entity > List < T > getEntity(Class < T > cls) {
            ArrayList<T> someArray = new ArrayList<>();
            for (Node n : root.getChildren())
                if (cls.isInstance(n)) {
                    someArray.add((T) n);
                }
            return someArray;

        }





}


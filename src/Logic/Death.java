package Logic;


import View.Frog;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import View.Main;


import java.io.File;


public class Death {
    private final static String die= new File(Main.AUDIO_PATH + "frog_die.wav").toURI().toString();
    static AudioClip frogDie = new AudioClip(die);

    private final static String waterSplashSound= new File(Main.AUDIO_PATH + "water-splash.wav").toURI().toString();
    static AudioClip waterSplash= new AudioClip(waterSplashSound);

    static int carD = 0;
    static int watD = 0;

    public static boolean carDeath(Long now, LogicFrog frog){

        boolean death= false;

        if((now% 12)==0)
            carD++;

        if(carD==1) {
      //      frog.setImage(new Image(new File(Main.IMAGE_PATH + "cardeath1.png").toURI().toString(), 30, 30, true, true));
            LogicFrog.position=9;

        }

        if(carD==2) {
         //   frog.setImage(new Image(new File(Main.IMAGE_PATH + "cardeath2.png").toURI().toString(), 30, 30, true, true));
            LogicFrog.position=10;

        }
        if(carD==3) {
        //    frog.setImage(new Image(new File(Main.IMAGE_PATH + "cardeath3.png").toURI().toString(), 30, 30, true, true));
            LogicFrog.position=11;
        }if (carD == 4) {
         //   frog.setImage(new Image(new File(Main.IMAGE_PATH + "froggerUp.png").toURI().toString(), 30, 30, true, true));
            LogicFrog.position=1;
            carD = 0;
            frog.setX(135);
            frog.setY(475);
            death=true;
            LogicFrog.froggerLives--;
            LogicFrog.lifeLost=true;
            LogicFrog.isAFK=false;
            LogicFrog.points-=300/LogicFrog.diffMult;


        }
        return death;
    }


    public static boolean waterDeath(Long now, LogicFrog frog) {

        boolean death=false;

        if((now%12)==0)
            watD++;

        if(watD==1) {
           // frog.setImage(new Image(new File(Main.IMAGE_PATH + "waterdeath1.png").toURI().toString(), 30, 30, true, true));
         //   waterSplash.play(20);
            LogicFrog.position=12;
        }


        if(watD==2) {
           // frog.setImage(new Image(new File(Main.IMAGE_PATH + "waterdeath2.png").toURI().toString(), 30, 30, true, true));
            LogicFrog.position=13;

        }
        if(watD==3) {
           // frog.setImage(new Image(new File(Main.IMAGE_PATH + "waterdeath3.png").toURI().toString(), 30, 30, true, true));
            LogicFrog.position=14;

        }if (watD == 4) {
           // frog.setImage(new Image(new File(Main.IMAGE_PATH + "froggerUp.png").toURI().toString(), 30, 30, true, true));
            LogicFrog.position=1;
            watD = 0;
            frog.setX(135);
            frog.setY(475);
            death=true;
            LogicFrog.froggerLives--;
            LogicFrog.lifeLost=true;
            LogicFrog.isAFK=false;
            LogicFrog.points-=300/LogicFrog.diffMult;

        }
        return death;
    }
}

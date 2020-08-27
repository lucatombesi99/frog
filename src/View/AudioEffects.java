package View;

import javafx.scene.media.AudioClip;

import java.io.File;
import java.util.*;

public class AudioEffects {

    private final static String frogSound = new File(Main.AUDIO_PATH + "frog.wav").toURI().toString();
    private final static String carPassSound= new File(Main.AUDIO_PATH + "car-pass.wav").toURI().toString();
    private final static String splashSound= new File(Main.AUDIO_PATH + "splash.wav").toURI().toString();
    private final static String waterSplashSound= new File(Main.AUDIO_PATH + "water-splash.wav").toURI().toString();
    private final static String sirenSound= new File(Main.AUDIO_PATH + "siren.wav").toURI().toString();
    private final static String hornSound= new File(Main.AUDIO_PATH + "long-horn.wav").toURI().toString();

    //FROG EFFECT
    private final static  AudioClip frogAudio = new AudioClip(frogSound);

    //WATER EFFECTS
    private final static AudioClip splash= new AudioClip(splashSound);
    private final static AudioClip waterSplash= new AudioClip(waterSplashSound);

    //ROAD EFFECTS
    private final static AudioClip siren = new AudioClip(sirenSound);
    private final static AudioClip carPass = new AudioClip(carPassSound);
    private final static AudioClip horn = new AudioClip(hornSound);



    public static void playRandomAmbientSound(double timeLeft, Frog frog){

        LinkedList<AudioClip>road_effects = new LinkedList<>();
        road_effects.add(siren);
        road_effects.add(carPass);
        road_effects.add(horn);

        LinkedList<AudioClip> water_effects = new LinkedList<>();
        water_effects.add(splash);
        water_effects.add(waterSplash);
        Random rand = new Random();

        //FROG
        if(timeLeft %20 ==0)
            frogAudio.play(20);

        //WATER
        if(timeLeft%4==0 && frog.getY() < 260 && frog.getY() > 107)
            water_effects.get(rand.nextInt(2)).play(20);


        //ROAD
        if(timeLeft%5==0 && frog.getY()< 465 && frog.getY() >260)
            road_effects.get(rand.nextInt(3)).play(20);
    }
}

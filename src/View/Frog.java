package View;


import javafx.scene.image.Image;
import java.io.File;




//modificato

public class Frog extends Entity { //da finire collisione con il coccodrillo



    Image imgW1;
    Image imgA1;
    Image imgS1;
    Image imgD1;
    Image imgW2;
    Image imgA2;
    Image imgS2;
    Image imgD2;
    Image carD1;
    Image carD2;
    Image carD3;
    Image watD1;
    Image watD2;
    Image watD3;

    public static int position=1;
    public static double xpos=135;
    public static double ypos=475;



    int size = 30;//serve a fare lo scaling della rana


    public Frog(String link) {
        setImage(new Image(new File(link).toURI().toString(), size, size, true, true));
        setX(135);
        setY(475);
        imgW1 = new Image(new File(Main.IMAGE_PATH + "froggerUp.png").toURI().toString(), size, size, true, true);
        imgA1 = new Image(new File(Main.IMAGE_PATH + "froggerLeft.png").toURI().toString(), size, size, true, true);
        imgS1 = new Image(new File(Main.IMAGE_PATH + "froggerDown.png").toURI().toString(), size, size, true, true);
        imgD1 = new Image(new File(Main.IMAGE_PATH + "froggerRight.png").toURI().toString(), size, size, true, true);
        imgW2 = new Image(new File(Main.IMAGE_PATH + "froggerUpJump.png").toURI().toString(), size, size, true, true);
        imgA2 = new Image(new File(Main.IMAGE_PATH + "froggerLeftJump.png").toURI().toString(), size, size, true, true);
        imgS2 = new Image(new File(Main.IMAGE_PATH + "froggerDownJump.png").toURI().toString(), size, size, true, true);
        imgD2 = new Image(new File(Main.IMAGE_PATH + "froggerRightJump.png").toURI().toString(), size, size, true, true);
        watD1=new Image(new File(Main.IMAGE_PATH + "waterdeath1.png").toURI().toString(), 30, 30, true, true);
        watD2=new Image(new File(Main.IMAGE_PATH + "waterdeath2.png").toURI().toString(), 30, 30, true, true);
        watD3=new Image(new File(Main.IMAGE_PATH + "waterdeath3.png").toURI().toString(), 30, 30, true, true);
        carD1=new Image(new File(Main.IMAGE_PATH + "cardeath1.png").toURI().toString(), 30, 30, true, true);
        carD2=new Image(new File(Main.IMAGE_PATH + "cardeath2.png").toURI().toString(), 30, 30, true, true);
        carD3=new Image(new File(Main.IMAGE_PATH + "cardeath3.png").toURI().toString(), 30, 30, true, true);

    }

    @Override
    public void movement(Long now) {
        if(position==1)
            this.setImage(imgW1);
        else if(position==2)
            this.setImage(imgA1);
        else if(position==3)
            this.setImage(imgS1);
        else if(position==4)
            this.setImage(imgD1);
        else if(position==5)
            this.setImage(imgW2);
        else if(position==6)
            this.setImage(imgA2);
        else if(position==7)
            this.setImage(imgS2);
        else if(position==8)
            this.setImage(imgD2);
        else if(position==9)
            this.setImage(carD1);
        else if(position==10)
            this.setImage(carD2);
        else if(position==11)
            this.setImage(carD3);
        else if(position==12)
            this.setImage(watD1);
        else if(position==13)
            this.setImage(watD2);
        else
            this.setImage(watD3);

            setX(xpos);
        setY(ypos);

    }
}

      /*  control();
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
            if (Collision.specificCollision(entities, this, Vehicle.class) || Collision.specificCollision(entities, this, Snake.class) || carDeath) {
                carDeath=true;
                death = true;
                isDeath = false;
                isDeath = Death.carDeath(now, this);
                timeLeft=timeMax;
            }


        //ACQUA
        if (getY() < 260 && getY() > 107) {
            if(Collision.specificCollision(entities, this, Turtle.class) && !noMove) {

                Turtle turtle = Collision.getOne(entities, this, Turtle.class);
                if(!turtle.isWet())
                    this.move(turtle.getSpeed(), 0);
                else{
                    death = true;
                    isDeath = false;
                    noMove=true;
                    isDeath = Death.waterDeath(now, this);
                    timeLeft=timeMax;
                }

            }else if (Collision.specificCollision(entities, this, Log.class) && !noMove) {
                Log log = Collision.getOne(entities, this, Log.class);
                this.move(log.getSpeed(), 0);

            }else  if(Collision.specificCollision(entities, this, Crocodile.class) && !noMove){
                Crocodile croc=Collision.getOne(entities, this, Crocodile.class);
                crocSpeed=croc.getSpeed();
                this.move(crocSpeed,0);
                if(croc.isHungry())
                    if((this.getX()>=croc.getX()+65 && crocSpeed>0) || (this.getX()<=croc.getX()+25 && crocSpeed<0)){
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
            if (Collision.specificCollision(entities, this, Burrow.class)) {
                frogGoal.play(20);
                Burrow b = Collision.getOne(entities, this, Burrow.class);
                if (!b.isFull()) {
                    if (Collision.specificCollision(entities, this, Bonus.class)) {
                        points += 1000 * diffMult;
                        bonus.play(20);
                    }
                    this.setX(135);
                    this.setY(475);
                    b.setFrogEnd();
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

  */


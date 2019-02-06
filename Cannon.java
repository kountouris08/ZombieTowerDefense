package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by RealProgramming4Kids on 2018-10-20.
 */

public class Cannon {
    public Sprite cannonSprite;
    public float xpos,ypos,width,height,angle,range;
    public int counter, fireDelay;
    public Sound bigBoom;
    public boolean inRange;

    public Cannon(float x, float y) {
        bigBoom = Gdx.audio.newSound(Gdx.files.internal("Bullet.mp3"));
        cannonSprite = new Sprite(Resources.cannonTexture);
        width = Resources.cannonTexture.getWidth();
        height = Resources.cannonTexture.getHeight();
        xpos = lockToGrid(x-width/2);
        ypos = lockToGrid(y-height/2);;

        cannonSprite.setPosition(lockToGrid(xpos),lockToGrid(ypos));
        fireDelay = 30;
        counter = fireDelay;
    }
    public Cannon(float x, float y, boolean b) {
        bigBoom = Gdx.audio.newSound(Gdx.files.internal("Bullet.mp3"));
        cannonSprite = new Sprite(Resources.firecannonTexture);
        width = Resources.firecannonTexture.getWidth();
        height = Resources.firecannonTexture.getHeight();
        xpos = lockToGrid(x-width/2);
        ypos = lockToGrid(y-height/2);;

        cannonSprite.setPosition(lockToGrid(xpos),lockToGrid(ypos));
        fireDelay = 1;
        counter = fireDelay;
    }

    private float lockToGrid(float pos) {
        return ((int) (pos+25)/50)*50;
    }

    public void draw(SpriteBatch batch){
        cannonSprite.draw(batch);
    }

    public void update() {


        if (counter++ >= fireDelay){
            counter=0;
            pewpew();
            getAngle();
            cannonSprite.setRotation(angle);
        }
    }
    public void pewpew() {
        if (!ZombieTD.zomboList.isEmpty()) {

            if (withinRange(300)) {
                ZombieTD.bulletList.add(new Bullet(xpos + width / 2, ypos + height / 2));
                bigBoom.play();
            }
        }
    }


    public void getAngle() {
        if (!ZombieTD.zomboList.isEmpty()){
            float xC, yC, xZ,yZ;
            xC = xpos;
            yC = ypos;
            xZ = ZombieTD.zomboList.get(0).xpos;
            yZ = ZombieTD.zomboList.get(0).ypos;
            angle = (float) Math.atan((yC-yZ)/(xC-xZ));
            if (xC >= xZ) {
                angle += Math.PI;
            }
            if (withinRange(300)) {
                this.angle = (float) Math.toDegrees(angle);
            }
        }
    }
   public boolean withinRange(int x) {

       if (!ZombieTD.zomboList.isEmpty()) {
           for (int i = 0; i < ZombieTD.cannonList.size(); i++) {
               if (Math.sqrt(Math.pow((ZombieTD.cannonList.get(i).xpos - ZombieTD.zomboList.get(0).xpos), 2) + Math.pow((ZombieTD.cannonList.get(i).ypos - ZombieTD.zomboList.get(0).ypos), 2)) < x)
                   return true;
           }
       }
       return false;
   }
}







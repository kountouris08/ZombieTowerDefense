package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.*;


/**
 * Created by RealProgramming4Kids on 11/10/2018.
 */

public class Bullet {
    public Texture bulletTexture;
    public float xpos,ypos,width,height,speed,angle;
    int life;
    public boolean active = true;

    public Bullet (float x, float y){

        bulletTexture = Resources.bulletTexture;
        xpos = x;
        ypos = y;
        speed = 2;
        life = 100;
        width = Resources.bulletTexture.getWidth();
        height = Resources.bulletTexture.getHeight();
        getAngle();
    }
    public void draw(SpriteBatch batch){
        batch.draw(bulletTexture, xpos-width/2, ypos-height/2);
    }
    public void update() {

        xpos += (float)Math.cos(angle)*speed;
        ypos += (float)Math.sin(angle)*speed;
        if (life-- < 0){
            active = false;
        }
    }
    public void getAngle() {
        if (!ZombieTD.zomboList.isEmpty()) {
            float xC,xZ,yC,yZ,angle;
            xC = xpos;
            yC = ypos;
            xZ = ZombieTD.zomboList.get(0).xpos;
            yZ = ZombieTD.zomboList.get(0).ypos;
            angle =(float) Math.atan((yC-yZ)/(xC-xZ));
            if (xC >= xZ){
                angle += Math.PI;
            }
            this.angle = angle;

        }
    }
    public Circle getCircle() {
        return new Circle(xpos,ypos,width/2);
    }

}

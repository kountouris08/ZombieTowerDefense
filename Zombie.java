package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by RealProgramming4Kids on 2018-10-27.
 */

public class Zombie {
    public Texture zombieTexture;
    public float xpos, ypos, width, height, speed, HP, frameTime;
    public int numCol, numRow;
    public Animation walkAnime;
    public TextureRegion[] walkFrame;
    public TextureRegion currentFrame;

    public boolean active = true;

    public Zombie(float x, float y, int hp) {
        initTexture();
        xpos = x;
        ypos = y;
        speed = 1;
        HP = hp;

        width = Resources.zombieTexture.getWidth() / 4;
        height = Resources.zombieTexture.getHeight();
        initAnimation();
    }
    public void initTexture() {
        zombieTexture = Resources.zombieTexture;
    }

    public void draw(SpriteBatch batch) {
        frameTime += Gdx.graphics.getDeltaTime();
        currentFrame = (TextureRegion)walkAnime.getKeyFrame(frameTime, true);
        batch.draw(currentFrame, xpos - width / 2, ypos - height / 2);
    }

    public void update() {
        if (xpos < 0) {
            ZombieTD.lives--;
            UI.life = ZombieTD.lives;
            active = false;
        }
        xpos = xpos - speed;
        //ypos = (int)(Math.random()*551);
    }

    public Rectangle getRectangle() {
        return new Rectangle(xpos, ypos, width, height);
    }

    public void takeDamagae() {
        if (HP-- < 0) {
            active = false;
            UI.money += 10;
        }

    }

    public void initAnimation() {
        numRow = 1;
        numCol = 4;
        TextureRegion[][] temp = TextureRegion.split(zombieTexture, zombieTexture.getWidth() / numCol, zombieTexture.getHeight() / numRow);
        walkFrame = new TextureRegion[numRow * numCol];
        int frameIndex = 0;
        for (int l = 0; l < numRow; l++) {
            for (int j = 0; j < numCol; j++) {
                walkFrame[frameIndex++] = temp[l][j];


            }
        }
        walkAnime = new Animation(0.2f, walkFrame);

    }

}







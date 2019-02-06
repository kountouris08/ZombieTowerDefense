package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by RealProgramming4Kids on 2018-12-01.
 */

public class Explosion {
    public Texture explosionTexture;
    public float xpos, ypos, width, height, frameTime;
    public boolean active;
    public int hp, numCol, numRow;
    public Animation expAnime;
    public TextureRegion[] expFrames;
    public TextureRegion currentFrame;

    public Explosion(float x, float y) {
        active = true;
        numCol = 6;
        numRow = 1;
        explosionTexture = Resources.explosionTexture;
        xpos = x;
        ypos = y;
        hp = 100;


        width = explosionTexture.getWidth() / numCol;
        height = explosionTexture.getHeight() / numRow;
    initAnimation();
    }

    public void initAnimation() {

        TextureRegion[][] temp = TextureRegion.split(explosionTexture, explosionTexture.getWidth() / numCol, explosionTexture.getHeight() / numRow);
        expFrames = new TextureRegion[numRow * numCol];
        int frameIndex = 0;
        for (int l = 0; l < numRow; l++) {
            for (int j = 0; j < numCol; j++) {
                expFrames[frameIndex++] = temp[l][j];


            }
        }
        expAnime = new Animation(0.2f,expFrames);
    }
    public void draw(SpriteBatch batch) {
        frameTime += Gdx.graphics.getDeltaTime();
        currentFrame = (TextureRegion)expAnime.getKeyFrame(frameTime, true);
        batch.draw(currentFrame, xpos - width / 2, ypos - height / 2);
    }
    public void update() {
        if(hp > 0) {
            hp--;
        }
        else {
            active = false;
        }
    }

}

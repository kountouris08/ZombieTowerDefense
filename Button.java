package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by RealProgramming4Kids on 2/2/2019.
 */

public class Button {
    public Texture bTexture;
    public float xpos, ypos, width, height;
    public Rectangle rect;
    public Button(float x, float y, Texture texture) {
        xpos = x;
        ypos = y;

        bTexture = texture;

        width = (float) bTexture.getWidth();
        height = (float) bTexture.getHeight();

        rect = new Rectangle(xpos, ypos, width, height);

    }
    public void draw (SpriteBatch batch) {
        batch.draw(bTexture, (xpos-width), (ypos-height));
    }

    public boolean isClicked(float x, float y) {

            return  rect.contains(x+width,y+height);

    }

}

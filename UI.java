package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;

/**
 * Created by RealProgramming4Kids on 2018-12-01.
 */

public class UI {
    public static int money = 80;
    public static int waves = 0;
    public static int life = 20;
    public static BitmapFont font = new BitmapFont();

    public static void draw(SpriteBatch batch) {
        font.setColor(Color.YELLOW);
        font.draw(batch, "Lives: " + life, 100, 80);
        font.draw(batch, "Money: " + money, 100, 100);
        font.draw(batch, "Waves: " + waves, 100, 120);
        if (life <= 0 ) {
            font.getData().setScale(2);
            font.draw(batch, "Game Over", 430,300);
            font.getData().setScale(1);
        }
    }

}

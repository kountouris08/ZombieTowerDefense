package com.mygdx.game;

/**
 * Created by RealProgramming4Kids on 12/15/2018.
 */

public class FastZombie extends Zombie {
    public FastZombie(float x, float y, int hp){
        super(x, y, hp);
        speed = 2;
        //initTexture();
    }
    public void initTexture() {
        zombieTexture = Resources.fastZombieTexture;
    }
}

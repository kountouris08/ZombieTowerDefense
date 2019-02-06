package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;


import org.lwjgl.opengl.Display;

import java.awt.Dimension;
import java.util.ArrayList;

public class ZombieTD extends ApplicationAdapter {
	SpriteBatch batch;
	public OrthographicCamera camera;

	public static ArrayList<Cannon> cannonList = new ArrayList<Cannon>();
	public static ArrayList<Zombie> zomboList = new ArrayList<Zombie>();
	public static ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
	public static ArrayList<Explosion> explosionList = new ArrayList<Explosion>();
	public static Button cannonButton;

	public boolean isBuildable(float x, float y) {
		return (y < 500 && y > 450 || y < 350 && y > 300 || y < 200 && y > 150 || y < 50);
	}
	public static int waves = 0;
	public static int lives = 20;
	@Override
	public void create() {

		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho (false,(Gdx.graphics.getWidth()/2),(Gdx.graphics.getHeight()/2 + 50));
		cannonButton = new Button(400, 550, Resources.explosionTexture);

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		update();
		batch.draw(Resources.backgroundTexture, 0, 0);
		cannonButton.draw(batch);
		//drawing stuff goes here
		for (int i = 0; i < cannonList.size(); i++) {
			cannonList.get(i).draw(batch);
		}

		for (int i = 0; i < zomboList.size(); i++) {

			zomboList.get(i).draw(batch);
		}
		for (int i = 0; i < bulletList.size(); i++) {

			bulletList.get(i).draw(batch);
		}
		for (int i = 0; i < explosionList.size(); i++) {

			explosionList.get(i).draw(batch);
		}
		UI.draw(batch);


		batch.end();


	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	public void update() {
		controls();
		removeSprite();
		checkCollisions();
		spawnZombies();

		if (lives > 0) {
			for (int i = 0; i < zomboList.size(); i++) {
				zomboList.get(i).update();
			}
			for (int i = 0; i < bulletList.size(); i++) {
				bulletList.get(i).update();
			}
			for (int i = 0; i < cannonList.size(); i++) {
				cannonList.get(i).update();
			}
			for (int i = 0; i < explosionList.size(); i++) {
				explosionList.get(i).update();
			}
		}
	}

	public void controls() {
		if (Gdx.input.justTouched()) {
			int x, y;
			x = Gdx.input.getX();
			y = Gdx.graphics.getHeight() - Gdx.input.getY();

			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(),0);



			camera.unproject(touchPos);

			if (isBuildable((int)touchPos.x, (int)touchPos.y) && UI.money >= 10) {
				cannonList.add(new Cannon((int)touchPos.x, (int)touchPos.y));
				UI.money -= 10;
				removeTowerStack();
			}
			if (cannonButton.isClicked((int)touchPos.x,(int)touchPos.y)) {
				if (UI.money >= 10) {
					for (int i = 0; i < zomboList.size(); i++) {
						zomboList.get(i).active = false;
					}
					UI.money -= 10;
				}
			}


		}
	}

	public void spawnZombies() {
	if (zomboList.isEmpty()) {
		waves++;
		UI.waves = waves;
		for (int i = 0; i < 4 + waves*4; i++) {
			zomboList.add(new Zombie(1024 + i * 50, 275,10+waves));
			zomboList.add(new Zombie(1024 + i * 50, 175,10+waves));
			zomboList.add(new Zombie(1024 + i * 50, 375,10+waves));

		}
		zomboList.add(new FastZombie(1024 + (4 + waves*4) * 50, 275,10+waves));
	}
	}

	public void removeSprite() {
		for (int i = 0; i < zomboList.size(); i++) {
			if (!zomboList.get(i).active) {
				zomboList.remove(i);
			}

		}
		for (int i = 0; i < bulletList.size(); i++) {
			if (!bulletList.get(i).active) {
				bulletList.remove(i);
			}

		}
		for (int i = 0; i < explosionList.size(); i++) {
			if (!explosionList.get(i).active) {
				explosionList.remove(i);
			}

		}
	}

	public void checkCollisions() {
		for (int i = 0; i < bulletList.size(); i++) {
			for (int j = 0; j < zomboList.size(); j++) {
				if (Intersector.overlaps(bulletList.get(i).getCircle(), zomboList.get(j).getRectangle())) {
					zomboList.get(j).takeDamagae();
					bulletList.get(i).active = false;
					explosionList.add(new Explosion(zomboList.get(j).xpos, zomboList.get(j).ypos));
				}
			}
		}
	}

	public void removeTowerStack() {
		if (cannonList.size() > 1) {
			for (int i = 0; i < cannonList.size() - 1; i++) {
				if (cannonList.get(i).xpos == cannonList.get(cannonList.size() - 1).xpos && cannonList.get(i).ypos == cannonList.get(cannonList.size() - 1).ypos) {
					cannonList.remove(cannonList.size() - 1);
					UI.money += 10;
					if (UI.money >= 50) {
						UI.money -= 50;
						cannonList.add(new Cannon(cannonList.get(i).xpos, cannonList.get(i).ypos, true));
						cannonList.remove(i);
					}
				}
			}
		}
	}

}
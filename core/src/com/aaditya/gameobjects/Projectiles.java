package com.aaditya.gameobjects;

import java.util.ArrayList;

import com.aaditya.eehelpers.AssetLoader;
import com.aaditya.gameworld.GameWorld;
import com.badlogic.gdx.math.MathUtils;

public class Projectiles {
	private GameWorld myWorld;
	private Projectile projectile0, projectile1, projectile2, projectile3,
			projectile4, projectile5, projectile6, projectile7, projectile8,
			projectile9, projectile10, projectile11, projectile12,
			projectile13, projectile14;//, projectile15, projectile16,
			//projectile17, projectile18, projectile19;
	private float gameWidth;
	private float gameHeight;
	private float NO_OF_PROJECTILES = 15;
	private float PROJECTILE_SPEEDY = 40;
	private float PROJECTILE_WIDTH = 2;
	private float PROJECTILE_HEIGHT = 10;
	private ArrayList<Projectile> tropo, strato, meso, thermo, exo,
			currentArray;

	private Alien alien;
	

	public Projectiles(GameWorld gameWorld) {
		gameWidth = AssetLoader.gameWidth;
		gameHeight = AssetLoader.gameHeight;

		myWorld = gameWorld;
		alien = gameWorld.getAlien();

		projectile0 = new Projectile(MathUtils.random(gameWidth * 7 / 8),
				-gameHeight * 0 / NO_OF_PROJECTILES, 0, PROJECTILE_SPEEDY,
				PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
		projectile1 = new Projectile(getProjectileValue(projectile0),
				-gameHeight * 1 / NO_OF_PROJECTILES, 0, PROJECTILE_SPEEDY,
				PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
		projectile2 = new Projectile(getProjectileValue(projectile1),
				-gameHeight * 2 / NO_OF_PROJECTILES, 0, PROJECTILE_SPEEDY,
				PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
		projectile3 = new Projectile(getProjectileValue(projectile2),
				-gameHeight * 3 / NO_OF_PROJECTILES, 0, PROJECTILE_SPEEDY,
				PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
		projectile4 = new Projectile(getProjectileValue(projectile3),
				-gameHeight * 4 / NO_OF_PROJECTILES, 0, PROJECTILE_SPEEDY,
				PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
		projectile5 = new Projectile(getProjectileValue(projectile4),
				-gameHeight * 5 / NO_OF_PROJECTILES, 0, PROJECTILE_SPEEDY,
				PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
		projectile6 = new Projectile(getProjectileValue(projectile5),
				-gameHeight * 6 / NO_OF_PROJECTILES, 0, PROJECTILE_SPEEDY,
				PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
		projectile7 = new Projectile(getProjectileValue(projectile6),
				-gameHeight * 7 / NO_OF_PROJECTILES, 0, PROJECTILE_SPEEDY,
				PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
		projectile8 = new Projectile(getProjectileValue(projectile7),
				-gameHeight * 8 / NO_OF_PROJECTILES, 0, PROJECTILE_SPEEDY,
				PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
		projectile9 = new Projectile(getProjectileValue(projectile8),
				-gameHeight * 9 / NO_OF_PROJECTILES, 0, PROJECTILE_SPEEDY,
				PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
		projectile10 = new Projectile(getProjectileValue(projectile9),
				-gameHeight * 10 / NO_OF_PROJECTILES, 0, PROJECTILE_SPEEDY,
				PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
		projectile11 = new Projectile(getProjectileValue(projectile10),
				-gameHeight * 11 / NO_OF_PROJECTILES, 0, PROJECTILE_SPEEDY,
				PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
		projectile12 = new Projectile(getProjectileValue(projectile11),
				-gameHeight * 12 / NO_OF_PROJECTILES, 0, PROJECTILE_SPEEDY,
				PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
		projectile13 = new Projectile(getProjectileValue(projectile12),
				-gameHeight * 13 / NO_OF_PROJECTILES, 0, PROJECTILE_SPEEDY,
				PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
		projectile14 = new Projectile(getProjectileValue(projectile13),
				-gameHeight * 14 / NO_OF_PROJECTILES, 0, PROJECTILE_SPEEDY,
				PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
		/*
		projectile15 = new Projectile(getProjectileValue(projectile14),
				-gameHeight * 15 / NO_OF_PROJECTILES, 0, PROJECTILE_SPEEDY,
				PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
		projectile16 = new Projectile(getProjectileValue(projectile15),
				-gameHeight * 16 / NO_OF_PROJECTILES, 0, PROJECTILE_SPEEDY,
				PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
		projectile17 = new Projectile(getProjectileValue(projectile16),
				-gameHeight * 17 / NO_OF_PROJECTILES, 0, PROJECTILE_SPEEDY,
				PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
		projectile18 = new Projectile(getProjectileValue(projectile17),
				-gameHeight * 18 / NO_OF_PROJECTILES, 0, PROJECTILE_SPEEDY,
				PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
		projectile19 = new Projectile(getProjectileValue(projectile18),
				-gameHeight * 19 / NO_OF_PROJECTILES, 0, PROJECTILE_SPEEDY,
				PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
				*/
		addProjectiles();
	}

	public void update(float delta) {
		for (int i = 0; i < currentArray.size(); i++) {
			currentArray.get(i).update(delta, gameHeight);

			if (currentArray.get(i).getPositionY() > gameHeight) {

				if (currentArray.get(i).isAlive()) {
					
					
					currentArray.get(i).setPositionY(-1 / currentArray.size());
					currentArray.get(i).setRecorded(false);
				

				if (i == 0) {
					currentArray.get(0).setPositionX(
							getProjectileValue(currentArray.get(currentArray
									.size() - 1)));
				} else {
					currentArray.get(i).setPositionX(
							getProjectileValue(currentArray.get(i - 1)));
					}
				}
			}

			if (currentArray.get(i).getPositionX() > alien.getBoundingCircle().x
					&& currentArray.get(i).getPositionX() < alien
							.getBoundingCircle().x
							+ alien.getBoundingCircle().width
					&& currentArray.get(i).getPositionY()
							+ currentArray.get(i).getHeight() > alien
								.getBoundingCircle().y
					&& currentArray.get(i).getPositionY() < alien
							.getBoundingCircle().y
							+ alien.getBoundingCircle().height) {
				myWorld.setFalling();
				
			}
			
			if (currentArray.get(i).getPositionY()>alien.getPosition().y + alien.getHeight() && currentArray.get(i).isRecorded() == false){
				myWorld.addProjectilesPassed();
				currentArray.get(i).setRecorded(true);
			}

		}
	}

	
	public void onRestart() {
		PROJECTILE_SPEEDY = 40;
		addProjectiles();
		for (int i = 0; i <	thermo.size(); i++) {
			// exo.get(i).setVelocityY(0);
			if (i == 0) {
				thermo.get(0).setPositionX(
						getProjectileValue(thermo.get(thermo.size() - 1)));
			} else {
				thermo.get(i).setPositionX(getProjectileValue(thermo.get(i - 1)));
			}
			thermo.get(i).setAlive(true);
			thermo.get(i).setRecorded(false);
			thermo.get(i).setPositionY(-gameHeight * i / NO_OF_PROJECTILES);

		}

	}

	private void addProjectiles() {
		
		currentArray = new ArrayList<Projectile>();
		
		tropo = new ArrayList<Projectile>();
		strato = new ArrayList<Projectile>();
		meso = new ArrayList<Projectile>();
		thermo = new ArrayList<Projectile>();
		exo = new ArrayList<Projectile>();

		tropo.add(projectile0);
		tropo.add(projectile1);
		tropo.add(projectile2);
		tropo.add(projectile3);
		tropo.add(projectile4);

		strato.add(projectile0);
		strato.add(projectile1);
		strato.add(projectile2);
		strato.add(projectile3);
		strato.add(projectile4);
		strato.add(projectile5);
		strato.add(projectile6);

		meso.add(projectile0);
		meso.add(projectile1);
		meso.add(projectile2);
		meso.add(projectile3);
		meso.add(projectile4);
		meso.add(projectile5);
		meso.add(projectile6);
		meso.add(projectile7);
		meso.add(projectile8);
		meso.add(projectile9);

		thermo.add(projectile0);
		thermo.add(projectile1);
		thermo.add(projectile2);
		thermo.add(projectile3);
		thermo.add(projectile4);
		thermo.add(projectile5);
		thermo.add(projectile6);
		thermo.add(projectile7);
		thermo.add(projectile8);
		thermo.add(projectile9);
		thermo.add(projectile10);
		thermo.add(projectile11);
		thermo.add(projectile12);
		thermo.add(projectile13);
		thermo.add(projectile14);

		exo.add(projectile0);
		exo.add(projectile1);
		exo.add(projectile2);
		exo.add(projectile3);
		exo.add(projectile4);
		exo.add(projectile5);
		exo.add(projectile6);
		exo.add(projectile7);
		exo.add(projectile8);
		/*
		exo.add(projectile9);
		exo.add(projectile10);
		exo.add(projectile11);
		exo.add(projectile12);
		exo.add(projectile13);
		exo.add(projectile14);
		exo.add(projectile15);
		exo.add(projectile16);
		exo.add(projectile17);
		exo.add(projectile18);
		exo.add(projectile19);
		*/
		currentArray = tropo;
	}

	public float getNO_OF_PROJECTILES() {
		return NO_OF_PROJECTILES;
	}

	public void setNO_OF_PROJECTILES(float nO_OF_PROJECTILES) {
		NO_OF_PROJECTILES = nO_OF_PROJECTILES;
	}

	public float getPROJECTILE_SPEEDY() {
		return PROJECTILE_SPEEDY;
	}

	public void setPROJECTILE_SPEEDY(float pROJECTILE_SPEEDY) {
		PROJECTILE_SPEEDY = pROJECTILE_SPEEDY;
	}

	public float getPROJECTILE_WIDTH() {
		return PROJECTILE_WIDTH;
	}

	public void setPROJECTILE_WIDTH(float pROJECTILE_WIDTH) {
		PROJECTILE_WIDTH = pROJECTILE_WIDTH;
	}

	public float getPROJECTILE_HEIGHT() {
		return PROJECTILE_HEIGHT;
	}

	public void setPROJECTILE_HEIGHT(float pROJECTILE_HEIGHT) {
		PROJECTILE_HEIGHT = pROJECTILE_HEIGHT;
	}

	public ArrayList<Projectile> getTropo() {
		return tropo;
	}

	public ArrayList<Projectile> getStrato() {
		return strato;
	}

	public ArrayList<Projectile> getMeso() {
		return meso;
	}

	public ArrayList<Projectile> getThermo() {
		return thermo;
	}

	public ArrayList<Projectile> getExo() {
		return exo;
	}

	public ArrayList<Projectile> getCurrentArray() {
		return currentArray;
	}

	public void setCurrentArray(ArrayList<Projectile> currentArray) {
		this.currentArray = currentArray;
	}

	public float getProjectileValue(Projectile projectile) {
		float newValue = MathUtils.random(gameWidth-PROJECTILE_WIDTH);
		while (Math.abs(newValue - projectile.getPositionX()) < gameWidth * 1 / 4) {
			newValue = MathUtils.random(gameWidth-PROJECTILE_WIDTH);
		}
		return newValue;

	}

	public void reset(ArrayList<Projectile> projectiles, int newSum, int newSpeed) {

		for (int i = 0; i < projectiles.size(); i++) {
			if (i == 0) {
				projectiles.get(0)
						.setPositionX(
								getProjectileValue(projectiles.get(projectiles
										.size() - 1)));
			} else {
				projectiles.get(i).setPositionX(
						getProjectileValue(projectiles.get(i - 1)));
			}
			projectiles.get(i).setPositionY(-gameHeight * i / newSum);
			projectiles.get(i).setAlive(true);
			projectiles.get(i).setRecorded(false);
			projectiles.get(i).setVelocityY(newSpeed);
			PROJECTILE_SPEEDY = newSpeed;
		}
	}

	public void finishOff(ArrayList<Projectile> projectiles) {
		for (int i = projectiles.size() - 1; i >= 0; i--) {
			projectiles.get(i).setAlive(false);
			if (projectiles.get(i).getPositionY() > gameHeight) {
				projectiles.remove(i);
			}
		}

	}
	
	public void fallProjectiles(float delta){
		
			for (int i = currentArray.size() - 1; i >= 0; i--) {
				
				currentArray.get(i).setAlive(false);
				currentArray.get(i).setVelocityY(-200);
				currentArray.get(i).update(delta, gameHeight);
				
				if (currentArray.get(i).getPositionY() + PROJECTILE_HEIGHT < 0) {
					currentArray.remove(i);
				}
			}
	}

}

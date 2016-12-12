package com.aaditya.gameobjects;

import com.aaditya.eehelpers.AssetLoader;
import com.aaditya.gameworld.GameWorld;
import com.aaditya.gameworld.GameWorld.GameState;
//import com.aaditya.gameworld.GameWorld.Region;
import com.aaditya.ui.CircleButton;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RegionHandler {

	private GameWorld myWorld;
	private Region currentRegion;
	private String currentRegionString, nextRegionString;
	private TextureRegion currentBanner, currentBg;
	private ScrollHandler scroller;
	private Projectiles projectileHandler;
	private Alien alien;
	private CircleButton music;
	private float alienSpeed;
	private float distanceLeft = 12;
	private float distance;
	private boolean canReset = true;
	private boolean tropoUpdated = false;
	private boolean stratoUpdated = false;
	private boolean mesoUpdated = false;
	private boolean thermoUpdated = false;
	private boolean exoUpdated = false;
	private boolean spaceUpdated = false;
	float gameWidth = AssetLoader.gameWidth;
	float gameHeight = AssetLoader.gameHeight;
	
	public enum Region {
		TROPO, MESO, STRATO, THERMO, EXO, SPACE
	}
	
	public RegionHandler(GameWorld gameWorld) {
		this.myWorld = gameWorld;
		scroller = new ScrollHandler(this);
		projectileHandler = myWorld.getProjectileHandler();
		currentRegionString = "TROPOSPHERE";
		nextRegionString = "STRATOSPHERE";
		currentBanner = AssetLoader.tropoTr;
		alien = myWorld.getAlien();
		music = myWorld.getMenuButtons().get(0);
		currentRegion = Region.TROPO;
	}
	
	public void update(float delta) {
		
		distance = myWorld.getDistance();
		
		
	

		
		
		if (currentRegion == Region.TROPO || currentRegion == Region.STRATO || currentRegion == Region.MESO) {
			scroller.updateClouds(delta);
			
			if (currentRegion == Region.TROPO) {
				currentBanner = AssetLoader.tropoTr;
				currentBg = AssetLoader.tropoBg;
				currentRegionString = "TROPOSPHERE";
				nextRegionString = "STRATOSPHERE";
				alienSpeed = (float) 874.9863;
				distanceLeft = (12 - distance);
			}

			if (currentRegion == Region.STRATO) {
				distanceLeft = (50 - distance);
				currentBanner = AssetLoader.stratoTr;
				currentBg = AssetLoader.stratoBg;
				currentRegionString = "STRATOSPHERE";
				nextRegionString = "MESOSPHERE";
				alienSpeed = (float) 2770.7900;
			}

			if (currentRegion == Region.MESO) {
				 distanceLeft = (80 - distance);
				currentBanner = AssetLoader.mesoTr;
				currentBg = AssetLoader.mesoBg;
				currentRegionString = "MESOSPHERE";
				nextRegionString = "THERMOSPHERE";
				alienSpeed = (float) 1093.74291;
			}
		}

		if (currentRegion == Region.THERMO || currentRegion == Region.EXO || currentRegion == Region.SPACE) {
			scroller.updateStars(delta);
			
			if (currentRegion == Region.THERMO) {
				 distanceLeft = (700 - distance);
				currentBanner = AssetLoader.thermoTr;
				currentBg = AssetLoader.thermoBg;
				currentRegionString = "THERMOSPHERE";
				nextRegionString = "EXOSPHERE";
				alienSpeed = (float) 11301.906741;
			}

			if (currentRegion == Region.EXO) {
				 distanceLeft = (10000 - distance);
				currentBanner = AssetLoader.exoTr;
				currentBg = AssetLoader.exoBg;
				currentRegionString = "EXOSPHERE";
				nextRegionString = "SPACE";
				alienSpeed = (float) 169528.6011156;
			}

			if (currentRegion == Region.SPACE) {
				distanceLeft = (12629 - distance);
				nextRegionString = "WORMHOLE";
				alienSpeed = (float) 2629000/((gameHeight*25/32+alien.getHeight())/25);
			}

		}
		
		if (distance >= 0 && distance < 0.5) {
			currentRegion = Region.TROPO;

			if (canReset) {
				AssetLoader.fullSound.setVolume(1);
				AssetLoader.menuTheme.pause();
				if (music.isPressed()) {
					AssetLoader.fullSound.play();

					AssetLoader.menuTheme.setPosition(0);
				}
				projectileHandler.reset(projectileHandler.getTropo(), 5, 40);
				canReset = !canReset;
				projectileHandler.setCurrentArray(projectileHandler.getTropo());
			}

		}
		if (distance > (12.0 - ((gameHeight * 2) / 80) * (alienSpeed / 1000)) && tropoUpdated == false
				|| distance > (12.0 - ((gameHeight * 2) / 80) * (alienSpeed / 1000))
						&& projectileHandler.getTropo().size() > 0) {
			for (int i = 0; i < scroller.getBackgrounds().size(); i++) {
				scroller.getBackgrounds().get(i).setSpeedY(80);
			}
			scroller.updateTropo(delta);
			projectileHandler.finishOff(projectileHandler.getTropo());
			if (tropoUpdated == true) {
				currentRegion = Region.STRATO;

				if (!canReset) {
					projectileHandler.reset(projectileHandler.getStrato(), 7, 50);
					canReset = !canReset;
					projectileHandler.setCurrentArray(projectileHandler.getStrato());
					if (myWorld.getTouches() == 0){
						myWorld.getGame().actionResolver.unlockAchievementGPGS("CgkIm7vkra8UEAIQCg");
					}

				}
			}

		}
		if (distance > 50.0 - ((gameHeight * 2) / 100) * (alienSpeed / 1000) && stratoUpdated == false
				|| distance > 50.0 - ((gameHeight * 2) / 100) * (alienSpeed / 1000)
						&& projectileHandler.getStrato().size() > 0) {
			for (int i = 0; i < scroller.getBackgrounds().size(); i++) {
				scroller.getBackgrounds().get(i).setSpeedY(100);
			}
			scroller.updateStrato(delta);
			projectileHandler.finishOff(projectileHandler.getStrato());
			if (stratoUpdated == true) {
				currentRegion = Region.MESO;

				if (canReset) {
					projectileHandler.reset(projectileHandler.getMeso(), 10, 60);
					canReset = !canReset;
					projectileHandler.setCurrentArray(projectileHandler.getMeso());
					
				}
			}
		}
		if (distance > 80.0 - ((gameHeight * 2) / 110) * (alienSpeed / 1000) && mesoUpdated == false
				|| distance > 80.0 - ((gameHeight * 2) / 110) * (alienSpeed / 1000)
						&& projectileHandler.getMeso().size() > 0) {
			for (int i = 0; i < scroller.getBackgrounds().size(); i++) {
				scroller.getBackgrounds().get(i).setSpeedY(110);
			}

			scroller.updateMeso(delta);
			projectileHandler.finishOff(projectileHandler.getMeso());
			if (mesoUpdated == true) {
				currentRegion = Region.THERMO;

				if (!canReset) {
					projectileHandler.reset(projectileHandler.getThermo(), 15, 70);
					canReset = !canReset;
					projectileHandler.setCurrentArray(projectileHandler.getThermo());
					if (myWorld.getAlien().getHasTouchedSides() == false){
						myWorld.getGame().actionResolver.unlockAchievementGPGS("CgkIm7vkra8UEAIQEQ");
					}
					
				}
			}

		}
		if (distance > 700.0 - ((gameHeight * 2) / 130) * (alienSpeed / 1000) && thermoUpdated == false
				|| distance > 700.0 - ((gameHeight * 2) / 130) * (alienSpeed / 1000)
						&& projectileHandler.getThermo().size() > 0) {
			for (int i = 0; i < scroller.getBackgrounds().size(); i++) {
				scroller.getBackgrounds().get(i).setSpeedY(130);
			}

			scroller.updateThermo(delta);
			projectileHandler.finishOff(projectileHandler.getThermo());
			if (thermoUpdated == true) {
				currentRegion = Region.EXO;

				if (canReset) {
					projectileHandler.reset(projectileHandler.getExo(), 9, 140);
					canReset = !canReset;
					projectileHandler.setCurrentArray(projectileHandler.getExo());
					
				}
			}
		}

		if (distance > 10000.0 - (gameHeight * 2 / 140) * (alienSpeed / 1000) && exoUpdated == false
				|| distance > 10000.0 - ((gameHeight * 2) / 140) * (alienSpeed / 1000)
						&& projectileHandler.getExo().size() > 0) {

			for (int i = 0; i < scroller.getBackgrounds().size(); i++) {
				scroller.getBackgrounds().get(i).setSpeedY(140);
			}
			scroller.updateExo(delta);
			projectileHandler.finishOff(projectileHandler.getExo());
			if (exoUpdated == true) {
				currentRegion = Region.SPACE;

			}
		}

		if (currentRegion == Region.SPACE) {
			alien.setVelocityY(-25);
			for (int i = 0; i < scroller.getBackgrounds().size(); i++) {
				scroller.getBackgrounds().get(i).setSpeedY(25);
			}
			scroller.updateSpace(delta);
			if (alien.getPosition().y + alien.getHeight() < 0) {
				myWorld.setCurrentState(GameState.GAMEWON);
			}
		}

	}
	
	public void updateFalling(){
		
		switch (currentRegion) {
		case TROPO:
			AssetLoader.addDeathsInRegion("deathsInTroposphere");
			myWorld.setDeathsInRegion(AssetLoader.getDeathsInRegion("deathsInTroposphere"));
			break;
		case STRATO:
			AssetLoader.addDeathsInRegion("deathsInStratosphere");
			myWorld.setDeathsInRegion(AssetLoader.getDeathsInRegion("deathsInStratosphere"));
			break;
		case MESO:
			AssetLoader.addDeathsInRegion("deathsInMesosphere");
			myWorld.setDeathsInRegion(AssetLoader.getDeathsInRegion("deathsInMesosphere"));
			break;
		case THERMO:
			AssetLoader.addDeathsInRegion("deathsInThermosphere");
			myWorld.setDeathsInRegion(AssetLoader.getDeathsInRegion("deathsInThermosphere"));
			break;
		case EXO:
			AssetLoader.addDeathsInRegion("deathsInExosphere");
			myWorld.setDeathsInRegion(AssetLoader.getDeathsInRegion("deathsInExosphere"));
			break;
		default:
			break;
			

		}

	}

	public void onRestart() {
		currentRegionString = "TROPOSPHERE";
		nextRegionString = "STRATOSPHERE";
		tropoUpdated = false;
		stratoUpdated = false;
		mesoUpdated = false;
		thermoUpdated = false;
		exoUpdated = false;
		spaceUpdated = false;
		distanceLeft = (12);
		canReset = true;
		alienSpeed = (float) 874.9863;
		currentRegion = Region.TROPO;
	}

	public TextureRegion getCurrentBanner() {
		return currentBanner;
	}

	public TextureRegion getCurrentBg() {
		return currentBg;
	}

	public String getCurrentRegionString() {
		return this.currentRegionString;
	}

	public void setCurrentRegionString(String currentRegionString) {
		this.currentRegionString = currentRegionString;
	}

	public String getNextRegionString() {
		return this.nextRegionString;
	}

	public void setNextRegionString(String nextRegionString) {
		this.nextRegionString = nextRegionString;
	}

	public void setTropoUpdated(boolean tropoUpdated) {
		this.tropoUpdated = tropoUpdated;
	}

	public void setStrapoUpdated(boolean stratoUpdated) {
		this.stratoUpdated = stratoUpdated;
	}

	public void setMesoUpdated(boolean mesoUpdated) {
		this.mesoUpdated = mesoUpdated;
	}

	public void setThermoUpdated(boolean thermoUpdated) {
		this.thermoUpdated = thermoUpdated;
	}

	public void setExoUpdated(boolean exoUpdated) {
		this.exoUpdated = exoUpdated;
	}
	
	public void setSpaceUpdated(boolean spaceUpdated) {
		this.spaceUpdated = spaceUpdated;
	}
	
	public float getDistanceLeft() {
		return this.distanceLeft;
	}
	
	public float getAlienSpeed(){
		return this.alienSpeed;
	}
	
	public Region getCurrentRegion() {
		return this.currentRegion;
	}
	
	public ScrollHandler getScroller() {
		return scroller;
	}
}

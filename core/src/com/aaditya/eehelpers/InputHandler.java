package com.aaditya.eehelpers;

import java.util.ArrayList;

import com.aaditya.gameobjects.Alien;
import com.aaditya.gameobjects.RegionHandler.Region;
import com.aaditya.gameworld.GameWorld;
import com.aaditya.ui.CircleButton;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.List;

public class InputHandler implements InputProcessor {

	private float scaleFactorX;
	private float scaleFactorY;

	float gameWidth = AssetLoader.gameWidth;
	float gameHeight = AssetLoader.gameHeight;

	private Alien myAlien;
	private GameWorld myWorld;
	private CircleButton sounds, music, leaderboards, achievements, share;
	private ArrayList<CircleButton> menuButtons, endScreenButtons;

	public InputHandler(GameWorld myWorld, float scaleFactorX, float scaleFactorY) {
		this.scaleFactorX = scaleFactorX;
		this.scaleFactorY = scaleFactorY;
		this.myWorld = myWorld;
		myAlien = myWorld.getAlien();
		menuButtons = myWorld.getMenuButtons();
		endScreenButtons = myWorld.getEndScreenButtons();
		sounds = menuButtons.get(1);
		music = menuButtons.get(0);
		//if (myWorld.getGame().actionResolver.getSignedInGPGS()) {
			leaderboards = menuButtons.get(2);
		achievements = menuButtons.get(3);
		share = endScreenButtons.get(0);
		//}

	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			if (myAlien.getVelocityX() >= 0) {
				if (sounds.isPressed() == true) {
					if (myWorld.getRegionHandler().getCurrentRegion() != Region.SPACE) {
						AssetLoader.tapLeft.play();
					}
				}
			}
			myWorld.addTouches();
			myAlien.setVelocityX(-45);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			if (myAlien.getVelocityX() <= 0) {
				if (sounds.isPressed() == true) {
					if (myWorld.getRegionHandler().getCurrentRegion() != Region.SPACE) {
						AssetLoader.tapRight.play();
					}
				}
			}
			myWorld.addTouches();
			myAlien.setVelocityX(45);
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);
		if (myWorld.isReady()) {

			if (leaderboards.isTouchDown(screenX, screenY)){

					myWorld.getGame().actionResolver.showLeaderboardsGPGS();

			}

			else if (achievements.isTouchDown(screenX, screenY)){
				myWorld.getGame().actionResolver.getAchievementsGPGS();
			}

			else if (sounds.isTouchDown(screenX, screenY) && sounds.isPressed() == false) {
				sounds.setPressed(true);

			} else if (sounds.isTouchDown(screenX, screenY) && sounds.isPressed() == true) {
				sounds.setPressed(false);

			}

			else if (music.isTouchDown(screenX, screenY) && music.isPressed() == false) {
				music.setPressed(true);
			} else if (music.isTouchDown(screenX, screenY) && music.isPressed() == true) {
				music.setPressed(false);
				if (AssetLoader.menuTheme.isPlaying()) {
					AssetLoader.menuTheme.pause();
				}

			}

			else {
				myWorld.start();
				AssetLoader.addAttemps();
				
			}
		}

		else if (myWorld.isRunning()) {

			if (screenX > gameWidth / 2) {
				if (myAlien.getVelocityX() <= 0) {
					if (sounds.isPressed()) {

						if (myWorld.getRegionHandler().getCurrentRegion() != Region.SPACE) {
							AssetLoader.tapRight.play();
						}
					}
				}
				myWorld.addTouches();
				myAlien.setVelocityX(45);
			}

			if (screenX < gameWidth / 2) {
				if (myAlien.getVelocityX() >= 0) {
					if (sounds.isPressed()) {
						if (myWorld.getRegionHandler().getCurrentRegion() != Region.SPACE) {
							AssetLoader.tapLeft.play();
						}
					}
				}
				myWorld.addTouches();
				myAlien.setVelocityX(-45);
			}
		}

		else if (myWorld.isFalling()) {

		}

		else if (myWorld.isGameOver()) {
			// Reset all variables, go to GameState.READY
			if (share.isTouchDown(screenX, screenY)){
				myWorld.getGame().actionResolver.shareScoreGPGS();
			}
			else{
				myWorld.restart();
			}

		}

		else if (myWorld.isGameWon()) {
			myWorld.addEndScreenControl();
			if (myWorld.getEndScreenControl() == 12) {
				myWorld.restart();
			}
		}

		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		// myAlien.setVelocityX(0);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	private int scaleX(int screenX) {
		return (int) (screenX / scaleFactorX);
	}

	private int scaleY(int screenY) {
		return (int) (screenY / scaleFactorY);
	}

}

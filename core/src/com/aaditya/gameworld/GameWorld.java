package com.aaditya.gameworld;

import java.util.ArrayList;
import com.aaditya.eehelpers.AssetLoader;
import com.aaditya.escapeearth.EEGame;
import com.aaditya.gameobjects.Alien;
import com.aaditya.gameobjects.Projectiles;
import com.aaditya.gameobjects.RegionHandler;
import com.aaditya.gameobjects.RegionHandler.Region;
import com.aaditya.gameservices.ActionResolver;
import com.aaditya.gameobjects.ScrollHandler;
import com.aaditya.ui.CircleButton;
import com.badlogic.gdx.Gdx;

public class GameWorld {
	private Alien alien;
	private ScrollHandler scroller;
	private Projectiles projectileHandler;
	float gameWidth = AssetLoader.gameWidth;
	float gameHeight = AssetLoader.gameHeight;//screenHeight / (screenWidth / gameWidth);
	private float alienSpeed;
	private ArrayList<CircleButton> menuButtons, endScreenButtons;
	private CircleButton sounds, music, leaderboards, achievements, share;
	private RegionHandler regionHandler;
	private int deathsInRegion, touches, projectilesPassed;
	private float calcDistance = 0;
	private float distance = 0;
	private int endScreenControl = 0;
	private EEGame game;
	private boolean canConnect = true;

	private Region currentRegion;

	public enum GameState {

		READY, RUNNING, FALLDOWN, GAMEOVER, GAMEWON

	}

	private GameState currentState;

	public GameWorld(EEGame game) {
		alien = new Alien(gameWidth / 2 - 11, gameHeight * 25 / 32, 22, 16);
		projectileHandler = new Projectiles(this);
		currentState = GameState.READY;
		menuButtons = new ArrayList<CircleButton>();
		endScreenButtons = new ArrayList<CircleButton>();
		sounds = new CircleButton(gameWidth * 12 / 16, gameHeight * 1 / 50, 16, 16, AssetLoader.soundsOff,
				AssetLoader.soundsOn);
		music = new CircleButton(gameWidth * 14 / 16, gameHeight * 1 / 50, 16, 16, AssetLoader.musicOff,
				AssetLoader.musicOn);
		leaderboards = new CircleButton(gameWidth * 1/20, gameHeight*1/50, 16, 16, AssetLoader.leaderboards, AssetLoader.leaderboards);
		achievements = new CircleButton(gameWidth*7/40, gameHeight*1/50, 16, 16, AssetLoader.achievements, AssetLoader.achievements);
		share = new CircleButton(gameWidth*1/20, gameHeight*46/50, 16, 16, AssetLoader.share, AssetLoader.share);

		menuButtons.add(music);
		menuButtons.add(sounds);
		//if (game.actionResolver.getSignedInGPGS()){
		menuButtons.add(leaderboards);
		menuButtons.add(achievements);
		endScreenButtons.add(share);
		regionHandler = new RegionHandler(this);
		scroller = regionHandler.getScroller();
		currentRegion = regionHandler.getCurrentRegion();



		this.game = game;
	}

	public void update(float delta) {

		switch (currentState) {
		case READY:
			updateReady(delta);
			break;

		case RUNNING:
		default:
			updateRunning(delta);
			break;

		case FALLDOWN:
			updateFalling(delta);
			break;

		case GAMEOVER:
			updateGameOver();
			break;

		case GAMEWON:
			updateGameWon();
			break;
		}

	}

	private void updateReady(float delta) {
		if (music.isPressed()) {
			AssetLoader.menuTheme.play();
		}

	}

	public void updateRunning(float delta) {
		Gdx.app.log("GameWorld", "update");
		alienSpeed = regionHandler.getAlienSpeed();
		calcDistance += (delta * alienSpeed);
		distance = calcDistance / 1000;
		alien.update(delta);
		regionHandler.update(delta);
		projectileHandler.update(delta);
	}

	private void updateFalling(float delta) {
		AssetLoader.fullSound.pause();
		if (music.isPressed()) {
			AssetLoader.fall.play();
		}
		projectileHandler.fallProjectiles(delta);
		// projectileHandler.update(delta);

		scroller.fallBack(delta);
		regionHandler.updateFalling();

		if (distance > AssetLoader.getHighScore()) {
			AssetLoader.setHighScore(distance);
			AssetLoader.setHighScoreRegion(regionHandler.getCurrentRegionString());

		}

		if (projectileHandler.getCurrentArray().size() == 0 && scroller.getTropoBg().getY() <= 0) {
			currentState = GameState.GAMEOVER;
		}

	}

	private void updateGameOver() {
		if (music.isPressed()) {

			AssetLoader.menuTheme.play();
		}
		AssetLoader.fall.pause();
		currentRegion = regionHandler.getCurrentRegion();

		if (canConnect) {
			//if (game.actionResolver.getSignedInGPGS()) {
				switch (currentRegion) {
				case TROPO:
					game.actionResolver.unlockAchievementGPGS("CgkIm7vkra8UEAIQAQ");
					
					break;
				case STRATO:
					game.actionResolver.unlockAchievementGPGS("CgkIm7vkra8UEAIQAg");
					break;
				case MESO:
					game.actionResolver.unlockAchievementGPGS("CgkIm7vkra8UEAIQAw");
					break;
				case THERMO:
					game.actionResolver.unlockAchievementGPGS("CgkIm7vkra8UEAIQBA");
					break;
				case EXO:
					game.actionResolver.unlockAchievementGPGS("CgkIm7vkra8UEAIQBQ");
					if (distance >9500){
						game.actionResolver.unlockAchievementGPGS("CgkIm7vkra8UEAIQEA");
					}
					break;
				default:
					break;

				}
				
				switch (AssetLoader.getTotalAttempts()) {
				case 100:
					game.actionResolver.unlockAchievementGPGS("CgkIm7vkra8UEAIQDQ");
					break;
				case 500:
					game.actionResolver.unlockAchievementGPGS("CgkIm7vkra8UEAIQDg");
					break;
				case 1000:
					game.actionResolver.unlockAchievementGPGS("CgkIm7vkra8UEAIQDw");
					break;
				default:
					break;
				}

				//if (distance == AssetLoader.getHighScore()) {

					game.actionResolver.submitScoreGPGS((long) (distance * 10));

				//}
		//	}
			canConnect = false;
		}

	}

	private void updateGameWon() {
		
		if(canConnect){
			game.actionResolver.submitScoreGPGS(126290 * AssetLoader.getAlienNumber());
			game.actionResolver.unlockAchievementGPGS("CgkIm7vkra8UEAIQCQ");
			if(alien.getHasTouchedSides() == false){
				game.actionResolver.unlockAchievementGPGS("CgkIm7vkra8UEAIQDA");
			}
			canConnect = false;
		}
		

		AssetLoader.setHighScore(0);
		AssetLoader.resetAttempts();
		AssetLoader.setHighScoreRegion("TROPOSPHERE");
		if (music.isPressed()) {
			AssetLoader.menuTheme.play();
		}
		
	}

	public void restart() {

		currentState = GameState.READY;
		touches = 0;
		calcDistance = 0;
		distance = 0;
		projectilesPassed = 0;
		endScreenControl = 0;
		alien.onRestart(alien.getPosition().x, gameHeight * 25 / 32);
		projectileHandler.onRestart();
		scroller.onRestart();
		regionHandler.onRestart();
		// alienSpeed = 1000;
		AssetLoader.fullSound.setPosition(0);
		AssetLoader.fall.setPosition(0);
		AssetLoader.canAdd = true;
		AssetLoader.canAdd2 = true;
		AssetLoader.canAdd3 = true;
		canConnect = true;

	}

	public Alien getAlien() {
		return alien;
	}

	public int getProjectilesPassed() {
		return projectilesPassed;
	}

	public void addProjectilesPassed() {
		this.projectilesPassed += 1;
	}

	public float getDistance() {
		return distance;
	}

	public Projectiles getProjectileHandler() {
		return projectileHandler;
	}

	public void setCurrentState(GameState gameState) {
		this.currentState = gameState;
	}

	public boolean isReady() {
		return currentState == GameState.READY;
	}

	public void start() {
		currentState = GameState.RUNNING;
	}

	public void gameOver() {
		this.currentState = GameState.GAMEOVER;
	}

	public void setFalling() {
		this.currentState = GameState.FALLDOWN;
	}

	public boolean isGameOver() {
		return currentState == GameState.GAMEOVER;
	}

	public boolean isRunning() {
		return currentState == GameState.RUNNING;
	}

	public boolean isFalling() {
		return currentState == GameState.FALLDOWN;
	}

	public boolean isGameWon() {
		return currentState == GameState.GAMEWON;
	}

	public int getDeathsInRegion() {
		return this.deathsInRegion;
	}

	public void setDeathsInRegion(int deathsInRegion) {
		this.deathsInRegion = deathsInRegion;
	}

	public int getTouches() {
		return this.touches;
	}

	public void addTouches() {
		this.touches += 1;
		if (touches == 500){
			game.actionResolver.unlockAchievementGPGS("CgkIm7vkra8UEAIQCw");
		}
	}

	public ArrayList<CircleButton> getMenuButtons() {
		return menuButtons;
	}

	public ArrayList<CircleButton> getEndScreenButtons() {
		return endScreenButtons;
	}

	public RegionHandler getRegionHandler() {
		return this.regionHandler;
	}

	public void addEndScreenControl() {
		endScreenControl += 1;
	}

	public int getEndScreenControl() {
		return endScreenControl;
	}
	
	public EEGame getGame(){
		return this.game;
	}

}

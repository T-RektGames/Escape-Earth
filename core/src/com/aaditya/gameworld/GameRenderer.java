package com.aaditya.gameworld;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import com.aaditya.eehelpers.AssetLoader;
import com.aaditya.gameobjects.Alien;
import com.aaditya.gameobjects.Projectile;
import com.aaditya.gameobjects.RegionHandler;
import com.aaditya.gameobjects.RegionHandler.Region;
import com.aaditya.gameobjects.ScrollHandler;

import com.aaditya.ui.CircleButton;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import javafx.scene.control.MenuButton;

public class GameRenderer {

	private GameWorld myWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	private ScrollHandler scroller;
	private RegionHandler regionHandler;

	private SpriteBatch batcher;

	private int midPointY, endScreenControl;
	private Alien alien;
	private ArrayList<Projectile> projectiles;
	private ArrayList<CircleButton> menuButtons, endScreenButtons;

	private TextureRegion currentCloud;

	private GlyphLayout launchReport, congratulation, youHave, theMotherspace, theFirst, butThe, thereAre, tapTo,
			aTrekt, codeBy, graphicsAnd, songTitle, ascentOf, attemptNo, altitudeReached, score, region, recordAltitude, recordScore, alienNo;

	String  distanceTo, distanceLeft, altitude, distance, highscore, distanceToSpace;

	NumberFormat format1DP, format0DP;
	//Color blue = new Color(Color.rgb888(95, 193, 213));

	float gameWidth = AssetLoader.gameWidth;
	float gameHeight = AssetLoader.gameHeight;

	public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
		myWorld = world;

		alien = myWorld.getAlien();
		regionHandler = myWorld.getRegionHandler();
		scroller = myWorld.getRegionHandler().getScroller();
		//this.gameHeight = gameHeight;
		//this.midPointY = midPointY;

		cam = new OrthographicCamera();
		cam.setToOrtho(true, gameWidth, gameHeight);

		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);

		currentCloud = new TextureRegion();
		//blue = new Color(95, 193, 213);
		// menuButtons = (InputHandler)Gdx.input.getInputProcessor().
		menuButtons = myWorld.getMenuButtons();
		endScreenButtons = myWorld.getEndScreenButtons();

		format1DP = new DecimalFormat("#0.0");
		format0DP = new DecimalFormat("#0");

		launchReport = new GlyphLayout();
		launchReport.setText(AssetLoader.OCRAMedium, "[ LAUNCH REPORT ]");

		alienNo = new GlyphLayout();
		
		
		recordAltitude = new GlyphLayout();
		recordAltitude.setText(AssetLoader.munroSmallMedium, "RECORD ALTITUDE:");
		
		altitudeReached = new GlyphLayout();
		altitudeReached.setText(AssetLoader.munroSmallMedium, "ALTITUDE REACHED:");
		
		score = new GlyphLayout();
		
		region = new GlyphLayout();
		
		recordScore = new GlyphLayout();
		
		attemptNo = new GlyphLayout();
		
		
		congratulation = new GlyphLayout();
		congratulation.setText(AssetLoader.OCRABig, "CONGRATULATION");

		youHave = new GlyphLayout();
		youHave.setText(AssetLoader.OCRASmall, "YOU HAVE ESCAPED FROM EARTH");

		theMotherspace = new GlyphLayout();
		theMotherspace.setText(AssetLoader.OCRASmall, "THE MOTHERSPACE AWAITS YOUR RETURN");

		theFirst = new GlyphLayout();
		theFirst.setText(AssetLoader.OCRASmall, "THE FIRST INVASION HAS FAILED");

		butThe = new GlyphLayout();
		butThe.setText(AssetLoader.OCRASmall, "BUT THE WAR IS NOT OVER");

		thereAre = new GlyphLayout();
		thereAre.setText(AssetLoader.OCRASmall, "THERE ARE STILL OTHERS WHO MUST");

		tapTo = new GlyphLayout();
		tapTo.setText(AssetLoader.OCRASmall, "[ TAP TO CONTINUE ]");

		aTrekt = new GlyphLayout();
		aTrekt.setText(AssetLoader.OCRASmall, "A T-REKT PRODUCTION");

		codeBy = new GlyphLayout();
		codeBy.setText(AssetLoader.OCRASmall, "CODE BY AADITYA");

		graphicsAnd = new GlyphLayout();
		graphicsAnd.setText(AssetLoader.OCRASmall, "GRAPHICS AND SOUND BY ETHAN");

		songTitle = new GlyphLayout();
		songTitle.setText(AssetLoader.OCRASmall, "SONG TITLE:");

		ascentOf = new GlyphLayout();
		ascentOf.setText(AssetLoader.OCRASmall, "ASCENT OF THE MOUNTAIN KING");
	}

	private void drawBanner() {
		if (regionHandler.getCurrentRegion() != Region.SPACE) {
			batcher.draw(AssetLoader.youAre, 0, 1, gameWidth, 80 / (float) (720 / 136));
			batcher.draw(regionHandler.getCurrentBanner(), 0, (80 / (float) (720 / 136)) - 5, 136,
					160 / (float) (720 / 136));
		}
	}

	private void drawBg() {
		batcher.draw(AssetLoader.tropoBg, scroller.getTropoBg().getX(), scroller.getTropoBg().getY(), gameWidth,
				gameHeight + 10);
		batcher.draw(AssetLoader.tropoStrato, scroller.getTropoStrato().getX(), scroller.getTropoStrato().getY(),
				gameWidth, gameHeight + 1);
		batcher.draw(AssetLoader.stratoBg, scroller.getStratoBg().getX(), scroller.getStratoBg().getY(), gameWidth,
				gameHeight + 1);
		batcher.draw(AssetLoader.stratoMeso, scroller.getStratoMeso().getX(), scroller.getStratoMeso().getY(),
				gameWidth, gameHeight + 1);
		batcher.draw(AssetLoader.mesoBg, scroller.getMesoBg().getX(), scroller.getMesoBg().getY(), gameWidth,
				gameHeight + 1);
		batcher.draw(AssetLoader.mesoThermo, scroller.getMesoThermo().getX(), scroller.getMesoThermo().getY(),
				gameWidth, gameHeight + 1);
		batcher.draw(AssetLoader.thermoBg, scroller.getThermoBg().getX(), scroller.getThermoBg().getY(), gameWidth,
				gameHeight + 1);
		batcher.draw(AssetLoader.thermoExo, scroller.getThermoExo().getX(), scroller.getThermoExo().getY(), gameWidth,
				gameHeight + 1);
		batcher.draw(AssetLoader.exoBg, scroller.getExoBg().getX(), scroller.getExoBg().getY(), gameWidth,
				gameHeight + 1);
		batcher.draw(AssetLoader.exoBg, scroller.getExoSpace().getX(), scroller.getExoSpace().getY(), gameWidth,
				gameHeight + 1);
		batcher.draw(AssetLoader.exoBg, scroller.getSpaceBg().getX(), scroller.getSpaceBg().getY(), gameWidth,
				gameHeight + 1);

	}

	private void drawGameWonScreen() {
		endScreenControl = myWorld.getEndScreenControl();
		if (endScreenControl < 5) {
			if (endScreenControl > -1) {
				AssetLoader.OCRABig.draw(batcher, congratulation, (gameWidth - congratulation.width) / 2,
						gameHeight * 3 / 20);
			}

			if (endScreenControl > 0) {
				AssetLoader.OCRASmall.draw(batcher, youHave, (gameWidth - youHave.width) / 2, gameHeight * 7 / 20);
			}

			if (endScreenControl > 1) {
				AssetLoader.OCRASmall.draw(batcher, theMotherspace, (gameWidth - theMotherspace.width) / 2,
						gameHeight * 9 / 20);
			}

			if (endScreenControl > 2) {
				AssetLoader.OCRASmall.draw(batcher, theFirst, (gameWidth - theFirst.width) / 2, gameHeight * 11 / 20);
			}

			if (endScreenControl > 3) {
				AssetLoader.OCRASmall.draw(batcher, butThe, (gameWidth - butThe.width) / 2, gameHeight * 13 / 20);
			}
		}

		if (endScreenControl < 12) {

			if (endScreenControl > 4) {
				AssetLoader.OCRASmall.draw(batcher, thereAre, (gameWidth - thereAre.width) / 2, gameHeight * 3 / 20);
			}

			if (endScreenControl > 5) {
				batcher.draw(AssetLoader.escapeEarth, 0, gameHeight * 1 / 4, gameWidth, 320 / (720 / gameWidth));
			}

			if (endScreenControl > 6) {
				AssetLoader.OCRASmall.draw(batcher, aTrekt, (gameWidth - aTrekt.width) / 2, gameHeight * 11 / 20);
			}

			if (endScreenControl > 7) {
				AssetLoader.OCRASmall.draw(batcher, codeBy, (gameWidth - codeBy.width) / 2, gameHeight * 13 / 20);
			}

			if (endScreenControl > 8) {
				AssetLoader.OCRASmall.draw(batcher, graphicsAnd, (gameWidth - graphicsAnd.width) / 2,
						gameHeight * 15 / 20);
			}
			if (endScreenControl > 9) {
				AssetLoader.OCRASmall.draw(batcher, songTitle, (gameWidth - songTitle.width) / 2, gameHeight * 17 / 20);
			}

			if (endScreenControl > 10) {
				AssetLoader.OCRASmall.draw(batcher, ascentOf, (gameWidth - ascentOf.width) / 2, gameHeight * 18 / 20);
			}
		}

	}

	private void drawWormhole() {
		batcher.draw(AssetLoader.spaceShip, scroller.getSpaceShipBg().getX(), scroller.getSpaceShipBg().getY(),
				gameWidth, gameWidth / 3);
	}

	private void drawProjectiles() {
		for (int i = 0; i < projectiles.size(); i++) {
			batcher.draw(AssetLoader.laserTr, projectiles.get(i).getPositionX(), projectiles.get(i).getPositionY(),
					projectiles.get(i).getWidth(), projectiles.get(i).getHeight());
		}

	}

	private void drawAlien(float runTime) {
		batcher.draw(AssetLoader.alienAnimation.getKeyFrame(runTime), alien.getPosition().x, alien.getPosition().y,
				alien.getWidth(), alien.getHeight());
	}

	private void drawScore() {

		// score = String.valueOf(myWorld.getScore());
		distance = format1DP.format(myWorld.getDistance());
		altitude = "ALTITUDE";
		distanceTo = "DISTANCE TO";
		distanceLeft = format0DP.format(regionHandler.getDistanceLeft());
		// AssetLoader.font.draw(batcher, score, (136 / 2) - (3 * score.length()
		// - 1), gameHeight/5);

		// Draw Remaining
		AssetLoader.munroNormalSmall.draw(batcher, distanceTo, gameWidth * 1 / 20, gameHeight * 35 / 40);
		AssetLoader.munroNormalMedium.draw(batcher, regionHandler.getNextRegionString(), gameWidth * 1 / 20,
				gameHeight * 73 / 80);
		AssetLoader.munroNormalSmall.draw(batcher, distanceLeft, gameWidth * 1 / 20, gameHeight * 77 / 80);
		AssetLoader.munroNormalSmall.draw(batcher, "KM", gameWidth * 2 / 20 + distanceLeft.length() * 4,
				gameHeight * 77 / 80);

		// Draw Total
		AssetLoader.munroNormalMedium.draw(batcher, altitude, gameWidth * 29 / 40, gameHeight * 73 / 80);
		AssetLoader.munroNormalSmall.draw(batcher, distance, (gameWidth * 18 / 20 - distance.length() * 4),
				gameHeight * 77 / 80);
		AssetLoader.munroNormalSmall.draw(batcher, "KM", gameWidth * 18 / 20, gameHeight * 77 / 80);
	}

	private void drawEndScreen() {
		distance = format1DP.format(myWorld.getDistance());
		highscore = format1DP.format(AssetLoader.getHighScore());
		distanceToSpace = format1DP.format(10000 - myWorld.getDistance());
		score.setText(AssetLoader.munroNormalBig, distance);
		region.setText(AssetLoader.munroNormalMedium, "(" + regionHandler.getCurrentRegionString() + ")");
		recordScore.setText(AssetLoader.munroNormalMedium, highscore + " KM" + " (" + AssetLoader.getHighScoreRegion() + ")");
		attemptNo.setText(AssetLoader.munroNormalMedium, "ATTEMPT NO. "+ AssetLoader.getTotalAttempts());
		//AssetLoader.munroSmall.draw(batcher, "// ALIEN NO. " + AssetLoader.getAlienNumber(), gameWidth / 20,
			//	gameHeight * 3 / 30);
		batcher.draw(AssetLoader.launchReport, 0, 0, gameWidth, gameWidth/4);
		AssetLoader.munroSmallMedium.setColor(95/255.0f, 193/255.0f, 213/255.0f, 1);
		//AssetLoader.munroSmallMedium.setColor(r, g, b, a);;
		AssetLoader.munroSmallMedium.draw(batcher, alienNo, (gameWidth-alienNo.width)/2, gameHeight*13/120);
		AssetLoader.munroNormalMedium.draw(batcher, attemptNo, (gameWidth- attemptNo.width)/2, gameHeight*5/30);
		alienNo.setText(AssetLoader.munroSmallMedium, "ALIEN " + AssetLoader.getAlienNumber());
		//AssetLoader.munroSmall.draw(batcher, "// ATTEMPT NO. " + String.valueOf(AssetLoader.getTotalAttempts()),
			//	gameWidth / 20, gameHeight * 4 / 30);
		
		//AssetLoader.munroNormalBig.setColor(Color.BLUE);
		AssetLoader.munroSmallMedium.draw(batcher, altitudeReached, (gameWidth-altitudeReached.width)/2, gameHeight * 8 / 30);
		AssetLoader.munroNormalBig.draw(batcher, score, (gameWidth - score.width)/2, gameHeight*9/30);
		AssetLoader.munroNormalMedium.draw(batcher, region, (gameWidth - region.width)/2, gameHeight*12/30);
		AssetLoader.munroSmallMedium.draw(batcher, recordAltitude, (gameWidth-recordAltitude.width)/2, gameHeight*15/30);
		AssetLoader.munroNormalMedium.draw(batcher, recordScore, (gameWidth - recordScore.width)/2, gameHeight*16/30);
		AssetLoader.munroSmall.draw(batcher, "// " + distanceToSpace + " KM" + " TO SPACE", gameWidth/10, gameHeight*37/60);
		AssetLoader.munroSmall.draw(batcher, "// " + String.valueOf(myWorld.getDeathsInRegion()) + " DEATHS IN " + regionHandler.getCurrentRegionString(), gameWidth/10, gameHeight*39/60);
		AssetLoader.munroSmall.draw(batcher, String.valueOf("// " + myWorld.getTouches() + " TOUCHES"), gameWidth/10, gameHeight*41/60);
		AssetLoader.munroSmall.draw(batcher, "// " + String.valueOf(myWorld.getProjectilesPassed() + " PROJECTILES EVADED"), gameWidth/10, gameHeight*43/60);
		AssetLoader.OCRASmall.draw(batcher, tapTo, (gameWidth - tapTo.width)/2, gameHeight*27/30);
		
		//AssetLoader.munroNormalMedium.setColor(0, 0, 0, 0);
		
		
		/*
		AssetLoader.munroNormalSmall.draw(batcher,
				distance + " KM" + " (" + regionHandler.getCurrentRegionString() + ")", gameWidth / 20,
				gameHeight * 27 / 120);
		AssetLoader.munroSmall.draw(batcher, "RECORD ALTITUDE:", gameWidth / 20, gameHeight * 9 / 30);
		AssetLoader.munroNormalSmall.draw(batcher, highscore + " KM" + " (" + AssetLoader.getHighScoreRegion() + ")",
				gameWidth / 20, gameHeight * 39 / 120);
		AssetLoader.munroSmall.draw(batcher, "DISTANCE TO SPACE:", gameWidth / 20, gameHeight * 12 / 30);
		AssetLoader.munroNormalSmall.draw(batcher, distanceToSpace + " KM", gameWidth / 20, gameHeight * 51 / 120);
		AssetLoader.munroSmall.draw(batcher, "DEATHS IN " + regionHandler.getCurrentRegionString() + ":",
				gameWidth / 20, gameHeight * 15 / 30);
		AssetLoader.munroNormalSmall.draw(batcher, String.valueOf(myWorld.getDeathsInRegion()), gameWidth / 20,
				gameHeight * 63 / 120);
		AssetLoader.munroSmall.draw(batcher, "TOUCHES:", gameWidth / 20, gameHeight * 18 / 30);
		AssetLoader.munroNormalSmall.draw(batcher, String.valueOf(myWorld.getTouches()), gameWidth / 20,
				gameHeight * 75 / 120);
		AssetLoader.munroSmall.draw(batcher, "PROJECTILES EVADED:", gameWidth / 20, gameHeight * 21 / 30);
		AssetLoader.munroNormalSmall.draw(batcher, String.valueOf(myWorld.getProjectilesPassed()), gameWidth / 20,
				gameHeight * 87 / 120);
		AssetLoader.OCRASmall.draw(batcher, tapTo, (gameWidth - tapTo.width) / 2, gameHeight * 27 / 30);
		//AssetLoader.OCRAMedium.draw(batcher, launchReport, (gameWidth - launchReport.width) / 2, gameHeight * 1 / 30);
		*/
		
		
	}

	private void drawClouds() {
		if (regionHandler.getCurrentRegion() == Region.TROPO || regionHandler.getCurrentRegion() == Region.STRATO
				|| regionHandler.getCurrentRegion() == Region.MESO) {
			if (regionHandler.getCurrentRegion() == Region.TROPO) {
				currentCloud = AssetLoader.cloud1;
			}
			if (regionHandler.getCurrentRegion() == Region.STRATO) {
				currentCloud = AssetLoader.cloud2;
			}
			if (regionHandler.getCurrentRegion() == Region.MESO) {
				currentCloud = AssetLoader.cloud3;
			}
			batcher.draw(currentCloud, scroller.getCloud1A().getX(), scroller.getCloud1A().getY(),
					scroller.getCloud1A().getWidth(), scroller.getCloud1A().getHeight());
			batcher.draw(currentCloud, scroller.getCloud1B().getX(), scroller.getCloud1B().getY(),
					scroller.getCloud1B().getWidth(), scroller.getCloud1B().getHeight());
		}
	}

	private void drawStars() {
		if (regionHandler.getCurrentRegion() == Region.THERMO || regionHandler.getCurrentRegion() == Region.EXO
				|| regionHandler.getCurrentRegion() == Region.SPACE) {
			batcher.draw(AssetLoader.star, scroller.getStar1().getX(), scroller.getStar1().getY(),
					scroller.getStar1().getWidth(), scroller.getStar1().getHeight());
			batcher.draw(AssetLoader.star, scroller.getStar2().getX(), scroller.getStar2().getY(),
					scroller.getStar2().getWidth(), scroller.getStar2().getHeight());
			batcher.draw(AssetLoader.star, scroller.getStar3().getX(), scroller.getStar3().getY(),
					scroller.getStar3().getWidth(), scroller.getStar3().getHeight());
		}
	}

	private void drawStartScreen() {
		batcher.draw(AssetLoader.tropoBg, 0, 0, gameWidth, gameHeight);
		batcher.draw(AssetLoader.theAliens, 0, gameHeight * 3 / 20, gameWidth, 80 / (720 / gameWidth));
		batcher.draw(AssetLoader.escapeEarth, 0, gameHeight / 4, gameWidth, 320 / (720 / gameWidth));
		batcher.draw(AssetLoader.tap, 0, gameHeight / 5 * 3, gameWidth, 80 / (720 / gameWidth));
	}

	private void drawMenuUI() {
		for (CircleButton button : menuButtons) {
			button.draw(batcher);
		}
		

	}

	private void drawEndScreenUI(){
		for (CircleButton button : endScreenButtons) {
			button.draw(batcher);
		}
	}

	private void drawBoundingShapes() {
		
		for (int i = 0; i < projectiles.size(); i++) {

			shapeRenderer.setColor(Color.BLUE);
			shapeRenderer.rect(projectiles.get(i).getBoundingRectangle().x, projectiles.get(i).getBoundingRectangle().y,
					projectiles.get(i).getBoundingRectangle().width, projectiles.get(i).getBoundingRectangle().height);
		}
		shapeRenderer.ellipse(alien.getBoundingCircle().x, alien.getBoundingCircle().y, alien.getBoundingCircle().width,
				alien.getBoundingCircle().height);

		for (int i = 0; i < menuButtons.size(); i++) {
			shapeRenderer.ellipse(menuButtons.get(i).getX(), menuButtons.get(i).getY(), menuButtons.get(i).getWidth(),
					menuButtons.get(i).getHeight());
		}
		

	}

	public void render(float runTime) {

		projectiles = myWorld.getProjectileHandler().getCurrentArray();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batcher.begin();

		if (myWorld.isReady()) {
			batcher.enableBlending();
			drawStartScreen();
			drawAlien(runTime);
			drawScore();
			drawMenuUI();
			
		} else if (myWorld.isRunning()) {
			batcher.disableBlending();
			// if (myWorld.currentRegion!= Region.SPACE){

			batcher.enableBlending();
			drawBg();
			drawProjectiles();
			drawClouds();
			drawStars();
			drawProjectiles();
			drawBanner();
			drawAlien(runTime);
			drawScore();
			drawWormhole();
			//drawBoundingShapes();

		}

		else if (myWorld.isFalling()) {
			drawBg();
			drawProjectiles();
			drawClouds();
			drawStars();
			batcher.draw(AssetLoader.alien1, alien.getPosition().x, alien.getPosition().y, alien.getWidth(),
					alien.getHeight());
			drawScore();
		}

		else if (myWorld.isGameOver()) {

			drawBg();
			drawEndScreen();
			drawAlien(runTime);
			drawEndScreenUI();
		}

		else if (myWorld.isGameWon()) {
			drawGameWonScreen();
		}
		batcher.end();

		//shapeRenderer.begin();

		///drawBoundingShapes();
		//shapeRenderer.end();
		
		
		

	}
	
	

}

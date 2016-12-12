package com.aaditya.eehelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class AssetLoader {

	public static Texture texture1, textureBg, trekt;
	public static TextureRegion trekt1, trekt2, trekt3, trekt4, trekt5, trekt6, trekt7, trekt8, trekt9, trekt10,
			trekt11, trekt12, trekt13, trekt14, trekt15, trekt16;
	public static TextureRegion alien1, alien2, alien3;
	public static TextureRegion exoTr, mesoTr, thermoTr, tropoTr, stratoTr;
	public static TextureRegion tropoStrato, stratoMeso, mesoThermo, thermoExo;
	public static TextureRegion tropoBg, stratoBg, mesoBg, thermoBg, exoBg, endBg, spaceShip;
	public static TextureRegion escapeEarth, tap, theAliens, youAre;
	public static TextureRegion laserTr;
	public static TextureRegion cloud1, cloud2, cloud3;
	public static TextureRegion star;
	public static TextureRegion soundsOn, soundsOff, musicOn, musicOff, leaderboards, achievements, share, launchReport;
	public static BitmapFont munroNormalSmall, munroNormalMedium, munroNormalBig, munroSmall, OCRAMedium, munroSmallMedium, OCRASmall, OCRABig;
	public static Animation trektanimation, alienAnimation;
	public static Music fullSound, fall, menuTheme;
	public static Sound tapLeft, tapRight, slash;
	private static Preferences prefs;
	public static Boolean canAdd = true;
	public static Boolean canAdd2 = true;
	public static Boolean canAdd3 = true;
	public static float screenWidth = Gdx.graphics.getWidth();
	public static float screenHeight = Gdx.graphics.getHeight();
	public static float gameWidth = 135;
	public static float gameHeight = 240;
	public static FreeTypeFontGenerator munroNormalFontGenerator, munroSmallFontGenerator, OCRAFontGenerator;
	public static FreeTypeFontGenerator.FreeTypeFontParameter freeTypeFontParameter;

	public static void load() {

		trekt = new Texture(Gdx.files.internal("data/t-rekt logo v2-01.png"));
		trekt.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		texture1 = new Texture(Gdx.files.internal("data/EE.texture1.5.png"));
		texture1.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		textureBg = new Texture(Gdx.files.internal("data/EE.texturebg-01.png"));
		textureBg.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		

		trekt1 = new TextureRegion(trekt, 0, 0, 512, 256);
		trekt2 = new TextureRegion(trekt, 512, 0, 512, 256);
		trekt3 = new TextureRegion(trekt, 1024, 0, 512, 256);
		trekt4 = new TextureRegion(trekt, 1536, 0, 512, 256);
		trekt5 = new TextureRegion(trekt, 0, 256, 512, 256);
		trekt6 = new TextureRegion(trekt, 512, 256, 512, 256);
		trekt7 = new TextureRegion(trekt, 1024, 256, 512, 256);
		trekt8 = new TextureRegion(trekt, 1536, 256, 512, 256);
		trekt9 = new TextureRegion(trekt, 0, 512, 512, 256);
		trekt10 = new TextureRegion(trekt, 512, 512, 512, 256);
		trekt11 = new TextureRegion(trekt, 1024, 512, 512, 256);
		trekt12 = new TextureRegion(trekt, 1536, 512, 512, 256);
		trekt13 = new TextureRegion(trekt, 0, 768, 512, 256);
		trekt14 = new TextureRegion(trekt, 512, 768, 512, 256);
		trekt15 = new TextureRegion(trekt, 1024, 768, 512, 256);
		trekt16 = new TextureRegion(trekt, 1536, 768, 512, 256);

		TextureRegion[] trekts = { trekt1, trekt2, trekt3, trekt4, trekt5, trekt6, trekt7, trekt8, trekt9, trekt10,
				trekt11, trekt12, trekt13, trekt14, trekt15, trekt16 };
		trektanimation = new Animation(0.0125f, trekts);
		trektanimation.setPlayMode(Animation.PlayMode.NORMAL);

		alien1 = new TextureRegion(texture1, 0, 0, 11, 8);
		alien1.flip(false, true);
		alien2 = new TextureRegion(texture1, 11, 0, 11, 8);
		alien2.flip(false, true);
		alien3 = new TextureRegion(texture1, 22, 0, 11, 8);
		alien3.flip(false, true);

		TextureRegion[] aliens = { alien1, alien2, alien1, alien3 };
		alienAnimation = new Animation(0.1f, aliens);
		alienAnimation.setPlayMode(Animation.PlayMode.LOOP);

		cloud1 = new TextureRegion(texture1, 0, 13, 16, 10);
		cloud1.flip(false, true);
		cloud2 = new TextureRegion(texture1, 16, 13, 16, 10);
		cloud2.flip(false, true);
		cloud3 = new TextureRegion(texture1, 32, 13, 16, 10);
		cloud3.flip(false, true);

		star = new TextureRegion(texture1, 1, 8, 3, 3);

		laserTr = new TextureRegion(texture1, 0, 8, 1, 5);

		tropoTr = new TextureRegion(textureBg, 0, 200, 720, 160);
		tropoTr.flip(false, true);
		stratoTr = new TextureRegion(textureBg, 0, 360, 720, 160);
		stratoTr.flip(false, true);
		mesoTr = new TextureRegion(textureBg, 0, 520, 720, 160);
		mesoTr.flip(false, true);
		thermoTr = new TextureRegion(textureBg, 0, 680, 720, 160);
		thermoTr.flip(false, true);
		exoTr = new TextureRegion(textureBg, 0, 840, 720, 160);
		exoTr.flip(false, true);

		tropoBg = new TextureRegion(textureBg, 0, 0, 100, 200);
		tropoBg.flip(false, true);
		stratoBg = new TextureRegion(textureBg, 100, 0, 100, 200);
		stratoBg.flip(false, true);
		mesoBg = new TextureRegion(textureBg, 200, 0, 100, 200);
		mesoBg.flip(false, true);
		thermoBg = new TextureRegion(textureBg, 300, 0, 100, 200);
		thermoBg.flip(false, true);
		exoBg = new TextureRegion(textureBg, 400, 0, 100, 200);
		exoBg.flip(false, true);
		endBg = new TextureRegion(textureBg, 500, 0, 100, 200);
		endBg.flip(false, true);
		spaceShip = new TextureRegion(textureBg, 720, 760, 720, 240 );
		spaceShip.flip(false, true);

		tropoStrato = new TextureRegion(textureBg, 600, 0, 100, 200);
		tropoStrato.flip(false, true);
		stratoMeso = new TextureRegion(textureBg, 700, 0, 100, 200);
		stratoMeso.flip(false, true);
		mesoThermo = new TextureRegion(textureBg, 800, 0, 100, 200);
		mesoThermo.flip(false, true);
		thermoExo = new TextureRegion(textureBg, 900, 0, 100, 200);
		thermoExo.flip(false, true);

		escapeEarth = new TextureRegion(textureBg, 720, 200, 720, 320);
		escapeEarth.flip(false, true);
		theAliens = new TextureRegion(textureBg, 720, 520, 720, 80);
		theAliens.flip(false, true);
		tap = new TextureRegion(textureBg, 720, 600, 720, 80);
		tap.flip(false, true);
		youAre = new TextureRegion(textureBg, 720, 680, 720, 80);
		youAre.flip(false, true);

		musicOff = new TextureRegion(textureBg, 1440, 400, 200, 200);
		musicOff.flip(false, true);
		musicOn = new TextureRegion(textureBg, 1440, 200, 200, 200);
		musicOn.flip(false, true);
		soundsOn = new TextureRegion(textureBg, 1640, 200, 200, 200);
		soundsOn.flip(false, true);
		soundsOff = new TextureRegion(textureBg, 1640, 400, 200, 200);
		soundsOff.flip(false, true);
		leaderboards = new TextureRegion(textureBg, 1840, 200, 200, 200 );
		leaderboards.flip(false, true);
		achievements = new TextureRegion(textureBg, 1840, 400, 200, 200 );
		achievements.flip(false, true);
		share = new TextureRegion(textureBg, 1440, 600, 200, 200 );
		share.flip(false, true);
		launchReport = new TextureRegion(textureBg, 1000, 0, 720, 180);
		launchReport.flip(false, true);
		
		
		/*
		munroNormalSmall = new BitmapFont(Gdx.files.internal("data/munro.fnt"));
		munroNormalSmall.getData().setScale(0.10f, -0.10f);
		munroNormalMedium = new BitmapFont(Gdx.files.internal("data/munro.fnt"));
		munroNormalMedium.getData().setScale(0.13f, -0.13f);
		munroNormalBig = new BitmapFont(Gdx.files.internal("data/munro.fnt"));
		munroNormalBig.getData().setScale(0.30f, -0.30f);
		munroSmallMedium = new BitmapFont(Gdx.files.internal("data/munroSmall.fnt"));
		munroSmallMedium.getData().setScale(0.12f, -0.12f);
		munroSmall = new BitmapFont(Gdx.files.internal("data/munroSmall.fnt"));
		munroSmall.getData().setScale(0.1f, -0.1f);
		OCRAMedium = new BitmapFont(Gdx.files.internal("data/OCRA.fnt"));
		OCRAMedium.getData().setScale(0.15f, -0.15f);
		OCRASmall = new BitmapFont(Gdx.files.internal("data/OCRA.fnt"));
		OCRASmall.getData().setScale(0.08f, -0.08f);
		OCRABig = new BitmapFont(Gdx.files.internal("data/OCRA.fnt"));
		OCRABig.getData().setScale(0.19f, -0.19f);
		*/


		munroNormalFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("data/munro.ttf"));
		freeTypeFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		freeTypeFontParameter.size = 80;
		munroNormalSmall = munroNormalFontGenerator.generateFont(freeTypeFontParameter);
		munroNormalSmall.getData().setScale(0.1f, -0.1f);
		munroNormalMedium = munroNormalFontGenerator.generateFont(freeTypeFontParameter);
		munroNormalMedium.getData().setScale(0.13f, -0.13f);
		munroNormalBig = munroNormalFontGenerator.generateFont(freeTypeFontParameter);
		munroNormalBig.getData().setScale(0.3f, -0.3f);


		munroSmallFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("data/munro_small.ttf"));
		munroSmall = munroSmallFontGenerator.generateFont(freeTypeFontParameter);
		munroSmall.getData().setScale(0.1f, -0.1f);
		munroSmallMedium = munroSmallFontGenerator.generateFont(freeTypeFontParameter);
		munroSmallMedium.getData().setScale(0.12f, -0.12f);

		OCRAFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("data/ocraextended.ttf"));
		OCRASmall = OCRAFontGenerator.generateFont(freeTypeFontParameter);
		OCRASmall.getData().setScale(0.08f, -0.08f);
		OCRAMedium = OCRAFontGenerator.generateFont(freeTypeFontParameter);
		OCRAMedium.getData().setScale(0.15f, -0.15f);
		OCRABig = OCRAFontGenerator.generateFont(freeTypeFontParameter);
		OCRABig.getData().setScale(0.19f, -0.19f);



		prefs = Gdx.app.getPreferences("EscapeEarth");
		if (!prefs.contains("highScore")) {
			prefs.putFloat("highScore", 0);
		}
		if (!prefs.contains("highScoreRegion")) {
			prefs.putString("highScoreRegion", "TROPOSPHERE");
		}
		if (!prefs.contains("deathsInTroposphere")) {
			prefs.putInteger("deathsInTroposphere", 0);
		}
		if (!prefs.contains("deathsInStratosphere")) {
			prefs.putInteger("deathsInStratosphere", 0);
		}
		if (!prefs.contains("deathsIsMesosphere")) {
			prefs.putInteger("deathsInMesosphere", 0);
		}
		if (!prefs.contains("deathsInThermosphere")) {
			prefs.putInteger("deathsInThermosphere", 0);
		}
		if (!prefs.contains("deathsInExosphere")) {
			prefs.putInteger("deathsInExosphere", 0);
		}
		if (!prefs.contains("totalAttempts")) {
			prefs.putInteger("totalAttempts", 0);
		}
		if (!prefs.contains("alienNumber")){
			prefs.putInteger("alienNumber", 1);
		}

		fullSound = Gdx.audio.newMusic(Gdx.files.internal("data/EE OST 2.1.mp3"));
		menuTheme = Gdx.audio.newMusic(Gdx.files.internal("data/EE OST - menushort.mp3"));
		menuTheme.setLooping(true);
		fall = Gdx.audio.newMusic(Gdx.files.internal("data/EE OST - fall.mp3"));
		tapLeft = Gdx.audio.newSound(Gdx.files.internal("data/EE OST - tapleft.mp3"));
		tapRight = Gdx.audio.newSound(Gdx.files.internal("data/EE OST - tapright.mp3"));
		slash = Gdx.audio.newSound(Gdx.files.internal("data/EE OST - slash.mp3"));
	}

	public static void setHighScore(float val) {
		prefs.putFloat("highScore", val);
		prefs.flush();
	}

	public static float getHighScore() {
		return prefs.getFloat("highScore");
	}

	public static void setHighScoreRegion(String region) {
		prefs.putString("highScoreRegion", region);
		prefs.flush();
	}

	public static String getHighScoreRegion() {
		return prefs.getString("highScoreRegion");
	}

	public static void addDeathsInRegion(String region) {
		if (canAdd == true) {
			prefs.putInteger(region, prefs.getInteger(region) + 1);
			prefs.flush();
			canAdd = false;
		}
	}
	

	public static int getDeathsInRegion(String region) {
		return prefs.getInteger(region);
	}

	public static void addAttemps() {
		if (canAdd2 = true) {
			prefs.putInteger("totalAttempts", prefs.getInteger("totalAttempts") + 1);
			canAdd2 = false;
			prefs.flush();
		}
	}

	public static void resetAttempts(){
		if (canAdd3 ==true){
			prefs.putInteger("totalAttempts", 0);
			prefs.putInteger("alienNumber", prefs.getInteger("alienNumber")+1);
			prefs.putInteger("deathsInTroposphere", 0);
			prefs.putInteger("deathsInStratosphere", 0);
			prefs.putInteger("deathsInMesosphere", 0);
			prefs.putInteger("deathsInThermosphere", 0);
			prefs.putInteger("deathsInExosphere", 0);
			prefs.flush();
			
			canAdd3 = false;
			
		}
	}
	
	public static int getAlienNumber(){
		return prefs.getInteger("alienNumber");
	}
	
	public static int getTotalAttempts() {
		return prefs.getInteger("totalAttempts");
	}

	public static void dispose() {
		munroNormalSmall.dispose();
		munroNormalMedium.dispose();
		munroNormalBig.dispose();
		munroSmallMedium.dispose();
		munroSmall.dispose();
		OCRAMedium.dispose();
		OCRASmall.dispose();
		OCRABig.dispose();
		munroNormalFontGenerator.dispose();
		munroSmallFontGenerator.dispose();
		OCRAFontGenerator.dispose();
		texture1.dispose();
		textureBg.dispose();
		trekt.dispose();
		fullSound.dispose();
		menuTheme.dispose();
		fall.dispose();
		tapLeft.dispose();
		tapRight.dispose();
		slash.dispose();
	}
}

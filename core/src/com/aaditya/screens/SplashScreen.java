package com.aaditya.screens;

import com.aaditya.eehelpers.AssetLoader;
import com.aaditya.escapeearth.EEGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class SplashScreen implements Screen{
	
	private EEGame game;
	private SpriteBatch batcher;
	private float runTime;
	private Boolean canPlay;
	//private OrthographicCamera cam;
	float screenWidth = Gdx.graphics.getWidth();
	float screenHeight = Gdx.graphics.getHeight();
	float gameWidth = AssetLoader.gameWidth;
	float gameHeight = AssetLoader.gameHeight;
	 
	public SplashScreen(EEGame game) {
		//cam = new OrthographicCamera();
		//cam.setToOrtho(true, 136, gameHeight);
		batcher = new SpriteBatch();
		//batcher.setProjectionMatrix(cam.combined);
	        this.game = game;
	        canPlay = true;
	    }
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		runTime += delta;
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batcher.begin();
		if (runTime<2.5){
		batcher.draw(AssetLoader.trekt1, screenWidth/2 - 256, screenHeight/2 - 128, 512, 256);
		}
		if (runTime>=2.5){
		if (canPlay == true){
		AssetLoader.slash.play();
		canPlay = false;
		}
		batcher.draw(AssetLoader.trektanimation.getKeyFrame(runTime-2.5f),
                screenWidth/2 - 256, screenHeight/2 - 128, 512, 256);
		}
		if (runTime >4.5){
			game.setScreen(new GameScreen(game));

		}
		batcher.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}

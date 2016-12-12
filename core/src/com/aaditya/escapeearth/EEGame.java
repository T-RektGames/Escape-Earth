package com.aaditya.escapeearth;

import com.aaditya.eehelpers.AssetLoader;
import com.aaditya.gameservices.ActionResolver;
import com.aaditya.screens.GameScreen;
import com.aaditya.screens.SplashScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class EEGame extends Game {
	public ActionResolver actionResolver;
	
	public EEGame(ActionResolver actionResolver) {
			this.actionResolver = actionResolver;
	}

	@Override
    public void create() {
        Gdx.app.log("EEGame", "created");
        AssetLoader.load();
        setScreen(new SplashScreen(this));
        
    }
	
	@Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }

	
	
}

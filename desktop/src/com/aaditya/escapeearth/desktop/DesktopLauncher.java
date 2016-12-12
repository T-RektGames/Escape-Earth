package com.aaditya.escapeearth.desktop;

import com.aaditya.escapeearth.EEGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "EscapeEarth";
		config.width = 360;
	    config.height = 640;

		new LwjglApplication(new EEGame(new ActionResolverDesktop()), config);
	}
}

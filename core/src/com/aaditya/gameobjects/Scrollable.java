package com.aaditya.gameobjects;

import com.aaditya.eehelpers.AssetLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Scrollable {

	// Protected is similar to private, but allows inheritance by subclasses.
	protected Vector2 position;
	protected Vector2 velocity;
	protected float width;
	protected float height;
	protected boolean isScrolledDown;
	float gameWidth = AssetLoader.gameWidth;
	float gameHeight = AssetLoader.gameHeight;

	public Scrollable(float x, float y, float width, float height, float scrollSpeed) {
		position = new Vector2(x, y);
		velocity = new Vector2(0, scrollSpeed);
		this.width = width;
		this.height = height;
		isScrolledDown = false;
	}

	public void update(float delta) {
		position.add(velocity.cpy().scl(delta));

		// If the Scrollable object is no longer visible:
		if (position.y > gameHeight) {
			isScrolledDown = true;
		}
	}

	// Reset: Should Override in subclass for more specific behavior.
	public void reset(float newX, float newY) {
		position.x = newX;
		position.y = newY;
		isScrolledDown = false;
	}

	public void stop() {
		velocity.y = 0;
	}

	// Getters for instance variables
	public boolean isScrolledDown() {
		return isScrolledDown;
	}

	public float getTailX() {
		return position.x + width;
	}

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}
	
	public void setY(float y) {
		this.position.y = y;
	}
	
	public void setSpeedY(float y) {
		this.velocity.y = y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

}

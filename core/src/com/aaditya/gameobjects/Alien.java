package com.aaditya.gameobjects;

import com.aaditya.eehelpers.AssetLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Alien {

	private Vector2 position;
	private Vector2 velocity;
	private float width;
	private float height;
	private Ellipse boundingCircle;
	float gameWidth = AssetLoader.gameWidth;
	float gameHeight = AssetLoader.gameHeight;
	private boolean hasTouchedSides = false;
	
	public Alien(float x, float y, int width, int height) {
		this.width = width;
		this.height = height;
		this.position = new Vector2(x, y);
		this.velocity = new Vector2(0, 0);
		this.boundingCircle = new Ellipse(position.x , position.y
				, width,  height-2);
	}

	public void update(float delta) {
		position.add(velocity.cpy().scl(delta));
		boundingCircle.set(position.x, position.y+1, width, height-1);
		if (position.x > gameWidth - width) {
			position.x = gameWidth - width;
			boundingCircle.x = gameWidth- width;
			hasTouchedSides = true;
		}
		if (position.x < 0) {
			position.x = 0;
			boundingCircle.x = 0;
			hasTouchedSides = true;
		}

	}
	
	public void onRestart(float x, float y) {
       
		position.x = x;
        position.y = y;
        velocity.x = 0;
        velocity.y = 0;
        hasTouchedSides = false;
        
    }

	public Ellipse getBoundingCircle() {
		return boundingCircle;
	}

	public void setBoundingCircle(Ellipse boundingCircle) {
		this.boundingCircle = boundingCircle;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public float getVelocityX() {
		return velocity.x;
	}

	public float getVelocityY() {
		return velocity.y;
	}

	public void setVelocityX(float velocityX) {
		this.velocity.x = velocityX;
	}

	public void setVelocityY(float velocityY) {
		this.velocity.y = velocityY;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
	
	public void setPositionY(float positionY) {
		this.position.y = positionY;
	}
	
	public boolean getHasTouchedSides(){
		return this.hasTouchedSides;
	}
	
	
}

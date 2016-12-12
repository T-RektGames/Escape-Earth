package com.aaditya.gameobjects;

import com.aaditya.eehelpers.AssetLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.math.Vector2;


public class Projectile {
	
	private Vector2 position;
	private Vector2 velocity;
	private Rectangle boundingRectangle;
	private float width;
	private float height;
    float gameWidth = AssetLoader.gameWidth;
    float gameHeight = AssetLoader.gameHeight;
	private boolean alive;
	private boolean isRecorded = false;
	
    
	public Projectile(float posX, float posY, float speedX, float speedY, float width, float height){
		this.position = new Vector2(posX, posY);
		this.velocity = new Vector2(speedX, speedY);
		this.boundingRectangle = new Rectangle(posX, posY, width, height);
		this.width = width;
		this.height = height;
		alive = true;
	}

	public void update(float delta, float screenSize){
		//if (alive){
			position.add(velocity.cpy().scl(delta));
			boundingRectangle.set(position.x, position.y, width, height);
		//}
	}
	
	public float getPositionX() {
		return position.x;
	}
	
	public float getPositionY() {
		return position.y;
	}

	public void setPositionX(float positionX) {
		this.position.x = positionX;
	}
	
	public void setPositionY(float positionY) {
		this.position.y = positionY;
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

	public Rectangle getBoundingRectangle() {
		return boundingRectangle;
	}

	public void setBoundingRectangle(Rectangle boundingRectangle) {
		this.boundingRectangle = boundingRectangle;
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public void setAlive(boolean alive){
		this.alive = alive;
	}
	
	public boolean isRecorded(){
		return isRecorded;
	}
	
	public void setRecorded(boolean recorded){
		this.isRecorded = recorded;
	}
	
	

}

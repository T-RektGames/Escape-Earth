package com.aaditya.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Ellipse;

public class CircleButton {
	

	    private float x, y, width, height;

	    private TextureRegion buttonUp;
	    private TextureRegion buttonDown;

	    private Ellipse bounds;

	    private boolean isPressed = true;

	    public CircleButton(float x, float y, float width, float height,
	            TextureRegion buttonUp, TextureRegion buttonDown) {
	        this.x = x;
	        this.y = y;
	        this.width = width;
	        this.height = height;
	        this.buttonUp = buttonUp;
	        this.buttonDown = buttonDown;
	        
	        bounds = new Ellipse(x+width/2, y+height/2, width, height);

	    }

	    public boolean isClicked(int screenX, int screenY) {
	        return bounds.contains(screenX, screenY);
	    }

	    public void draw(SpriteBatch batcher) {
	        if (isPressed) {
	            batcher.draw(buttonDown, x, y, width, height);
	        } else {
	            batcher.draw(buttonUp, x, y, width, height);
	        }
	    }

	    public boolean isTouchDown(int screenX, int screenY) {

	        if (bounds.contains(screenX, screenY)) {
	        	
	           
	            return true;
	        }

	        return false;
	    }

	    
	    public void setPressed (Boolean pressed){
	    	this.isPressed = pressed;
	    }
	    
	    public boolean isPressed() {
	    	return this.isPressed;
	    }

		public float getX() {
			return x;
		}

		public void setX(float x) {
			this.x = x;
		}

		public float getY() {
			return y;
		}

		public void setY(float y) {
			this.y = y;
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
		
		public Ellipse getBounds(){
			return this.bounds;
		}
	    
	    

	}



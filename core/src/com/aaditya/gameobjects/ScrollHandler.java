package com.aaditya.gameobjects;

import java.util.ArrayList;

import com.aaditya.eehelpers.AssetLoader;
import com.aaditya.gameobjects.RegionHandler.Region;
import com.aaditya.gameworld.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class ScrollHandler {

	private Background tropoBg, stratoBg, mesoBg, thermoBg, exoBg,spaceBg, spaceShipBg, tropoStrato, stratoMeso, mesoThermo, thermoExo, exoSpace;
	private Cloud cloud1A, cloud1B;
	private Star star1, star2, star3;
	private ArrayList<Background> backgrounds;
	float gameWidth = 135;
	float gameHeight = AssetLoader.gameHeight;

	private RegionHandler regionHandler;

	public ScrollHandler(RegionHandler regionHandler) {
		this.regionHandler = regionHandler;
		
	
		tropoBg = new Background(0, 0,  gameWidth,
				 gameHeight, 80);
		stratoBg = new Background(0, -gameHeight*2,  gameWidth,  gameHeight, 80);
		mesoBg = new Background(0, - gameHeight*4,  gameWidth,
				 gameHeight, 80);
		thermoBg = new Background(0, -gameHeight*6,  gameWidth,  gameHeight, 80);
		exoBg = new Background(0, -gameHeight*8,  gameWidth,
				 gameHeight, 80);
		spaceBg = new Background(0, - gameHeight*10,  gameWidth,
				 gameHeight, 80);
		spaceShipBg = new Background(0, -gameHeight*10-gameWidth/3, gameWidth, gameWidth/3, 80);
		tropoStrato = new Background(0, -gameHeight,  gameWidth, gameHeight, 80);
		stratoMeso = new Background(0, -gameHeight*3, gameWidth,
				 gameHeight, 80);
		mesoThermo = new Background(0, -gameHeight*5,  gameWidth, gameHeight, 80);
		thermoExo = new Background(0, -gameHeight*7, gameWidth,
				 gameHeight, 80);
		exoSpace = new Background(0, -gameHeight*9,  gameWidth, gameHeight, 80);
		backgrounds = new ArrayList<Background>();
		
		backgrounds.add(tropoBg);
		backgrounds.add(tropoStrato);
		backgrounds.add(stratoBg);
		backgrounds.add(stratoMeso);
		backgrounds.add(mesoBg);
		backgrounds.add(mesoThermo);
		backgrounds.add(thermoBg);
		backgrounds.add(thermoExo);
		backgrounds.add(exoBg);
		backgrounds.add(exoSpace);
		backgrounds.add(spaceBg);
		backgrounds.add(spaceShipBg);
		
		
		
		cloud1A = new Cloud(50, 100, 32, 20, 10);
		cloud1B = new Cloud (100, -50, 32, 20, 10);
		
		star1 = new Star(40, 0, 9, 9, 20);
		star2 = new Star(80, -gameHeight/3, 9, 9, 20);
		star3 = new Star(120,-gameHeight*2/3, 9, 9, 20);
		
	}

	
	public void updateTropo(float delta){
		updateAll(delta, stratoBg);
		if (stratoBg.getY()>=0){
			regionHandler.setTropoUpdated(true);
		}
	}
	
	public void updateStrato(float delta){
		updateAll(delta, mesoBg);
		if (mesoBg.getY()>=0){
			regionHandler.setStrapoUpdated(true);
		}
	}
	
	public void updateMeso(float delta){
		updateAll(delta, thermoBg);
		if (thermoBg.getY()>=0){
			regionHandler.setMesoUpdated(true);
		}
	}
	
	public void updateThermo(float delta){
		updateAll(delta, exoBg);
		if (exoBg.getY()>=0){
			regionHandler.setThermoUpdated(true);
		}
	}
	
	public void updateExo(float delta){
		updateAll(delta, spaceBg);
		if (spaceBg.getY()>=0){
			regionHandler.setExoUpdated(true);
		}
	}
	
	public void updateSpace(float delta){
		updateAll(delta, spaceShipBg);
		if (spaceShipBg.getY()>=0){
			regionHandler.setSpaceUpdated(true);
		}
	}
	
	public void fallBack (float delta){
		if (tropoBg.getY()>0){
		for (int i = 0; i <backgrounds.size(); i ++){
			backgrounds.get(i).setSpeedY(-400);
			backgrounds.get(i).update(delta);
			}
		}
		
		if (regionHandler.getCurrentRegion() == Region.TROPO || regionHandler.getCurrentRegion() == Region.STRATO || regionHandler.getCurrentRegion() == Region.MESO ){
			cloud1A.setSpeedY(-250);
			cloud1B.setSpeedY(-250);
			cloud1A.update(delta);
			cloud1B.update(delta);
		}	else	{
			star1.setSpeedY(-250);
			star2.setSpeedY(-250);
			star3.setSpeedY(-250);
			star1.update(delta);
			star2.update(delta);
			star3.update(delta);
		}
	}
	
	public void updateClouds(float delta){
		cloud1A.update(delta);
		cloud1B.update(delta);
		if (cloud1A.isScrolledDown){
			cloud1A.reset(getCloudValue(cloud1B), -50);
		}
		
		if (cloud1B.isScrolledDown){
			cloud1B.reset(getCloudValue(cloud1A), -50);
		}
	}
	
	public void updateStars(float delta){
		star1.update(delta);
		star2.update(delta);
		star3.update(delta);
		
		if (star1.isScrolledDown){
			star1.reset(getStarValue(star3), 0);
		}
		
		if (star2.isScrolledDown){
			star2.reset(getStarValue(star1), 0);
		}
		
		if (star3.isScrolledDown){
			star3.reset(getStarValue(star2), 0);
		}
	}
	
	public void onRestart(){
		
		for (int i = 0; i <backgrounds.size()-1; i ++){
			backgrounds.get(i).setY(-i*gameHeight);
			backgrounds.get(i).setSpeedY(80);
		}
		
		spaceShipBg.setY(-gameHeight*10-gameWidth/3);
		spaceShipBg.setSpeedY(80);
		
		cloud1A.setSpeedY(10);
		cloud1B.setSpeedY(10);
		cloud1A.setY(100);
		cloud1B.setY(-50);
	
		star1.setSpeedY(20);
		star2.setSpeedY(20);
		star3.setSpeedY(20);
		star1.setY(-gameHeight/3);
		star2.setY(-gameHeight*2/3);
		star3.setY(-gameHeight*3/3);
	
	}
	
	
	
	public Cloud getCloud1A(){
		return cloud1A;
	}
	
	public Cloud getCloud1B(){
		return cloud1B;
	}
	
	public Star getStar1(){
		return star1;
	}
	
	public Star getStar2(){
		return star2;
	}
	
	public Star getStar3(){
		return star3;
	}
	
	
	
	public Background getTropoBg() {
		return tropoBg;
	}

	public Background getStratoBg() {
		return stratoBg;
	}

	public Background getMesoBg() {
		return mesoBg;
	}

	public Background getThermoBg() {
		return thermoBg;
	}

	public Background getExoBg() {
		return exoBg;
	}

	public Background getSpaceBg() {
		return spaceBg;
	}
	
	public Background getSpaceShipBg() {
		return spaceShipBg;
	}

	public Background getTropoStrato() {
		return tropoStrato;
	}

	public Background getStratoMeso() {
		return stratoMeso;
	}

	public Background getMesoThermo() {
		return mesoThermo;
	}

	public Background getThermoExo() {
		return thermoExo;
	}

	public Background getExoSpace() {
		return exoSpace;
	}

	

	private float getCloudValue(Cloud cloud) {
		float newValue = MathUtils.random(gameWidth-20);
		while (Math.abs(newValue - cloud.getX()) < gameWidth * 2 / 5) {
			newValue = MathUtils.random(gameWidth-20);
		}
		return newValue;
	}
	
	private float getStarValue(Star star) {
		float newValue = MathUtils.random(gameWidth-9);
		while (Math.abs(newValue - star.getX()) < gameWidth * 2 / 5) {
			newValue = MathUtils.random(gameWidth-9);
		}
		return newValue;
	}
	
	private void updateAll(float delta, Background bg){
		if(bg.getY()<0){
			for (int i = 0; i <backgrounds.size(); i ++){
				backgrounds.get(i).update(delta);
			}
		
		}
	}

	public ArrayList<Background> getBackgrounds() {
		return backgrounds;
	}

	
}


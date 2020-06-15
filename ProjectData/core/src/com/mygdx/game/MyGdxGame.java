package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game {

	public SpriteBatch batch;

	@Override
	public void create () {
	this.setScreen(new MainMenuScreen(this));
	//this.setScreen(new GameOverScreen(this));
	batch = new SpriteBatch();
	}


}

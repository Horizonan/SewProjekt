package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;


public class GameOverScreen extends ScreenAdapter {

    Texture startButton;
    private static final int START_BUTTON_WIDTH = 300;
    private static final int START_BUTTON_HEIGHT = 100;
    MyGdxGame game;

    public GameOverScreen(MyGdxGame game) {
        startButton = new Texture("gameOverScreen.png");
        this.game = game;
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        int startButtonX = Gdx.graphics.getWidth() / 2 - START_BUTTON_WIDTH / 2;
        int startButtonY = Gdx.graphics.getHeight() / 2 - START_BUTTON_HEIGHT / 2;
        game.batch.draw(startButton, startButtonX, startButtonY, START_BUTTON_WIDTH, START_BUTTON_HEIGHT );
        game.batch.end();

    }

    @Override
    public void dispose() {
        game.batch.dispose();
    }

    @Override
    public void hide() {
        super.hide();
    }
}
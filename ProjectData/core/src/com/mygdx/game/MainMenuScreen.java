package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class MainMenuScreen extends ScreenAdapter {

    Texture startButton;
    private static final int START_BUTTON_WIDTH = 300;
    private static final int START_BUTTON_HEIGHT = 100;
    MyGdxGame game;

    public MainMenuScreen(MyGdxGame game) {
        startButton = new Texture("startButton.png");
        this.game = game;
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        int startButtonX = Gdx.graphics.getWidth() / 2 - START_BUTTON_WIDTH / 2;
        int startButtonY = Gdx.graphics.getHeight() / 2 - START_BUTTON_HEIGHT / 2;
        if(Gdx.input.getX() < startButtonX + START_BUTTON_WIDTH && Gdx.input.getX() > startButtonX && Gdx.graphics.getHeight() - Gdx.input.getY() < startButtonY + START_BUTTON_HEIGHT && Gdx.graphics.getHeight() - Gdx.input.getY() > startButtonY){
            if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                game.setScreen(new Gamescreen());
            }
        }
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
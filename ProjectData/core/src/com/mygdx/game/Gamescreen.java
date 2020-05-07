package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Gamescreen extends ScreenAdapter {

    SpriteBatch batch;
    Texture img;
    //Game Variables
    MyTextInputListener listener = new MyTextInputListener();


    public Gamescreen() {
        batch = new SpriteBatch();
        Gdx.input.getTextInput(listener, "Name", "", "Enter your name");
    }

    @Override
    public void render(float delta) {


        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.end();

    }

    public class MyTextInputListener implements Input.TextInputListener {
        @Override
        public void input(String text) {
        }

        @Override
        public void canceled() {
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }

    @Override
    public void hide() {
        super.hide();
    }

    }



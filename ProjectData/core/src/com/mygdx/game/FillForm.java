package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import sun.jvm.hotspot.utilities.BitMap;

public class FillForm extends ScreenAdapter {

    private Game game;
    private Stage stage;
    private TextField empireField;
    private TextField gameCurrency;
    private TextField leaderField;


    public String empireName;
    public String currencyName;
    public String leaderName;

    BitmapFont font;
    SpriteBatch batch;
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;


    public FillForm(Game g){
        game = g;
        batch = new SpriteBatch();
        generator = new FreeTypeFontGenerator(Gdx.files.internal("Standart.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 16;
        parameter.color = Color.BLACK;
        font = generator.generateFont(parameter);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        final TextButton btnLogin = new TextButton("Submit", skin);
        btnLogin.setPosition(250,100);
        btnLogin.setSize(300,70);
        btnLogin.addListener(new ClickListener(){
            @Override
            public void touchUp(InputEvent e, float x, float y, int point, int button){
                btnLoginClicked();
            }
        });
        empireField = new TextField("Empire-Name", skin);
        empireField.setPosition(250, 305);
        empireField.setSize(300, 40);

        leaderField = new TextField("Leader-Name", skin);
        leaderField.setPosition(250, 250);
        leaderField.setSize(300, 40);

        gameCurrency = new TextField("Currency-Name", skin);
        gameCurrency.setPosition(250, 195);
        gameCurrency.setSize(300, 40);

        stage.addActor(empireField);
        stage.addActor(leaderField);
        stage.addActor(gameCurrency);
        stage.addActor(btnLogin);
    }

    public void btnLoginClicked(){
        empireName = empireField.getText();
        leaderName = leaderField.getText();
        currencyName = gameCurrency.getText();

        game.setScreen(new Gamescreen(empireName, currencyName));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        font.draw(batch, "High-Space", 250, 425);
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Gamescreen extends ScreenAdapter {

    BitmapFont font;
    BitmapFont cashFont;
    SpriteBatch batch;
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    public static Texture backgroundTexture;

    private Stage stage;

    private float timeHelper;
    public String empireName;

    //Game Variables
    private TextButton btnBuyFac;


    //Factories
    public int factories = 0;
    public int factoryPrice = 1;

    public int cash = 0;
    public int income = 1;

    public Gamescreen(String empire) {
        empireName = empire;
        batch = new SpriteBatch();
        generator = new FreeTypeFontGenerator(Gdx.files.internal("Standart.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 16;
        parameter.color = Color.BLACK;
        font = generator.generateFont(parameter);
        cashFont = generator.generateFont(parameter);
        backgroundTexture = new Texture("images/background.png");

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        btnBuyFac = new TextButton("Buy Factory", skin);
        btnBuyFac.setPosition(450,180);
        btnBuyFac.setSize(280,70);


        stage.addActor(btnBuyFac);

    }

    public void btnBuyFactory(){
        if(cash >= factoryPrice){
            factoryPrice = factoryPrice * 2;
            factories += 1;
            income += 1;
            cash -= factoryPrice;
            return;
        } else {
            return;
        }
    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();



        font.draw(batch, empireName, 250, 450);
        cashFont.draw(batch, Integer.toString(cash), 450, 450);

        batch.end();

        btnBuyFac.addListener(new ClickListener(){
            @Override
            public void touchUp(InputEvent e, float x, float y, int point, int button){
                btnBuyFactory();
            }
        });

        stage.act(delta);
        stage.draw();

        timeHelper += Gdx.graphics.getDeltaTime();
        if(timeHelper > 1){
            cash = cash + income;
            timeHelper = 0;

        }
    }


    @Override
    public void dispose() {
    }

    @Override
    public void hide() {
        super.hide();
    }

    }



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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Gamescreen extends ScreenAdapter{

    BitmapFont font;
    BitmapFont cashFont;
    SpriteBatch batch;
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    public static Texture backgroundTexture;

    private Stage stage;

    private float timeHelper;
    private String empireName;
    private String currencyName;

    //Game Variables
    private TextButton btnBuyFac;
    private TextButton btnBuyHos;
    private TextButton btnBuyMin;
    private TextButton btnBuyRes;
    private TextButton btnBuyBar;
    private TextButton btnBuySpa;

    private ShapeRenderer shapeRenderer;
    private Skin skin;
    ExitDialog exitDia;
    PirateDialog pirDia;


    //Factories
    private int factories = 0;
    private int factoryPrice = 1;

    private int hospitalPrice = 1;
    private int hospitals = 0;
    private double plagueSafety = 0.02;

    private int mines = 0;
    private int minePrice = 1;
    private double material = 0;

    private int rescue = 0;
    private int rescuePrice = 1;
    private double stormSafety = 0.02;

    private int barrack = 0;
    private int barrackPrice = 1;
    private int dailySoldiers = 0;
    private int soldiers;

    private int portPrice = 1;
    private int ports = 0;
    private int dailyShips = 0;
    private int ships = 0;

    private int cash = 0;
    private int income = 1;

    //Event
    private int loosesBuild = 0;
    private int loosesSold = 0;

    private int pirateEvent = 0;
    private int pirateEventTicker = 10;
    private int pirateSoldiers = 0;
    private int accounter;
    private int buildAccounter;

    public Gamescreen(String empire, String currencyN) {
        empireName = empire;
        currencyName = currencyN;
        batch = new SpriteBatch();
        generator = new FreeTypeFontGenerator(Gdx.files.internal("Standart.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 16;
        parameter.color = Color.WHITE;
        font = generator.generateFont(parameter);
        cashFont = generator.generateFont(parameter);
        backgroundTexture = new Texture("images/background.png");
        shapeRenderer = new ShapeRenderer();
        skin = new Skin(Gdx.files.internal("button/uiskin.json"));


        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

         exitDia = new ExitDialog("Confirm exit", skin);
         pirDia = new PirateDialog("Pirate Attack", skin);

        btnBuyFac = new TextButton("Buy Factory", skin);
        btnBuyFac.setPosition(650,280);
        btnBuyFac.setSize(100,50);
        btnBuyFac.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                btnBuyFactory();
                return true;
            }
        });

        btnBuyMin = new TextButton("Buy Mine", skin);
        btnBuyMin.setPosition(650,180);
        btnBuyMin.setSize(100,50);
        btnBuyMin.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                btnBuyMine();
                return true;
            }
        });

        btnBuySpa = new TextButton("Buy Port", skin);
        btnBuySpa.setPosition(650,80);
        btnBuySpa.setSize(100,50);
        btnBuySpa.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                btnBuySpacePort();
                return true;
            }
        });

        btnBuyRes = new TextButton("Buy Rescue", skin);
        btnBuyRes.setPosition(50,180);
        btnBuyRes.setSize(100,50);
        btnBuyRes.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                btnBuyRescue();
                return true;
            }
        });

        btnBuyHos = new TextButton("Buy Hospital", skin);
        btnBuyHos.setPosition(50,280);
        btnBuyHos.setSize(100,50);
        btnBuyHos.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                btnBuyHospital();
                return true;
            }
        });

        btnBuyBar = new TextButton("Buy Barrack", skin);
        btnBuyBar.setPosition(50,80);
        btnBuyBar.setSize(100,50);
        btnBuyBar.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                btnBuyBarrack();
                return true;
            }
        });

        stage.addActor(btnBuySpa);
        stage.addActor(btnBuyBar);
        stage.addActor(btnBuyRes);
        stage.addActor(btnBuyFac);
        stage.addActor(btnBuyHos);
        stage.addActor(btnBuyMin);

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

    public void btnBuyHospital(){
        if(cash >= hospitalPrice){
            hospitalPrice *= 2;
            hospitals += 1;
            plagueSafety *= 2;
            cash -= hospitalPrice;
            return;
        } else {
            return;
        }
    }

    public void btnBuyMine(){
        if(cash >= minePrice){
            minePrice *= 2;
            mines += 1;
            material += 1.5;
            cash -= minePrice;
            return;
        } else {
            return;
        }
    }
    public void btnBuyRescue(){
        if(cash >= rescuePrice){
            rescuePrice *= 2;
            rescue += 1;
            material += 1.5;
            stormSafety *= 2;
            cash -= rescuePrice;
            return;
        } else {
            return;
        }
    }
    public void btnBuyBarrack(){
        if(cash >= barrackPrice){
            barrackPrice *= 2;
            barrack += 1;
            dailySoldiers += 10;
            cash -= barrackPrice;
            return;
        } else {
            return;
        }
    }
    public void btnBuySpacePort(){
        if(cash >= portPrice){
            portPrice *= 2;
            ports += 1;
            dailyShips += 1;
            cash -= portPrice;
            return;
        } else {
            return;
        }
    }

    public static class ExitDialog extends Dialog{
          public ExitDialog(String title, Skin skin, String windowStyleName) {
              super(title, skin, windowStyleName);
          }
        public ExitDialog(String title, Skin skin) {
            super(title, skin);
        }
        public ExitDialog(String title, WindowStyle windowStyleName) {
            super(title, windowStyleName);
        }
        {
            text("Do you really want to leave?");
            button("Yes", "Yes");
            button("No", "Enjoy your stay");
        }
        @Override
        protected void result(Object object){
            if(object == "Yes"){
                Gdx.app.exit();
            } else{
                System.out.println(object);
            }
        }
    }

    public class PirateDialog extends Dialog{
        public PirateDialog(String title, Skin skin, String windowStyleName) {
            super(title, skin, windowStyleName);
        }
        public PirateDialog(String title, Skin skin) {
            super(title, skin);
        }
        public PirateDialog(String title, WindowStyle windowStyleName) {
            super(title, windowStyleName);
        }
        {
            text("You lost " + loosesSold + " Soldiers and " + loosesBuild + " Factorys");
            button("Ok", "Yes");
        }
    }




    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, 800, 550);
        font.draw(batch, empireName, Gdx.graphics.getWidth() / 2 - 70, 450);
        font.draw(batch, currencyName + ": " + Integer.toString(cash), 600, 450);

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            exitDia.show(stage);
        }
        if(pirateEvent == pirateEventTicker) {
            pirDia.show(stage);
        }

        batch.end();

        stage.act(delta);
        stage.draw();

        timeHelper += Gdx.graphics.getDeltaTime();
        if(timeHelper > 1){

            if(pirateEvent == pirateEventTicker){
                accounter += 150;
                pirateEventTicker = 1 + (int)(Math.random() * 100);
                pirateSoldiers = 1 + (int)(Math.random() * 600 + accounter);
                pirateEvent = 0;
            }

            soldiers += dailySoldiers;
            cash = cash + income;
            timeHelper = 0;
            ships += dailyShips;
            pirateEvent++;


            if(pirateEvent == pirateEventTicker) {
                if(soldiers < pirateSoldiers){
                    loosesBuild = 1 + (int)(Math.random() * 10 + buildAccounter);
                    loosesSold = soldiers - soldiers * (20 / 100);
                    soldiers *= 0.2;
                } else{
                    loosesSold = soldiers - pirateSoldiers;
                }
            }


            //Debbuging
            System.out.println(pirateEvent);
            System.out.println("ticker: " + pirateEventTicker);
            System.out.println("Soldiers: " + soldiers);
            System.out.println("Soldiers: " + pirateSoldiers);
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



package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.mainGame.mainGame;

public class hud {
    public Stage stage;
    private Viewport viewport;


    private Integer worldTimer;
    private float timeCount;
    private static float p1_h;
    private static float p2_h;
    private static float p1_f;
    private static float p2_f;


    private Label heading;
    private static Label scoreLabel1;
    private static Label scoreLabel2;
    private static Label fuelLabel1;
    private static Label fuelLabel2;
    private Label p1Label;
    private Label p2Label;

    Arena a;
    public hud(SpriteBatch sb, Arena a) {
        //define our tracking variables

        this.a=a;
        p1_h=a.getP1().getT1().health;
        p2_h=a.getP2().getT1().health;
        p1_f=a.getP1().getT1().getFuel();
        p2_f=a.getP2().getT1().getFuel();

        viewport = new FitViewport(MyGdxGame.WIDTH, MyGdxGame.HIEGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        //define a table used to organize our hud's labels
        Table table = new Table();
        //Top-Align table
        table.top();
        //make the table fill the entire stage
        table.setFillParent(true);

        //define our labels using the String, and a Label style consisting of a font and color

         heading = new Label("TANK STARS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
         heading.setFontScale(3);

        scoreLabel1 =new Label(String.format("%03f", p1_h), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel2 =new Label(String.format("%03f", p2_h), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        fuelLabel1 =new Label(String.format("%03f", p1_f), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        fuelLabel2 =new Label(String.format("%03f", p2_f), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        p1Label = new Label("PLAYER1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        p2Label = new Label("PLAYER2", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        //public void update()



        //add our labels to our table, padding the top, and giving them all equal width with expandX
        table.add().expandX().padTop(10);
        table.add(heading).expandX();
        table.row();
        table.add(p1Label).expandX().padTop(10);
        table.add().expandX().padTop(10);
        table.add(p2Label).expandX().padTop(10);
        //add a second row to our table
        table.row();
        table.add(scoreLabel1).expandX();
        table.add().expandX();
        table.add(scoreLabel2).expandX();

        table.row();
        table.add(fuelLabel1).expandX();
        table.add().expandX();
        table.add(fuelLabel2).expandX();



        stage.addActor(table);
    }

    public void update(){
        p1_h=a.getP1().getT1().health;
        scoreLabel1.setText(String.format("%03f", p1_h));
        p2_h=a.getP2().getT1().health;
        scoreLabel2.setText(String.format("%03f", p2_h));
        p1_f=a.getP1().getT1().getFuel();
        fuelLabel1.setText(String.format("%03f", p1_f));
        p2_f=a.getP2().getT1().getFuel();
        fuelLabel2.setText(String.format("%03f", p2_f));
    }


    }

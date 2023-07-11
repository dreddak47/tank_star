package com.mygdx.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Arena;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tank.*;
import com.mygdx.game.gameSaver;
import com.mygdx.game.mainGame.mainGame;
import com.mygdx.game.player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main_menu implements Screen {
    MyGdxGame game;
    TextureAtlas ta_ng,ta_lsg,ta_b ;
    Sprite s_ng,s_lsg,s_b;
    TextureRegion tr_ng,tr_lsg,tr_b;
     long click;
    boolean main=true,he=false;
    boolean main1=false;
    public boolean click(int x1,int x2,int y1,int y2){
        return (Gdx.input.getX()<x1 || Gdx.input.getX()>x2) || (Gdx.input.getY()<y1 || Gdx.input.getY()>y2);
    }
    Texture t,v,va,e,ea;
    public Main_menu(MyGdxGame game){
        this.game = game;
        t=new Texture("mm1.jpg");
        v();
        e();
        ng();
        lsg();
        b();
        //c=new Texture(("coin.png"));
    }

    public void v(){
        v=new Texture("1v1.png");
        va=new Texture("1v1_a.png");
    }

    public void e(){
        e=new Texture("exit.png");
        ea=new Texture("exit_a.png");
    }

    public void ng(){
        ta_ng=new TextureAtlas(Gdx.files.internal("spritesheets/ng.atlas"));
        tr_ng=ta_ng.findRegion("ng");
        s_ng=new Sprite(tr_ng);
        s_ng.setPosition(745, -30);
    }

    public void a_ng(){
        if(main1){
            if (click(1060,1295,140,210)) {
                s_ng.setRegion(ta_ng.findRegion("ng"));
            } else {
                s_ng.setRegion(ta_ng.findRegion("ng_a"));
                if (Gdx.input.isTouched()) {
                    if(System.nanoTime()>=click+1000000000) {
                        game.setScreen(new selectTank(game));
                        this.dispose();
                    }
                }
            }
            s_ng.draw(game.batch);
        }
    }
    public void lsg(){
        ta_lsg=new TextureAtlas(Gdx.files.internal("spritesheets/lsg.atlas"));
        tr_lsg=ta_lsg.findRegion("lsg");
        s_lsg=new Sprite(tr_lsg);
        s_lsg.setPosition(745, -55);
    }
    public void a_lsg() throws IOException, ClassNotFoundException {  // last Saved Game;
        if(main1){
            if (click(1060,1295,280,350)){
                s_lsg.setRegion(ta_lsg.findRegion("lsg"));
            } else {
                s_lsg.setRegion(ta_lsg.findRegion("lsg_a"));
                if (Gdx.input.justTouched()) {
                    ObjectInputStream in = null;
                    try{
                        in= new ObjectInputStream(new FileInputStream("saved0.txt"));
                        gameSaver gs = (gameSaver) in.readObject();
                        Tank t1=getTank(0,gs.getName1());
                        t1.setPos(gs.getT1_x(),gs.getT1_y());
                        t1.setFuel(gs.getT1_fuel());
                        t1.health=gs.getT1_health();
                        Tank t2=getTank(1,gs.getName1());
                        t2.setPos(gs.getT2_x(),gs.getT2_y());
                        t2.setFuel(gs.getT2_fuel());
                        t2.health=gs.getT2_health();
                        player p1=new player(t1);
                        player p2 = new player(t2);
                        Arena a=new Arena(p1,p2);
                        a.turn=gs.turn;
                        game.setScreen(new mainGame(game,a));
                    }finally{
                        in.close();
                    }
                }
            }
            s_lsg.draw(game.batch);
        }
    }

    public void b(){
        ta_b=new TextureAtlas(Gdx.files.internal("spritesheets/b.atlas"));
        tr_b=ta_b.findRegion("back");
        s_b=new Sprite(tr_b);
        s_b.setPosition(745, -80);
    }

    public void a_b(){
        if(main1){
            if ((Gdx.input.getX() < 1060 || Gdx.input.getX() > 1295) || (Gdx.input.getY() < 420 || Gdx.input.getY() > 490)) {
                s_b.setRegion(ta_b.findRegion("back"));
            } else {
                s_b.setRegion(ta_b.findRegion("back_a"));
                if (Gdx.input.isTouched()) {
                    main=true;
                    main1=false;
                    he=false;
                }
            }
            s_b.draw(game.batch);
        }
    }

    public Tank getTank(int turn,String s){
        Tank t=new abram(turn);
        switch(s) {
            case "abram":
                t= new abram(turn);
                break;
            case "m1":
                t=new m1(turn);
                break;
            case "t_34":
                t=new t34(turn);
                break;
            case "buratino":
                t=new buratino(turn);
                break;
            case "froster":
                t= new froster(turn);
                break;
        }
        return t;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.507f, 0.3515f, 0.871f, 1);
        game.batch.begin();
        game.batch.draw(t,0,0);
        if(main) {
            if (click(1060,1295,140,210)){
                game.batch.draw(v, 900, MyGdxGame.HIEGHT / 20);
            } else {
                game.batch.draw(va, 900, MyGdxGame.HIEGHT / 20);
                if (Gdx.input.isTouched()) {
                    click=System.nanoTime();
                    main=false;
                    main1=true;
                }
            }
        }
        a_ng();
        try {
            a_lsg();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        a_b();
        if(main){
            if(click(1060,1295,286,360)){
                game.batch.draw(e,900,MyGdxGame.HIEGHT/20-10);
            }else{
                game.batch.draw(ea,900,MyGdxGame.HIEGHT/20-10);
                if(Gdx.input.isTouched()){
                    Gdx.app.exit();
                }
            }
        }

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

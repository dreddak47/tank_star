package com.mygdx.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Arena;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tank.*;
import com.mygdx.game.mainGame.mainGame;
import com.mygdx.game.player;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class selectTank implements Screen {
    static int k=1;
    boolean selected=false;
    boolean player2_selected=false;
    boolean play_active=false;
    boolean pl1=false,pl2=false;
    MyGdxGame game;
    TextureAtlas ta_p,ta_t,ta_s;
    TextureRegion tr_p,tr_t,tr_s;
    Sprite s_p,s_t,s_s;
    Texture st,l,r,p1,p2;
    player player1,player2;
    Tank t1,t2;
    public selectTank(MyGdxGame game){
        this.game=game;
        st=new Texture("st.png");
        l=new Texture("left.png");
        r=new Texture("right.png");
        p1=new Texture("p2.png");
        p2=new Texture("p1.png");
        p();
        tank();
        select();
    }
    public void select(){
        ta_s=new TextureAtlas(Gdx.files.internal("spritesheets/sel.atlas"));
        tr_s=ta_s.findRegion("sel");
        s_s=new Sprite(tr_s);
        s_s.setPosition(280, -140);
    }

    public void p1(){
        if(pl1) {
            game.batch.draw(p1, 250, 200);
            player1=new player(t1);
            player1.getT1().setPos(315/100f,250/100f);

        }
    }

    public void p2(){
        if(pl2) {
            game.batch.draw(p2, 1100, 200);
            player2=new player(t2);
            player2.getT1().setPos(1100/100f,250/100f);
        }
    }
    public void isSelected(){
        if(selected){
            if ((Gdx.input.getX() < 692 || Gdx.input.getX() > 892) || (Gdx.input.getY() < 440 || Gdx.input.getY() > 500)) {
                s_s.setRegion(ta_s.findRegion("sel_a"));
            } else {
                s_s.setRegion(ta_s.findRegion("sel_a1"));
                if (Gdx.input.justTouched()) {
                    if(player2_selected){
                        selected=false;
                        pl2=true;
                        t2=getTank(1);//new abram(1);
                        play_active=true;
                    }else{
                        selected=false;
                        pl1=true;
                        t1=getTank(0);//new abram(0);
                        player2_selected=true;
                    }

                }
            }
        }else{
            s_s.setRegion(ta_s.findRegion("sel"));
        }
    }
    public void p(){
        ta_p=new TextureAtlas(Gdx.files.internal("spritesheets/p.atlas"));
        tr_p=ta_p.findRegion("play");
        s_p=new Sprite(tr_p);
        s_p.setPosition(365, -250);
    }

    public void a_p(){
        if ((Gdx.input.getX() < 686 || Gdx.input.getX() > 863) || (Gdx.input.getY() < 490 || Gdx.input.getY() > 630)) {
            s_p.setRegion(ta_p.findRegion("play"));
        } else{
            if(play_active){
                s_p.setRegion(ta_p.findRegion("play_a"));
                if (Gdx.input.justTouched()) {
                    Arena a=new Arena(player1,player2);
                    game.setScreen(new mainGame(game,a));
                }
            }else {
                s_p.setRegion(ta_p.findRegion("play"));
            }
        }
    }
    public Tank getTank(int turn){
        Tank t=new abram(turn);
        switch(getName(k)) {
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
    public void tank(){
        ta_t=new TextureAtlas(Gdx.files.internal("spritesheets/t.atlas"));
        tr_t=ta_t.findRegion(getName(k));
        s_t=new Sprite(tr_t);
        s_t.setPosition(270, 50);
    }

    public void a_tank(){
        if(!selected && ((Gdx.input.getX() < 660 || Gdx.input.getX() > 900) || (Gdx.input.getY() < 200 || Gdx.input.getY() > 410))) {
            String s = getName(k);
            s_t.setRegion(ta_t.findRegion(s));
        }else{
            if (Gdx.input.justTouched()) {
                String s = getName(k);
                s = s + "_a";
                s_t.setRegion(ta_t.findRegion(s));
                selected = true;
            }
        }
    }
    public String getName(int n){
        String s="";
        String arr[]= new String[]{"abram","t34","m1","buratino","froster"};
        s=arr[n-1];
        return s;
    }

    public void l(){//left
        if ((Gdx.input.getX() < 630 || Gdx.input.getX() > 658) || (Gdx.input.getY() < 296 || Gdx.input.getY() > 352)) {

        }else{
            if(Gdx.input.justTouched()) {
                if (k > 1) {
                    k--;
                } else {
                    k = 5;
                }
                selected = false;
            }
        }

    }

    public void r(){ //right
        if ((Gdx.input.getX() < 911 || Gdx.input.getX() > 938) || (Gdx.input.getY() < 296 || Gdx.input.getY() > 352)) {

        }else{
            if(Gdx.input.justTouched()) {
                if (k < 5) {
                    k++;
                } else {
                    k = 1;
                }
                selected = false;
            }

        }
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.507f, 0.3515f, 0.871f, 1);
        System.out.println(Gdx.input.getX() +" "+ Gdx.input.getY());
        game.batch.begin();
        game.batch.draw(st,350,250);
        game.batch.draw(l,380,150);
        game.batch.draw(r,660,150);
        l();
        r();
        isSelected();
        p1();
        p2();
        s_p.draw(game.batch);
        s_t.draw(game.batch);
        s_s.draw(game.batch);
        a_tank();
        a_p();
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

package com.mygdx.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Arena;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tank.Tank;
import com.mygdx.game.Tank.abram;
import com.mygdx.game.mainGame.mainGame;
import com.mygdx.game.player;

public class LoadingScreen implements Screen {
    MyGdxGame game;
    int pt=0;
    public final float actionBeginTime=System.nanoTime();
    Texture MM,CTP_ACTIVE,CTP_INACTIVE;
    float x = game.WIDTH/2-310+35;           //calculated x co-ordinate for click_to_play_button
    //BitmapFont font = new BitmapFont();
    public LoadingScreen(MyGdxGame game){
        this.game=game;
        MM =new Texture("main_menu.jpg");
        CTP_ACTIVE=new Texture("click_to_play.png");
        CTP_INACTIVE=new Texture("click_to_play1.png");
    }

    public boolean click(int x1,int x2,int y1,int y2){
        return (Gdx.input.getX()<x1 || Gdx.input.getX()>x2) || (Gdx.input.getY()<y1 || Gdx.input.getY()>y2);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);
        game.batch.begin();

        float elapsedTime = System.nanoTime()-actionBeginTime;

        game.batch.draw(MM, 0, 0);

        //font.draw(game.batch, "Hello World!", 10, 10);
        int k= (int)(elapsedTime/600099999);  //used to create simple Play button fade-in and fade-out animation

        if(k%2==0){
            if(click(643,919,528,571)){
                game.batch.draw(CTP_INACTIVE, x, -100);
            }else{
                game.batch.draw(CTP_ACTIVE, x, -100);
                if(Gdx.input.isTouched()){
                    game.setScreen(new Main_menu(game));
                    this.dispose();
                }
            }
        }
        pt=k;
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

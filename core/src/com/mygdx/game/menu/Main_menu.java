package com.mygdx.game.menu;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;

public class Main_menu implements Screen {
    MyGdxGame game;
    Texture t;
    public Main_menu(MyGdxGame game){
        this.game = game;
        t=new Texture("badlogic.jpg");
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 0, 1);
        game.batch.begin();
        game.batch.draw(t,0,0);
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

package com.mygdx.game.mainGame;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Arena;
import com.mygdx.game.MyGdxGame;

public class mainGame implements Screen{
    MyGdxGame game;
    Arena a;
    Texture t,pt;

    public mainGame(MyGdxGame game, Arena a){
        this.game=game;
        this.a=a;
        t=new Texture("Game.jpg");
        pt=new Texture("pause.jpg");
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);
        game.batch.begin();
        game.batch.draw(t, 0, 0);
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

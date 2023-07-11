package com.mygdx.game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Tank.Tank;
import com.mygdx.game.Tank.abram;
import com.mygdx.game.mainGame.mainGame;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;


public class unitTest {
        @Test
        public void testp(){
            MyGdxGame game = new MyGdxGame();
            SpriteBatch batch=new SpriteBatch();
            game.batch=batch;
            Tank t1=new abram(0);

            Tank t2=new abram(1);

            player p1 = new player(t1);
            player p2=new player(t2);
            Arena a=new Arena(p1,p2);
            mainGame mg=new mainGame(game,a);
            t1.weapon.setAngle(0.5f);
            t1.weapon.setPower(5f);
            game.setScreen(mg);

            a.shoot();
            assertTrue(mg.isCollided());

    }
}

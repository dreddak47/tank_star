package com.mygdx.game.mainGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Tank.Tank;
import com.mygdx.game.weapons;

public class worldContactListener implements ContactListener {
    weapons weapon;
    mainGame game;

    public worldContactListener(mainGame mainGame) {
        this.game=mainGame;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fa= contact.getFixtureA();
        Fixture fb= contact.getFixtureB();

        if(fa.getUserData() instanceof weapons){
            weapon=(weapons) fa.getUserData();
            if(fb.getUserData() instanceof Tank){
                Tank tank=(Tank)fb.getUserData();
                tank.decreaseHealth(weapon);
            }else{
                game.detectDestruction(weapon);
            }
            game.collided=true;

        }else if(fb.getUserData() instanceof weapons){
                weapon=(weapons) fb.getUserData();
                if(fa.getUserData() instanceof Tank){
                    Tank tank=(Tank)fa.getUserData();
                    tank.decreaseHealth(weapon);

                }else{
                    game.detectDestruction(weapon);
                }
                game.collided=true;



        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}

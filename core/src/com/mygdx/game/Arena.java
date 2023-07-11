package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Tank.Tank;

import java.io.Serializable;

public class Arena {
    private player p1;
    private player p2;
    public int turn;

    public Arena(player p1,player p2){
        this.p1=p1;
        this.p2=p2;
        turn=0;

    }

    public player getP1() {
        return p1;
    }

    public player getP2() {
        return p2;
    }

    public Tank TurnTank(){
        if(turn==0){
            return p1.getT1();
        }else{
            return p2.getT1();
        }
    }

    public Tank TurnoppTank(){
        if(turn==0){
            return p2.getT1();
        }else{
            return p1.getT1();
        }
    }

    public weapons shoot(){
        weapons weap=TurnTank().shoot();
        return weap;
    }

    public void changeTurn(){
        if(turn==0){
            turn=1;
            p2.getT1().createWeapon();
        }else{
            turn=0;
            p1.getT1().createWeapon();
        }
    }


}

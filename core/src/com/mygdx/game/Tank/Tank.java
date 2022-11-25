package com.mygdx.game.Tank;
import com.mygdx.game.weapons;

import java.util.*;

public class Tank {
    //List<weapons> WpnList=new ArrayList<>();
    float POS_X;
    float POS_Y;
    int health;
    int fuel;
    String name;
    int defensive_shield;



    protected Tank(String name){
        this.name=name;
        this.health=100;
        this.fuel=100;
    }

    protected Tank(float POS_X,float POS_Y,int health,int fuel){
        this.POS_Y=POS_Y;
        this.POS_X=POS_X;
        this.health=health;
    }

    public void move(){

    }

    public void shoot(weapons weap, float angle, int power){

    }

    public void decreaseHealth(weapons weap){


    }

//    public float getAngle(){
//        return 0;
//    }
//
//    public void setAngle(float angle){
//
//    }


    public void setPos(float POS_X,float POS_Y) {
        this.POS_X = POS_X;
        this.POS_Y=POS_Y;

    }

    public float getPOS_X() {
        return POS_X;
    }

    public float getPOS_Y() {
        return POS_Y;
    }

    public void setFuel(int fuel){
        this.fuel=fuel;
    }
}

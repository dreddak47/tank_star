package com.mygdx.game;

public class weapons {
    float POS_X;
    float POS_Y;
    int power;

    weapons(){

    }

    public void projectile(){
        float posx=0;
        float posy=0;
        setPosX(posx);
        setPosY(posy);

    }

    public void setPosX(float POS_X){
        this.POS_X=POS_X;
    }

    public void setPosY(float POS_Y) {
        this.POS_Y = POS_Y;
    }

    public float getPOS_Y() {
        return POS_Y;
    }

    public float getPOS_X() {
        return POS_X;
    }

    //diffrent functions for creating weapons
}

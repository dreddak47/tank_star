package com.mygdx.game.Tank;

import com.badlogic.gdx.graphics.Texture;

public class t34 extends Tank{
    private Texture texture_p1;
    private Texture texture_p2;
    int player;
    protected t34(int player) {
        super("t34",player);
        this.player=player;
        texture_p1=new Texture("abram.png");
        texture_p2=new Texture("abram_r.png");
        setTex(player);
    }
    public void setTex(int p){
        if(p==0){
            super.te=texture_p1;
        }else{
            super.te=texture_p2;
        }
    }
}

package com.mygdx.game.Tank;

import com.badlogic.gdx.graphics.Texture;

public class m1 extends Tank{
    private Texture texture_p1;
    private Texture texture_p2;
    int player;
    public m1(int player) {
        super("m1",player);
        this.player=player;
        texture_p1=new Texture("m1.png");
        texture_p2=new Texture("m1_r.png");
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

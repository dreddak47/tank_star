package com.mygdx.game.Tank;

import com.badlogic.gdx.graphics.Texture;

public class t34 extends Tank{
    private Texture texture_p1;
    private Texture texture_p2;
    int player;
    public t34(int player) {
        super("t_34",player);
        this.player=player;
        texture_p1=new Texture("t_34.png");
        texture_p2=new Texture("t_34_r.png");
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

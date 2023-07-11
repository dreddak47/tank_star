package com.mygdx.game.Tank;

import com.badlogic.gdx.graphics.Texture;

public class froster extends Tank{
    private Texture texture_p1;
    private Texture texture_p2;
    int player;
    public froster(int player) {
        super("froster",player);
        this.player=player;
        texture_p1=new Texture("froster.png");
        texture_p2=new Texture("froster_r.png");
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

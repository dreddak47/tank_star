package com.mygdx.game.Tank;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.weapons;

import java.util.*;

public class abram extends Tank {
     //weapon list diffrent for each tank system
    private Texture texture_p1;
    private Texture texture_p2;
    int player;

    public abram(int player){
        //super(texture_p1);
        super("abram",player);
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

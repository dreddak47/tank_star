package com.mygdx.game.Tank;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.mainGame.mainGame;
import com.mygdx.game.weapons;

import java.io.Serializable;
import java.util.*;

public class Tank extends Sprite  {

    //List<weapons> WpnList=new ArrayList<>();
    float POS_X=0;
    float POS_Y=0;
    public int health;
    float fuel;
    String name;
    boolean move_w;
    int defensive_shield;
    public weapons weapon;
    public World world;
    public Body body1;
    //private mainGame screen;
    protected Fixture fixture;
    protected Texture te;
     int m=0;
    protected Tank(String name,int player){
        super(new TextureRegion(new Texture(name+".png")));
        this.m=player;
        this.name=name;
        this.health=100;
        this.fuel=100;
        move_w=false;

    }
    public void update(float dt){
        setPosition(body1.getPosition().x*100-getWidth()/2,body1.getPosition().y*100-getHeight()/2);
        if(move_w) {
            weapon.setPos(weapon.body1.getPosition().x, weapon.body1.getPosition().y);
            weapon.setPosition(weapon.body1.getPosition().x*100-weapon.getWidth()/2,weapon.body1.getPosition().y*100-weapon.getHeight()/2);
        }
    }
    public void defineTank(World world){
        //this.screen=screen;
        this.world=world;
        BodyDef bdef = new BodyDef();
        bdef.position.set(POS_X,POS_Y); //POS_X,POS_Y
        bdef.type = BodyDef.BodyType.DynamicBody;
        body1 = world.createBody(bdef);


        FixtureDef fdef = new FixtureDef();
        fdef.restitution=0;
        fdef.friction=1.5f;
        CircleShape shape = new CircleShape();
        shape.setRadius(30/100f);

        fdef.shape = shape;
        fixture=body1.createFixture(fdef);
        fixture.setUserData(this);

        setRegion(te);
    }

    protected Tank(float POS_X,float POS_Y,int health,int fuel){
        this.POS_Y=POS_Y;
        this.POS_X=POS_X;
        this.health=health;
    }

    public void move(float dis,float dt){
        if(fuel>0) {
            body1.applyLinearImpulse(new Vector2(dis, 0), body1.getWorldCenter(), true);
        }
        setFuel(Math.abs(body1.getLinearVelocity().x)*dt*15f);
        setPos(body1.getPosition().x,body1.getPosition().y);

    }

    public weapons shoot(){

        weapon.visible(this.world);

        move_w=true;
        weapon.projectile();
        return weapon;
    }
    public boolean getmove(){
        return move_w;
    }
    public void decreaseHealth(weapons weap){
        this.health-=weap.destruction*weap.getPower();

    }

    public weapons createWeapon(){
        move_w=false;
        weapon=new weapons();
        if(m==0) {
            weapon.setPos(body1.getPosition().x + (55 / 100f) , body1.getPosition().y + (40 / 100f));
        }else{
            weapon.setPos(body1.getPosition().x - (55 / 100f) , body1.getPosition().y + (100 / 100f));
        }
        return weapon;
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

    public void setFuel(float fuel){
        this.fuel-=fuel;
    }

    public float getFuel() {
        return fuel;
    }

    public String getName() {
        return name;
    }
}

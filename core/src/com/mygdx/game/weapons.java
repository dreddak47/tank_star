package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.io.Serializable;

public class weapons extends Sprite  {
    float POS_X;
    float POS_Y;
    float power;
    float angle;
    float speed=10;
    protected Fixture fixture;
    public float destruction;
    public World world;
    public Body body1;

    public weapons(){
        super(new Texture("missile.png"));
        this.power=1;
        this.angle=0;
        destruction=10;

    }

    public void projectile(){
        Vector2 startingVelocity=new Vector2(speed*power,speed*Math.abs(power));

        startingVelocity.rotateDeg(angle);
        body1.setLinearVelocity(startingVelocity);
    }

    public void setPos(float POS_X,float POS_Y){
        this.POS_X=POS_X;
        this.POS_Y=POS_Y;
    }


    public float getPOS_Y() {
        return POS_Y;
    }

    public float getPOS_X() {
        return POS_X;
    }

    public void setAngle(float angle){
        this.angle=angle;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public float getAngle() {
        return angle;
    }

    public float getPower() {
        return Math.abs(power);
    }

    public void visible(World world){
        this.world=world;
        BodyDef bdef = new BodyDef();

        bdef.position.set(POS_X,POS_Y); //POS_X,POS_Y
        bdef.type = BodyDef.BodyType.DynamicBody;

        body1 = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        fdef.restitution=0;
        CircleShape shape = new CircleShape();
        shape.setRadius(10/100f);

        fdef.shape = shape;
        fixture =body1.createFixture(fdef);
        fixture.setUserData(this);

        setRegion(getTexture());
//        CircleShape cs = new CircleShape();
//        shape.setRadius(10/100f);
//
//        fdef.shape=cs;
//        fdef.isSensor=true;


    }

    //diffrent functions for creating weapons
}

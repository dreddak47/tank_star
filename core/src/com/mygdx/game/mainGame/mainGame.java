package com.mygdx.game.mainGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Arena;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.weapons;

public class mainGame implements Screen{
    MyGdxGame game;
    Arena a;
    float power=0;
    float angle=0;

    // cam
    private OrthographicCamera cam;
    private Viewport port;

    //ground
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //box2D
    private World world;
    private Box2DDebugRenderer brenderer;
    Texture c1,c2,fire;
    float pos_cx=1230 ,pos_cy=115;

    public World getWorld(){
        return this.world;
    }
     public mainGame(MyGdxGame game, Arena a){

        this.game=game;
        cam = new OrthographicCamera();
        port=new FitViewport(MyGdxGame.WIDTH/100f,MyGdxGame.HIEGHT/100f,cam);

        c1=new Texture("big.png");
        c2=new Texture("small.png");
        fire=new Texture("fire.png");

        mapLoader=new TmxMapLoader();
        map=mapLoader.load("Finaltile.tmx");
        renderer=new OrthogonalTiledMapRenderer(map,1/100f);
        cam.position.set(port.getWorldWidth()/2,port.getWorldHeight()/2,0);


        world =new World(new Vector2(0,-10),true);
        brenderer = new Box2DDebugRenderer();

         BodyDef bdef = new BodyDef();
         PolygonShape shape = new PolygonShape();
         FixtureDef fdef = new FixtureDef();
         Body body;
         this.a=a;

         for(MapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
             Rectangle rect =((RectangleMapObject)object).getRectangle();

             bdef.type = BodyDef.BodyType.StaticBody;
             bdef.position.set((rect.getX() + rect.getWidth()/2)/100f,(rect.getY() + rect.getHeight()/2)/100f);

             body = world.createBody(bdef);

             shape.setAsBox((rect.getWidth()/2)/100f,(rect.getHeight()/2)/100f);
             fdef.shape=shape;

             body.createFixture(fdef);
         }
         a.getP1().getT1().setPos(315/100f,250/100f); // temp
         a.getP1().getT1().defineTank(this.world);
         a.getP1().getT1().createWeapon();
         a.getP2().getT1().setPos(1100/100f,250/100f); //temp
         a.getP2().getT1().defineTank(this.world);
         a.getP2().getT1().createWeapon();

         world.setContactListener(new worldContactListener(this));






//        t=new Texture("Game.jpg");
//        pt=new Texture("pause.jpg");
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        world.step(1/60f,6,2);
        handleInput(delta);
        a.getP1().getT1().update(delta);
        a.getP2().getT1().update(delta);
        ScreenUtils.clear(0, 0, 0, 1);
        //System.out.println(Gdx.input.getX()+" "+Gdx.input.getY());
        renderer.setView(cam);
        renderer.render();
        brenderer.render(world,cam.combined);
        cam.update();
        //game.batch.setProjectionMatrix(cam.combined);

        game.batch.begin();
        game.batch.draw(c1,1200,90);
        game.batch.draw(fire,1100,115);
        cursor();
        fire();

        a.getP1().getT1().draw(game.batch);
        a.getP2().getT1().draw(game.batch);
        game.batch.end();

    }

    public void cursor(){

            if (Math.pow(Gdx.input.getX() - 1252, 2) + Math.pow(Gdx.input.getY() - 580, 2) < 2800) {
                game.batch.draw(c2,Gdx.input.getX(),695-Gdx.input.getY());
                power=(float)(Math.pow(Gdx.input.getX() - 1252, 2) + Math.pow(Gdx.input.getY() - 580, 2))/1400;
                if(Gdx.input.getX()==1252){
                    if(Gdx.input.getY() - 580>=0){
                        angle=(float)Math.PI/2*-1;
                    }else{
                        angle=(float)Math.PI/2;
                    }
                }else if(Gdx.input.getY()==580){
                    angle=0;
                }else{
                    angle=(float)Math.atan(((580-Gdx.input.getY())/(Math.abs(Gdx.input.getX() - 1252))));
                }


                if(Gdx.input.justTouched()){
                    if(Gdx.input.getX()<=1252){
                        power=-1*power;
                    }
                    a.TurnTank().weapon.setAngle(angle);
                    a.TurnTank().weapon.setPower(power);
                    System.out.println(angle+" "+power);
                }
            }else{
                game.batch.draw(c2,pos_cx,pos_cy);
            }

    }

    public void fire(){
        if((Gdx.input.getX()<1100||Gdx.input.getX()>1200) && (Gdx.input.getY()<560||Gdx.input.getX()>602)){

        }else{
            if(Gdx.input.justTouched() || Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                a.shoot();
                a.changeTurn();
            }
        }
    }
    public void handleInput(float dt){

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && a.TurnTank().body1.getLinearVelocity().x <=3){
            a.TurnTank().move(0.2f,dt);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && a.TurnTank().body1.getLinearVelocity().x >=-3){
            a.TurnTank().move(-0.2f,dt);
        }
    }
    @Override
    public void resize(int width, int height) {
        port.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        game.batch.dispose();
    }

    public void detectDestruction(weapons weapon){
        float x=a.TurnoppTank().getPOS_X();
        //float y=a.TurnoppTank().getPOS_Y();
        if(weapon.getPOS_X()-x<40){
            a.TurnoppTank().health-=(float)((weapon.getPOS_X()-x)*weapon.getPower()*weapon.destruction/80);
        }
    }
}

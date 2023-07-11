package com.mygdx.game;

import java.io.*;

public class gameSaver implements Serializable {

    public int turn;
    float t1_x,t1_y;
    float t2_x,t2_y;
    float t1_fuel,t2_fuel;
    int t1_health,t2_health;
    String name1,name2;




    public gameSaver(){

    }

    public void save(Arena a){
        turn=a.turn;
        t1_x=a.getP1().getT1().getPOS_X();
        t2_x=a.getP2().getT1().getPOS_X();
        t1_y=a.getP1().getT1().getPOS_Y();
        t2_y=a.getP2().getT1().getPOS_Y();
        t1_fuel=a.getP1().getT1().getFuel();
        t2_fuel=a.getP2().getT1().getFuel();
        t1_health=a.getP1().getT1().health;
        t2_health=a.getP2().getT1().health;
        name1=a.getP1().getT1().getName();
        name2=a.getP2().getT1().getName();
    }
    public static int k=0;
    String s;

    public void sg() throws IOException {
        ObjectOutputStream out=null;
        //s="load/saved"+(String.format("%01d",k)+".txt");
        out=new ObjectOutputStream(new FileOutputStream("saved0.txt"));
        out.writeObject(this);
        out.close();
        k++;
    }

    public float getT1_x() {
        return t1_x;
    }

    public float getT1_y() {
        return t1_y;
    }

    public float getT2_x() {
        return t2_x;
    }

    public float getT2_y() {
        return t2_y;
    }

    public float getT1_fuel() {
        return t1_fuel;
    }

    public float getT2_fuel() {
        return t2_fuel;
    }

    public int getT1_health() {
        return t1_health;
    }

    public int getT2_health() {
        return t2_health;
    }

    public String getName1() {
        return name1;
    }

    public String getName2() {
        return name2;
    }
}

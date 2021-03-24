package SquirrelGame;

import java.util.Random;

public final class XY{
    private int x;
    private int y;
    private static Random random = new Random();

    public XY(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public String toString(){
        return "XY" + "x= " + x + "y= " + y;
    }






}

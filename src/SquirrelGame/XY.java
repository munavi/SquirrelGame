package SquirrelGame;

import java.util.Random;


public final class XY{
    private final int x;
    private final int y;
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

    public static XY randomDirection(){
        XY[] direction = {new XY(1,1), new XY(1,0) }; // muss ausgefüllt sein
        return direction[random.nextInt(direction.length)];
    }

    public double calcDistance(XY loc){
        int distanceX = loc.getX() - getX();
        int distanceY = loc.getY() - getY();
        double distanceTotal = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        return distanceTotal;
    }

    public XY add(XY xy){
        return new XY(xy.x + this.x, xy.y + this.y);
    }






}

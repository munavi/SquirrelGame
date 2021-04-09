package de.hsa.fatsquirrel;

import java.util.Random;


public final class XY {
    private final int x;
    private final int y;
    private static final Random random = new Random();

    public XY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return "XY " + " x= " + y + " y= " + x;		//Nicolas: x und y vertauscht. Erklärung: Bsp.: Array[0][5]; Arrayposition in 2d im Kopf vorstellen: die nullte Zeile, und fünftes Element. toString soll also sagen: x=5 y=0 (wenn wir den Origin des Koordinatensystems links oben haben)
    }												//Sollte man die Vertauschung vielleicht generell machen(also oben im Konstruktor)?
    												//oder bin ich doof? (es ist mir aufgefallen als ich den Board Konstruktor gemacht habe und die Entity positionen auf der Konsole gelesen habe)
    public static XY randomDirection() {
        XY[] direction = {new XY(0, 1), new XY(1, 0), new XY(0, -1), new XY(-1, 0)};
        //  new XY(1, 1), new XY(-1, -1), new XY(-1, 1), new XY(1, -1)
        return direction[random.nextInt(direction.length)];
    }

    public double calcDistance(XY loc) {
        int distanceX = loc.getX() - getX();
        int distanceY = loc.getY() - getY();
        return Math.sqrt(distanceX * distanceX + distanceY * distanceY);
    }

    public XY add(XY xy) {
        return new XY(xy.x + this.x, xy.y + this.y);
    }


    public boolean equals(Object o){
        if(this == o)
            return true;
        if( o == null || getClass() != o.getClass())
            return false;
        XY xy = (XY)o;
        return this.x == xy.x && this.y  == xy.y;
    }

    

}

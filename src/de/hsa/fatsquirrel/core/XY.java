package de.hsa.fatsquirrel.core;

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
        return "XY " + " x= " + x + " y= " + y;
    }


    public static XY randomDirection() {
        XY[] direction = {new XY(0, 1), new XY(1, 0), new XY(0, -1), new XY(-1, 0)};
        //  new XY(1, 1), new XY(-1, -1), new XY(-1, 1), new XY(1, -1)
        return direction[random.nextInt(direction.length)];
    }

    public static XY getRandomPosition(int xSize, int ySize) {
        Random random = new Random();
        int x = random.nextInt(xSize);
        int y = random.nextInt(ySize);
        return new XY(x, y);
    }

    public double calcDistance(XY loc) {
        int distanceX = loc.getX() - getX();
        int distanceY = loc.getY() - getY();
        return Math.sqrt(distanceX * distanceX + distanceY * distanceY);
    }

    public XY add(XY other) {
        return new XY(other.x + this.x, other.y + this.y);
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        XY xy = (XY) o;
        return this.x == xy.x && this.y == xy.y;
    }

    public boolean equals(XY other) {
        return this.x == other.getX() && this.y == other.getY();
    }

}

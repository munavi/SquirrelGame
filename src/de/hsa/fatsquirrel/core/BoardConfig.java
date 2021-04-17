package de.hsa.fatsquirrel.core;

public class BoardConfig {
    private XY size;
    private int wallCount;
    private final int width;
    private final int height;
    private final int numberWalls, numberGoodBeasts, numberGoodPlants, numberBadPlants, numberBadBeasts;


    public BoardConfig(){
        size = new XY(10,10);
        this.width = size.getX();
        this.height = size.getY();
        this.wallCount= 2 * (this.height + this.width) - 4;
        this.numberWalls = wallCount;
        numberBadPlants = 5;
        numberBadBeasts = 5;
        numberGoodPlants = 5;
        numberGoodBeasts = 5;
    }

    public XY getSize(){
        return size;
    }

    public int getWallCount(){
        return wallCount;
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getNumberWalls() {
        return numberWalls;
    }

    public int getNumberGoodBeasts() {
        return numberGoodBeasts;
    }

    public int getNumberGoodPlants() {
        return numberGoodPlants;
    }

    public int getNumberBadPlants() {
        return numberBadPlants;
    }

    public int getNumberBadBeasts() {
        return numberBadBeasts;
    }


}

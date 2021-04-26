package de.hsa.fatsquirrel.core;
import java.util.HashMap;

public class BoardConfig {
    private XY size;
    private int wallCount;
    private final int width;
    private final int height;
    private final int numberWalls, numberGoodBeasts, numberGoodPlants, numberBadPlants, numberBadBeasts;


    public BoardConfig(){
        size = new XY(20,15);
        this.width = size.getX();
        this.height = size.getY();
        this.wallCount= 2 * (this.height + this.width) - 4;
        this.numberWalls = wallCount;
        numberBadPlants = 2;
        numberBadBeasts = 2;
        numberGoodPlants = 2;
        numberGoodBeasts = 2;
        
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

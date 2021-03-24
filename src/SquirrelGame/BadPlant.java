package SquirrelGame;

public class BadPlant extends Entity {
    public static final int START_ENERGY = -100;

    public BadPlant(int id, XY loc){
        super(id, START_ENERGY, loc);
    }
}

package SquirrelGame;

public class BadBeast extends Entity {
    public final static int START_ENERGY = -150;

    public BadBeast(int id, XY loc) {
        super(id, START_ENERGY, loc);
    }

}

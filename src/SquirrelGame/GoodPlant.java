package SquirrelGame;

public class GoodPlant extends Entity {
    public static final int START_ENERGY = 100;


    public GoodPlant(int id, XY loc) {
        super(id, START_ENERGY, loc);
    }

    public String toString() {
        return super.toString() + " of type core.GoodPlant ";
    }

    @Override
    public void nextStep() {
        this.location = this.location.add(XY.randomDirection());
    }
}

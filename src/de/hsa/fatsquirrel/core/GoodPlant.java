package de.hsa.fatsquirrel.core;

public class GoodPlant extends Entity {
    public static final int START_ENERGY = 100;


    public GoodPlant(int id, XY loc) {
        super(id, START_ENERGY, loc);
    }

    public int getStartEnergy() {
        return START_ENERGY;
    }

    public String toString() {
        return super.toString() + " of type core.GoodPlant " + " ID: "+ this.getId() + " Location: " + this.getPosition() + " Energy: " + this.getEnergy();
    }

}

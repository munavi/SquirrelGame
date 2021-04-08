package de.hsa.fatsquirrel;

public class GoodBeast extends Entity {
    public final static int START_ENERGY = 200;

    public GoodBeast(int id, XY loc){
        super(id, START_ENERGY, loc);
    }

    public int getStartEnergy() {
        return START_ENERGY;
    }

    public String toString() {
        return super.toString() + " of type GoodBeast " + " ID: "+ this.getId() + " Location: " + this.getPosition() + " Energy: " + this.getEnergy();
    }

    public void nextStep() {
        this.position = this.position.add(XY.randomDirection());
    }
}

package de.hsa.fatsquirrel;

public class BadBeast extends Entity {
    public final static int START_ENERGY = -150;

    public BadBeast(int id, XY loc) {
        super(id, START_ENERGY, loc);
    }

    public String toString() {
        return super.toString() + " of type BadBeast " + " ID: "+ this.getId() + " Location: " + this.getPosition() + " Energy: " + this.getEnergy();
    }
    public int getStartEnergy() {
        return START_ENERGY;
    }

    @Override
    public void nextStep() {
        this.position = this.position.add(XY.randomDirection());
    }
}

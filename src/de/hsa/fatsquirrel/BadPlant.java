package de.hsa.fatsquirrel;

public class BadPlant extends Entity {
    public static final int START_ENERGY = -100;

    public BadPlant(int id, XY loc){
        super(id, START_ENERGY, loc);
    }

    public int getStartEnergy() {
        return START_ENERGY;
    }


    public String toString() {
        return super.toString() + " of type BadPlant " + " ID: "+ this.getId() + " Location: " + this.getPosition() + " Energy: " + this.getEnergy();
    }

    @Override
    public void nextStep() {

    }
}
package SquirrelGame;

public class GoodBeast extends Entity {
    public final static int START_ENERGY = 200;

    public GoodBeast(int id, XY loc){
        super(id, START_ENERGY, loc);
    }

    public String toString() {
        return super.toString() + " of type GoodBeast " + " ID: "+ this.getId() + " Location: " + this.getLocation() + " Energy: " + this.getEnergy();
    }

    public void nextStep() {
        this.location = this.location.add(XY.randomDirection());
    }
}

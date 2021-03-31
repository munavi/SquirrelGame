package SquirrelGame;

public class Wall extends Entity{

    public static final int START_ENERGY = -10;

    public Wall(int id, XY loc){
        super(id, START_ENERGY, loc);
    }

    public String toString() {
        return super.toString() + " of type Wall" + " ID: "+ this.getId() + " Location: " + this.getLocation() + " Energy: " + this.getEnergy();
    }

    @Override
    public void nextStep() {
    }
}

package de.hsa.fatsquirrel.core;

public class GoodBeast extends Character {
    public final static int START_ENERGY = 200;
    private int waitTime = 4;

    public GoodBeast(int id, XY loc){
        super(id, START_ENERGY, loc);
    }

    public int getStartEnergy() {
        return START_ENERGY;
    }

    public String toString() {
        return super.toString() + " of type GoodBeast " + " ID: "+ this.getId() + " Location: " + this.getPosition() + " Energy: " + this.getEnergy();
    }

    public void nextStep(EntityContext context) {
        if (getWaitTime() == 0) {
            context.tryMove(this, XY.randomDirection());
        }
        else {
            reduceWaitTime();
        }
    }

    private void reduceWaitTime() {
        waitTime--;
    }


    public int getWaitTime() {
        return waitTime;
    }

    public void updateWaitTime() {
        waitTime = 3;
    }



}

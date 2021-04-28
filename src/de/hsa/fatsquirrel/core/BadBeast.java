package de.hsa.fatsquirrel.core;

public class BadBeast extends Character {

    private int waitTime = 4;
    private int remainingBites = 7;
    private final static int START_ENERGY = -150;

    public BadBeast(int id, XY loc) {
        super(id, START_ENERGY, loc);
    }

    public String toString() {
        return super.toString() + " of type BAD_BEAST " + " ID: " + this.getId() + " Location: " + this.getPosition() + " Energy: " + this.getEnergy();
    }

    public int getStartEnergy() {
        return START_ENERGY;
    }

    @Override
    public void nextStep(EntityContext context) {
        if (getWaitTime() == 0) {
            context.tryMove(this, XY.randomDirection());
        } else {
            reduceWaitTime();
        }

    }

    public int getWaitTime() {
        return waitTime;
    }

    public int getRemainingBites() {
        return remainingBites;
    }

    private void reduceWaitTime() {
        waitTime--;
    }

    public void reduceBites() {
        remainingBites--;
    }

    public void updateWaitTime() {
        waitTime = 3;
    }


}

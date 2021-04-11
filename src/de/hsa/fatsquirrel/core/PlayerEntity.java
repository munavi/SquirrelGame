package de.hsa.fatsquirrel.core;

public abstract class PlayerEntity extends Character {
    private int waitTime = 0;

    public PlayerEntity(int id, int startEnergy, XY startPos) {
        super(id, startEnergy, startPos);
    }

    public boolean collidesWith(Entity entity) {
        updateEnergy(entity.getEnergy());
        return super.collidesWith(entity);
    }

    public void paralyze() {
        waitTime = 3;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void reduceWaitTime() {
        waitTime--;
    }



}

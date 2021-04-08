package de.hsa.fatsquirrel;

public abstract class PlayerEntity extends Character {
    private static final int START_ENERGY = 0;

    private boolean isParalyzed;
    private MasterSquirrel master; /// wo soll es sein : hier oder in MiniSquirrel?


    public MasterSquirrel getMasterSquirrel() {
        return master;
    }

    public PlayerEntity(int id, int startEnergy, XY startPos) {
        super(id, startEnergy, startPos);
    }

    public void paralyze() {
        isParalyzed = true;
    }


}

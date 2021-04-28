package de.hsa.fatsquirrel.core;

public abstract class Character extends Entity {
    public Character(int id, int energy, XY loc) {
        super(id, energy, loc);
    }

    public abstract void nextStep(EntityContext context);
}

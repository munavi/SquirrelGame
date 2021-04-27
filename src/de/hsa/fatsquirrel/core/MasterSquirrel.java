package de.hsa.fatsquirrel.core;

import de.hsa.fatsquirrel.console.MoveCommand;
import de.hsa.fatsquirrel.core.*;

public abstract class MasterSquirrel extends PlayerEntity {
    private MoveCommand input;
    public final static int START_ENERGY = 1000;
    int counter;

    {
        counter = 0;
    }

    public MasterSquirrel(int id, XY loc) {
        super(id, START_ENERGY, loc);
    }


    public boolean isItMySquirrel(Entity e) {
        if (e instanceof MiniSquirrel) {
            return ((MiniSquirrel) e).getMaster() == this;
        }
        return false;
    }

    public void setInput(MoveCommand input) {
        this.input = input;
    }

    public void nextStep(EntityContext context) {
        if (getWaitTime() == 0) {
            context.tryMove(this, input.toXY());
        }
        else {
            reduceWaitTime();
        }
    }


    public String toString(){
        return super.toString() + " of type " + this.getClass()+ " ID: "+ this.getId() + " Location: " + this.getPosition() + " Energy: " + this.getEnergy();
    }

    public abstract void setMoveCommand(XY position);
}

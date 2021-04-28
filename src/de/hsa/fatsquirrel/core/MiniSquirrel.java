package de.hsa.fatsquirrel.core;

public class MiniSquirrel extends PlayerEntity{
    private static int START_ENERGY = 0;
    private MasterSquirrel master;


    public MiniSquirrel(int id, int energy, XY loc, MasterSquirrel master){
        super(id, energy, loc);
        this.master = master;
    }


    @Override
    public int getStartEnergy() { // TODO : es muss geändert werden
        return START_ENERGY;
    }

    public MasterSquirrel getMaster(){
        return master;
    }


    public void nextStep(EntityContext context) {
        updateEnergy(-1);
        if (getWaitTime() == 0) {
            context.tryMove(this, XY.randomDirection());
        }
        else {
            reduceWaitTime();
        }

    }

    public String toString(){
        return super.toString() + " of type " + this.getClass()+ " ID: "+ this.getId() + " Location: " + this.getPosition() + " Energy: " + this.getEnergy();
    }
}

package de.hsa.fatsquirrel;

public class MiniSquirrel extends PlayerEntity{
    private static int START_ENERGY = 0;
    private MasterSquirrel master;


    public MiniSquirrel(int id,int energy, XY loc, MasterSquirrel master){
        super(id, energy, loc);
    }


    @Override
    public int getStartEnergy() { // TODO : es muss geändert werden
        return START_ENERGY;
    }

    public MasterSquirrel getMaster(){
        return master;
    }



    public void nextStep() {
        this.position = this.position.add(XY.randomDirection());
    }
}

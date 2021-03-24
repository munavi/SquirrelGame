package SquirrelGame;

public class MiniSquirrel extends Squirrel{
    public MasterSquirrel master;

    public MiniSquirrel(int id,int energy, XY loc, MasterSquirrel master){
        super(id, energy, loc);
    }

    public MasterSquirrel getMaster(){
        return master;
    }

    public void nextStep() {
        this.location = this.location.add(XY.randomDirection());
    }
}

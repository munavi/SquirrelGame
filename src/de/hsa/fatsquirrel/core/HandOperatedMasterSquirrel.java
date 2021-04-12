package de.hsa.fatsquirrel.core;


public class HandOperatedMasterSquirrel extends MasterSquirrel {
    private XY moveCommand = new XY(0, 0);

    public HandOperatedMasterSquirrel(int id, XY loc) {
        super(id, loc);
    }


    @Override
    public int getStartEnergy() { // TODO es muss geändert werden
        return 0;
    }


    public void nextStep(EntityContext context) {
        if (getWaitTime() == 0 ) {
            context.tryMove(this, moveCommand);
        }
        else {
            reduceWaitTime();
        }
    }

    public void setMoveCommand(XY moveCommand) {
        this.moveCommand = moveCommand;
    }

    public XY getMoveCommand(){
        return moveCommand;
    }






}



package de.hsa.fatsquirrel.core;


public class HandOperatedMasterSquirrel extends MasterSquirrel {
    XY newLocation;
    private XY moveCommand = new XY(0, 0);





    public HandOperatedMasterSquirrel(int id, XY loc) {
        super(id, loc);
    }


    @Override
    public int getStartEnergy() { // TODO es muss geändert werden
        return 0;
    }


//    @Override
//    public void nextStep(EntityContext context) {
//        System.out.println("Eingabe: ");
//        Scanner scanner = new Scanner(System.in);
//        char c = scanner.next(".").charAt(0);
//        newLocation = switch (c) {
//// left
//            case 'a' -> getPosition().add(new XY(-1, 0));
////up
//            case 'w' -> getPosition().add(new XY(0, 1));
//// right
//            case 'd' -> getPosition().add(new XY(1, 0));
//// down
//            case 's' -> getPosition().add(new XY(0, -1));
//            default -> throw new IllegalStateException("Unexpected value: " + c);
//        };
//    }

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



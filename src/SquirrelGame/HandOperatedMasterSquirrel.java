package SquirrelGame;


import java.util.Scanner;

public class HandOperatedMasterSquirrel extends MasterSquirrel {


    public HandOperatedMasterSquirrel(int id, XY loc) {
        super(id, loc);
    }

    @Override
    public void nextStep() {
        Scanner scanner = new Scanner(System.in);
        XY newLocation;
        char c = scanner.next(".").charAt(0);
        switch (c) {
            case 'a': // left
                newLocation = getLocation().add(new XY(-1, 0));
                break;
            case 'w': //up
                newLocation = getLocation().add(new XY(0,1));
                break;
            case 'd':// right
                newLocation = getLocation().add(new XY(1,0));
                break;
            case 's': // down
                newLocation = getLocation().add(new XY(0,-1));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + c);
        }
    }


}



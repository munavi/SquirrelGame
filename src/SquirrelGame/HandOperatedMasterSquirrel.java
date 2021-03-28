package SquirrelGame;


import java.util.Scanner;

public class HandOperatedMasterSquirrel extends MasterSquirrel {
    XY newLocation;

    public HandOperatedMasterSquirrel(int id, XY loc) {
        super(id, loc);
    }


    @Override
    public void nextStep() {
        Scanner scanner = new Scanner(System.in);
        char c = scanner.next(".").charAt(0);
        newLocation = switch (c) {
// left
            case 'a' -> getLocation().add(new XY(-1, 0));
//up
            case 'w' -> getLocation().add(new XY(0, 1));
// right
            case 'd' -> getLocation().add(new XY(1, 0));
// down
            case 's' -> getLocation().add(new XY(0, -1));
            default -> throw new IllegalStateException("Unexpected value: " + c);
        };
    }



}



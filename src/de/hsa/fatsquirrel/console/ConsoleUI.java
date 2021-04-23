package de.hsa.fatsquirrel.console;

import de.hsa.fatsquirrel.core.*;
import de.hsa.fatsquirrel.util.ui.console.Command;
import de.hsa.fatsquirrel.util.ui.console.CommandScanner;
import de.hsa.fatsquirrel.util.ui.console.ScanException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class ConsoleUI implements UI {
    private Command puffer = null;
    private final PrintStream outputStream = System.out;
    private final BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
    private final CommandScanner commandScanner = new CommandScanner(GameCommandType.values(), inputReader, outputStream);


    @Override
    public void message(String message) {
        System.out.println(message);
    }

    public void render(BoardView view) {
        for (int y = 0; y < view.getSize().getY(); y++) {
            for (int x = 0; x < view.getSize().getX(); x++) {
                System.out.print("");
                Entity entity = view.getEntityType(x, y);

                if (entity instanceof Wall) {
                    System.out.print("[]");
                } else if (entity instanceof GoodBeast) {
                    System.out.print("GP");
                } else if (entity instanceof BadPlant) {
                    System.out.print("BP");
                } else if (entity instanceof GoodBeast) {
                    System.out.print("GB");
                } else if (entity instanceof BadBeast) {
                    System.out.print("BB");
                } else if (entity instanceof HandOperatedMasterSquirrel) {
                    System.out.print("Ma");
                } else if (entity instanceof MiniSquirrel) {
                    System.out.print("Mi");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }


    }

    public Command getCommand() {
        Command command = puffer;
        puffer = null;
        return command;

    }

    @Override
    public void inputLoop() {
        while (true) {
            try {
                puffer = commandScanner.next();
            } catch (ScanException e) {
                System.out.println(e.getMessage());
            }
        }
    }




//    @Override
//    public Command getCommand() {
//        while (true) {
//            try {
//                Command puffer = null;
//                return puffer = commandScanner.next();
//            } catch (ScanException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//    }
}







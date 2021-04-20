package de.hsa.fatsquirrel.console;

import de.hsa.fatsquirrel.core.*;
import de.hsa.fatsquirrel.util.ui.console.Command;
import de.hsa.fatsquirrel.util.ui.console.CommandScanner;
import de.hsa.fatsquirrel.util.ui.console.ScanException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class ConsoleUI implements UI {
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
                System.out.print("|");
                EntityType entity = view.getEntityType(x, y);

                if (entity == EntityType.WALL) {
                    System.out.print("WA");
                } else if (entity == EntityType.GOOD_PLANT) {
                    System.out.print("GP");
                } else if (entity == EntityType.BAD_PLANT) {
                    System.out.print("BP");
                } else if (entity == EntityType.GOOD_BEAST) {
                    System.out.print("GB");
                } else if (entity == EntityType.BAD_BEAST) {
                    System.out.print("BB");
                } else if (entity == EntityType.MASTER_SQUIRREL) {
                    System.out.print("Ma");
                } else if (entity == EntityType.MINI_SQUIRREL) {
                    System.out.print("Mi");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }


    }

    @Override
    public Command getCommand() {
        while (true) {
            try {
                Command puffer = null;
                return puffer = commandScanner.next();
            } catch (ScanException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}







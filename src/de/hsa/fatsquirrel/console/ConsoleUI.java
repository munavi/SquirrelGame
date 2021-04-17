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
    private CommandScanner commandScanner = new CommandScanner(GameCommandType.values(), inputReader, outputStream);


    @Override
    public void message(String message) {
        System.out.println(message);
    }


    @Override
    public void render(BoardView view) {
        for (int y = 0; y < view.getSize().getY(); y++) {
            for (int x = 0; x < view.getSize().getX(); x++) {
                System.out.print("|");
                EntityType entity = view.getEntityType(x, y);

                if (entity == EntityType.Wall) {
                    System.out.print("WA");
                }
                else if (entity == EntityType.GoodPlant) {
                    System.out.print("GP");
                }
                else if (entity== EntityType.BadPlant) {
                    System.out.print("BP");
                }
                else if (entity == EntityType.GoodBeast) {
                    System.out.print("GB");
                }
                else if (entity == EntityType.BadBeast) {
                    System.out.print("BB");
                }
                else if (entity == EntityType.MasterSquirrel) {
                    System.out.print("Ma");
                }
                else if (entity == EntityType.MiniSquirrel) {
                    System.out.print("Mi");
                }
                else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }


    }

    @Override
    public Command getCommand() {
        Command command = puffer;
        puffer = null;
        return command;

    }



//    private final Scanner scanner = new Scanner(System.in);
//    public MoveCommand command;
//
//    @Override
//    public MoveCommand getCommand() {
//        System.out.println("Please insert Move Direction :");
//        char moveDirection = scanner.next().charAt(0);
//        if(moveDirection == 'a' || moveDirection == 's' || moveDirection == 'd' || moveDirection == 'w') {
//            switch (moveDirection) {
//                case 'a':
//                    return command = MoveCommand.LEFT;
//                case 's':
//                    return command = MoveCommand.DOWN;
//                case 'd':
//                    return command = MoveCommand.RIGHT;
//                case 'w':
//                    return command = MoveCommand.UP;
//            }
//        }
//
//        System.out.println("Invalid Input !");
//        return getCommand();
//    }
//
//    @Override
//    public void render(BoardView view) {
//        view.visualize();
//
//
//    }
//
//    private String toString(EntityType entity) {
//        switch (entity) {
//            case GoodBeast:
//                return "GB";
//            case BadBeast:
//                return "BB";
//            case GoodPlant:
//                return "GP";
//            case BadPlant:
//                return "BP";
//            case Wall:
//                return "WA";
//            case MasterSquirrel:
//                return "MS";
//            case MiniSquirrel:
//                return "mS";
//            default:
//                return null;
//        }
//    }




}



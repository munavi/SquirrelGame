package de.hsa.fatsquirrel.console;

import de.hsa.fatsquirrel.core.BoardView;
import de.hsa.fatsquirrel.core.EntityType;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUI implements UI {
    private final Scanner scanner = new Scanner(System.in);
    public MoveCommand command;

    @Override
    public MoveCommand getCommand() {
        System.out.println("Please insert Move Direction :");
        char moveDirection = scanner.next().charAt(0);
        if(moveDirection == 'a' || moveDirection == 's' || moveDirection == 'd' || moveDirection == 'w') {
            switch (moveDirection) {
                case 'a':
                    return command = MoveCommand.LEFT;
                case 's':
                    return command = MoveCommand.DOWN;
                case 'd':
                    return command = MoveCommand.RIGHT;
                case 'w':
                    return command = MoveCommand.UP;
            }
        }

        System.out.println("Invalid Input !");
        return getCommand();
    }

    @Override
    public void render(BoardView view) {
        view.visualize();


    }

    private String toString(EntityType entity) {
        switch (entity) {
            case GoodBeast:
                return "GB";
            case BadBeast:
                return "BB";
            case GoodPlant:
                return "GP";
            case BadPlant:
                return "BP";
            case Wall:
                return "WA";
            case MasterSquirrel:
                return "MS";
            case MiniSquirrel:
                return "mS";
            default:
                return null;
        }
    }




}



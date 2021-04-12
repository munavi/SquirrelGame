package de.hsa.fatsquirrel.console;

import de.hsa.fatsquirrel.core.BoardView;
import de.hsa.fatsquirrel.core.EntityType;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUI implements UI {
    private Scanner scanner = new Scanner(System.in);
    private String FIELD_SEPERATOR = "|";
    public MoveCommand command;

    @Override
    public MoveCommand getCommand() throws InputMismatchException {
        System.out.println("Please insert Move Direction :");
        char moveDirection = scanner.next().charAt(0);
        if(moveDirection == 'a' || moveDirection == 's' || moveDirection == 'd' || moveDirection == 'w') {
            switch (moveDirection) {
                case 'a':
                    return command = command.LEFT;
                case 's':
                    return command = command.DOWN;
                case 'd':
                    return command = command.RIGHT;
                case 'w':
                    return command = command.UP;
            }
        }else {
            System.out.println("Invalid Input !");
            getCommand();
        }
            return null;
    }

    @Override
    public void render(BoardView view) {
//        String result = "";
//        for (int y = 0; y < view.getSize().getY(); y++) {
//            for (int x = 0; x < view.getSize().getX(); x++) {
//                EntityType iterField = view.getEntityType(x, y);
//                if (iterField == null)
//                    result += "__";
//                else
//                    result += toString(iterField) + FIELD_SEPERATOR;
//            }
//        }
        view.toString();


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



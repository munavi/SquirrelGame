package de.hsa.fatsquirrel.core;

import de.hsa.fatsquirrel.console.GameCommandType;
import de.hsa.fatsquirrel.console.MoveCommand;

public class State {
    private final Board board;
    private XY moveCommand;
    private int highScore;
    private FlattenedBoard flatBoard;

    public State(Board board) {
        this.board = board;
        flatBoard = board.flatten();
    }

    public Board getBoard() {
        return board;
    }

    public void update() {
        for (int x = 0; x < board.getEntitySet().getContainer().length; x++) {
            Entity iterField = board.getEntitySet().getElement(x);
            EntityType type = EntityType.fromEntity(iterField);
            switch (type) {
                case EmptyField:
                    continue;
                case HandOperatedMasterSquirrel:
                    ((HandOperatedMasterSquirrel) iterField).setMoveCommand(moveCommand);
                    setMoveCommand(null);
                    ((Character) iterField).nextStep(flattenedBoard());
                    break;
                case BadBeast:
                case GoodBeast:
                case MiniSquirrel:


                    for (int i = 0; i < board.getEntitySet().getContainer().length; i++) {
                        if (board.getEntitySet().getContainer()[i] instanceof HandOperatedMasterSquirrel) {
                            System.out.println("Your Energy is: " + board.getEntitySet().getElement(i).getEnergy());
                        }
                    }
            }
        }



//            if (iterField == null)
//                continue;
//            // If NOT NULL
//            if (iterField instanceof HandOperatedMasterSquirrel) {
//                ((HandOperatedMasterSquirrel) iterField).setMoveCommand(input.toXY());
//                ((HandOperatedMasterSquirrel) iterField).nextStep(state.flattenedBoard());
//            }
//            if (Character.class.isInstance(iterField) && !(iterField instanceof HandOperatedMasterSquirrel))
//                ((Character) iterField).nextStep(state.flattenedBoard());
//
//        }
//        for (int i = 0; i < getEntitySet().getContainer().length; i++) {
//            if (getEntitySet().getContainer()[i] instanceof HandOperatedMasterSquirrel) {
//                System.out.println("Your Energy is: " + getEntitySet().getElement(i).getEnergy());
//            }
        }

    public FlattenedBoard flattenedBoard() {
        return flatBoard;
    }

    public XY getMoveCommand() {
        return moveCommand;
    }

    public void setMoveCommand(XY command) {
        moveCommand = command;
    }
}

    // Wurde im Board verschoben

//    public void update() {
//        for (int x = 0; x < board.getEntitySet().getContainer().length; x++) {
//            Entity iterField = board.getEntitySet().getElement(x);
//            if (iterField == null)
//                continue;
//            // If NOT NULL
//            if (iterField instanceof HandOperatedMasterSquirrel) {
//                ((HandOperatedMasterSquirrel) iterField).setMoveCommand(input.toXY());
//                ((HandOperatedMasterSquirrel) iterField).nextStep(flattenedBoard());
//            }
//            if (Character.class.isInstance(iterField) && !(iterField instanceof HandOperatedMasterSquirrel))
//                ((Character) iterField).nextStep(flattenedBoard());
//
//        }
//        for (int i = 0; i < board.getEntitySet().getContainer().length; i++) {
//            if (board.getEntitySet().getContainer()[i] instanceof HandOperatedMasterSquirrel) {
//                System.out.println("Your Energy is: " + board.getEntitySet().getElement(i).getEnergy());
//            }
//        }
//
//    }




//    public FlattenedBoard flattenedBoard() {
//        return board.flatten();
//    }
//
//    public void setInput(MoveCommand command) {
//        input = command;
//    }



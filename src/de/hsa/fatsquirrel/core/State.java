package de.hsa.fatsquirrel.core;

import de.hsa.fatsquirrel.console.GameCommandType;

public class State {
    private final Board board;
    private XY moveCommand;
    private int highScore;
    private FlattenedBoard flatBoard;
    private GameCommandType input;

    public State(Board board) {
        this.board = board;
        flatBoard = board.flatten();
    }

    public Board getBoard() {
        return board;
    }


    public void setInput(GameCommandType commandType) {
        input = commandType;

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


//    public void update() {
//        for (int x = 0; x < board.getEntitySet().getContainer().length; x++) {
//            Entity iterField = board.getEntitySet().getElement(x);
//            if (iterField == null)
//                continue;
//            // If NOT NULL
//            if (iterField instanceof HandOperatedMasterSquirrel) {
//                ((HandOperatedMasterSquirrel) iterField).nextStep(flattenedBoard());
//            }
//            if (Character.class.isInstance(iterField) && !(iterField instanceof HandOperatedMasterSquirrel))
//                ((Character) iterField).nextStep(flattenedBoard());
//
//
//        }
//    }
}









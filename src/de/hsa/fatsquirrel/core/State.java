package de.hsa.fatsquirrel.core;

import de.hsa.fatsquirrel.console.MoveCommand;

public class State {
    private final Board board;
    private MoveCommand input;
    private int highScore;

    public State(Board board){
        this.board = board;
    }

    public Board getBoard(){
        return board;
    }

    public void update(){
        for (int x = 0; x < board.getEntitySet().getContainer().length ; x++) {
                Entity iterField = board.getEntitySet().getElement(x);
                if (iterField == null)
                    continue;
                // If NOT NULL
                if (iterField instanceof HandOperatedMasterSquirrel) {
                    ((HandOperatedMasterSquirrel) iterField).setMoveCommand(input.toXY());
                    ((HandOperatedMasterSquirrel) iterField).nextStep(flattenedBoard());
                }
                if (Character.class.isInstance(iterField) && !(iterField instanceof HandOperatedMasterSquirrel))
                    ((Character)iterField).nextStep(flattenedBoard());

        }
        for(int i = 0; i < board.getEntitySet().getContainer().length; i++){
            if(board.getEntitySet().getContainer()[i] instanceof HandOperatedMasterSquirrel){
                System.out.println("Your Energy is: "+board.getEntitySet().getElement(i).getEnergy());
            }
        }

    }

    public FlattenedBoard flattenedBoard() {
        return board.flatten();
    }

    public void setInput(MoveCommand command) {
        input = command;
    }

}

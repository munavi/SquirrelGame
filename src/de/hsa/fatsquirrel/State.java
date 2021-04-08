package de.hsa.fatsquirrel;

public class State {
    private Board board;
    private MoveCommand input;
    private int highScore;

    public State(Board board){
        this.board = board;
    }

    public Board getBoard(){
        return board;
    }

    public void update(){
        for (int x = 0; x < board.getSize().getX(); x++) {
            for (int y = 0; y < board.getSize().getY(); y++) {
                Entity iterField = board.getEntities()[x][y];
                if (iterField == null)
                    continue;
                // If NOT NULL
                if (iterField instanceof MasterSquirrel)

                    ((MasterSquirrel)iterField).setInput(input);

                if (Character.class.isInstance(iterField))
                    continue; //((Character)iterField).nextStep();
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

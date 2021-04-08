package de.hsa.fatsquirrel;

public interface UI {

    public MoveCommand getCommand();

    public void render(BoardView view);
}

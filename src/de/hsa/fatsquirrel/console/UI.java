package de.hsa.fatsquirrel.console;

import de.hsa.fatsquirrel.core.BoardView;

public interface UI {

    public MoveCommand getCommand();
    public void render(BoardView view);

}

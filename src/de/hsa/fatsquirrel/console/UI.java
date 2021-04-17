package de.hsa.fatsquirrel.console;

import de.hsa.fatsquirrel.core.BoardView;
import de.hsa.fatsquirrel.util.ui.console.Command;

public interface UI {

    void render(BoardView view);
    Command getCommand();
    void message(String message);

}

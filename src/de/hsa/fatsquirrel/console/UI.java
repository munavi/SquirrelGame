package de.hsa.fatsquirrel.console;

import de.hsa.fatsquirrel.core.BoardView;
import de.hsa.fatsquirrel.util.ui.console.Command;

public interface UI {

    void render(BoardView view);
    Command getCommand();
    void message(String message);

    void inputLoop();

    Command waitForInput();		// sodass man die alte version (wo immer erst auf input gewartet wird) ausfuehren kann; wird nur in Game.runOld() benutzt
}

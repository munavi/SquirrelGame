package de.hsa.fatsquirrel;

import de.hsa.fatsquirrel.console.ConsoleUI;
import de.hsa.fatsquirrel.core.GameImpl;
import de.hsa.fatsquirrel.console.UI;
import de.hsa.fatsquirrel.core.*;

import de.hsa.fatsquirrel.core.Board;
import de.hsa.fatsquirrel.core.Game;

public class Launcher {
    public static void main(String [] args){
        BoardConfig config = new BoardConfig();
        Board board = new Board(config);
        HandOperatedMasterSquirrel player = new HandOperatedMasterSquirrel(0, new XY(5,5));
        board.getEntitySet().push(player);
        State state = new State(board);
        UI ui = new ConsoleUI();
        Game game = new GameImpl(state,ui,player);
        game.run();



    }
}

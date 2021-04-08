package de.hsa.fatsquirrel;

public class Launcher {
    public static void main( String [] args){
        Game game = new GameImpl(new State(new Board(new BoardConfig(new XY(10,10),12))), new ConsoleUI());
        game.run();
    }
}

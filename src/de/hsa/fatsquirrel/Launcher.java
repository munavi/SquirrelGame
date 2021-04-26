package de.hsa.fatsquirrel;

import de.hsa.fatsquirrel.console.ConsoleUI;
import de.hsa.fatsquirrel.console.FxUI;
import de.hsa.fatsquirrel.core.GameImpl;
import de.hsa.fatsquirrel.console.UI;
import de.hsa.fatsquirrel.core.*;

import de.hsa.fatsquirrel.core.Board;
import de.hsa.fatsquirrel.core.Game;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Timer;
import java.util.TimerTask;


public class Launcher extends Application {

    public static void main(String[] args) {
        if (containsArgument(args, "gui") || containsArgument(args, "--gui")) {
            Application.launch(new String[]{args[0]});
            return;
        }
        BoardConfig config = new BoardConfig();
        Board board = new Board(config);
        HandOperatedMasterSquirrel player = new HandOperatedMasterSquirrel(0, new XY(5, 5));
        board.getEntitySet().push(player);
        State state = new State(board);
        UI ui = new ConsoleUI();
        Game game = new GameImpl(state, ui, player);
        if (containsArgument(args, "single-thread") || containsArgument(args, "--single-thread"))
            startGameSingleThreaded(game);
        else
            startGameMultiThreaded(game);
    }



//    public static void main(String[] args) {
//
//        BoardConfig config = new BoardConfig();
//        Board board = new Board(config);
//        HandOperatedMasterSquirrel player = new HandOperatedMasterSquirrel(0, new XY(5, 5));
//        board.getEntitySet().push(player);
//        State state = new State(board);
//        UI ui = new ConsoleUI();
//        Game game = new GameImpl(state, ui, player);
//        startGame(game);
//        ui.inputLoop();
//
//
//    }


        private static void startGame (Game game){
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    game.runMultiThreaded();
                }
            }, 1000);
        }


        @Override
        public void start (Stage stage){
            BoardConfig config = new BoardConfig();
            Board board = new Board(config);
            HandOperatedMasterSquirrel player = new HandOperatedMasterSquirrel(0, new XY(5, 5));
            board.getEntitySet().push(player);
            State state = new State(board);
            UI ui = new ConsoleUI();
            FxUI fxUI = FxUI.createInstance(new XY(config.getWidth(), config.getHeight()));
            Game game = new GameImpl(state, fxUI, player);
            stage.setScene(fxUI);
            stage.setTitle("Diligent Squirrel");
            fxUI.getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent evt) {
                    System.exit(-1);
                }
            });
            stage.show();

            startGameMultiThreaded(game);
        }

    private static void startGameSingleThreaded(Game game) {
        game.run();
    }


    private static void startGameMultiThreaded(Game game) {
        UI ui = new ConsoleUI();
        startGame(game);
        ui.inputLoop();

    }

        private static boolean containsArgument (String[] args, String arg){
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals(arg))
                    return true;
            }
            return false;
        }
    }



//    }
//}

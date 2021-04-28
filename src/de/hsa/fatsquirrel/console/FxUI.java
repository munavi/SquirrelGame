package de.hsa.fatsquirrel.console;

import de.hsa.fatsquirrel.core.*;
import de.hsa.fatsquirrel.util.ui.console.Command;

import java.awt.*;

import de.hsa.fatsquirrel.util.ui.console.ScanException;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class FxUI extends Scene implements UI{
    private Canvas boardCanvas;
    private Label msgLabel;
    private static final int CELL_SIZE = 32;
    private static Command puffer = null;

    public FxUI(Parent parent, Canvas boardCanvas, Label msgLabel) {
        super(parent);
        this.boardCanvas = boardCanvas;
        this.msgLabel = msgLabel;
    }


    @Override
    public void render(final BoardView view) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                repaintBoardCanvas(view);
            }
        });


    }

    private void repaintBoardCanvas(BoardView view) {
        GraphicsContext gc = boardCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());
        XY viewSize = view.getSize();

        for (int i = 0; i < viewSize.getX(); i++) {
            for (int j = 0; j < viewSize.getY(); j++) {
                Entity entity = view.getEntityType(i, j);
                if (entity == null) continue;

                if (entity instanceof Wall) {
                    gc.setFill(Color.YELLOW);
                } else if (entity instanceof GoodPlant) {
                    gc.setFill(Color.GREEN);
                } else if (entity instanceof BadPlant) {
                    gc.setFill(Color.DARKRED);
                } else if (entity instanceof GoodBeast) {
                    gc.setFill(Color.DARKOLIVEGREEN);
                } else if (entity instanceof BadBeast) {
                    gc.setFill(Color.RED);
                } else if (entity instanceof HandOperatedMasterSquirrel) {
                    gc.setFill(Color.ROYALBLUE);
                } else if (entity instanceof MiniSquirrel) {
                    gc.setFill(Color.LIGHTBLUE);
                } else if(entity instanceof MasterSquirrelBot){
                    gc.setFill(Color.PURPLE);
                }

                gc.fillRect(entity.getPosition().getX() * CELL_SIZE,
                        entity.getPosition().getY() * CELL_SIZE,
                        CELL_SIZE, CELL_SIZE);
            }
        }

    }

    @Override
    public Command getCommand() {
        Command command = puffer;
        puffer = null;
        return command;
    }

    @Override
    public void message(final String message) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                msgLabel.setText(message);
            }
        });


    }
    public static FxUI createInstance(XY boardSize) {
        Canvas boardCanvas = new Canvas(boardSize.getX() * CELL_SIZE, boardSize.getY() * CELL_SIZE);
        Label statusLabel = new Label();
        VBox top = new VBox();
        top.getChildren().add(boardCanvas);
        top.getChildren().add(statusLabel);
        statusLabel.setText("Go Squirrel!");
        final FxUI fxUI = new FxUI(top, boardCanvas, statusLabel);
        fxUI.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        System.out.println("Es wurde folgende Taste gedrueckt: " + event.getCode());
                        if (event.getText().equals("w")) {
                            puffer = new Command(GameCommandType.UP, null);
                        }
                        if (event.getText().equals("a")) {
                            puffer = new Command(GameCommandType.LEFT, null);
                        }
                        if (event.getText().equals("s")) {
                            puffer = new Command(GameCommandType.DOWN, null);
                        }
                        if (event.getText().equals("d")) {
                            puffer = new Command(GameCommandType.RIGHT, null);
                        }
                        if (event.getText().equals("e")) {
                            puffer = new Command(GameCommandType.MASTER_ENERGY, null);
                        }
                        if (event.getText().equals("f")) {
                        	puffer = new Command(GameCommandType.SPAWN_MINI_FX, null);
                        }
                    }
                }
        );
        return fxUI;
    }



    @Override
    public void inputLoop() {

    }


	@Override
	public void singleThreadInput() {
		
	}
}

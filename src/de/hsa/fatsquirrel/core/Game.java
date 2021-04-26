package de.hsa.fatsquirrel.core;

import de.hsa.fatsquirrel.console.UI;

public abstract class Game {

    protected final int FPS = 1;
    private int frameCount = 0;
    protected UI ui;
    protected State state;

    public Game(State state, UI ui) {
        this.state = state;
        this.ui = ui;
    }

    public void runMultiThreaded() {
        while (true) {
            try {
                Thread.sleep((int)(1.0f / FPS *1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            render();
            processInput();
            update();
            frameCount++;
        }
    }

    public void run() {
        while (true) {
            render();
            processInput();
            update();
        }
    }





    public abstract void render();

    protected abstract void processInput();

//    public void update() {
//        state.update();
//    }

    public void update() {
        state.getBoard().update(state);
    }



}

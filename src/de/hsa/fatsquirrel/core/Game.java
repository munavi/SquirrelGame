package de.hsa.fatsquirrel.core;

import de.hsa.fatsquirrel.console.UI;

public abstract class Game {

    public UI ui;
    public State state;

    public Game(State state, UI ui) {
        this.state = state;
        this.ui = ui;
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

    public void update() {
        state.update();
    }


}

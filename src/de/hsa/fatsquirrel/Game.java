package de.hsa.fatsquirrel;

public abstract class Game {

    protected UI ui;
    protected State state;

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

    abstract void render();

    abstract void processInput();

    protected void update() {
        state.update();
    }


}

package de.hsa.fatsquirrel;

public abstract class Game {

    public void run() {
        while (true) {
            render();
            processInput();
            update();
        }
    }

    abstract void render();

    abstract void processInput();

    private void update() {
    }


}

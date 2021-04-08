package de.hsa.fatsquirrel;

public class GameImpl extends Game {

    public GameImpl(State state, UI ui) {
        super(state, ui);
    }

    @Override
    void render() {
        ui.render(state.flattenedBoard());
    }

    @Override
    void processInput() {
        state.setInput(ui.getCommand());

    }
}

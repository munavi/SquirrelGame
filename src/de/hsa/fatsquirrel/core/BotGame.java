package de.hsa.fatsquirrel.core;

import de.hsa.fatsquirrel.console.UI;

public class BotGame extends Game{

    public BotGame(State state, UI ui) {
        super(state, ui);
    }

    @Override
    public void render() {
        ui.render(state.flattenedBoard());
    }

    @Override
    protected void processInput() {

    }
}

package de.hsa.fatsquirrel.console;

import de.hsa.fatsquirrel.core.Game;
import de.hsa.fatsquirrel.core.HandOperatedMasterSquirrel;
import de.hsa.fatsquirrel.core.State;

public class GameImpl extends Game {
    HandOperatedMasterSquirrel player;

    public GameImpl(State state, UI ui, HandOperatedMasterSquirrel player) {
        super(state, ui);
        this.player = player;

    }

    @Override
    public void render() {
        ui.render(state.flattenedBoard());
    }

    @Override
    public void processInput() {
        state.setInput(ui.getCommand());
    }
}

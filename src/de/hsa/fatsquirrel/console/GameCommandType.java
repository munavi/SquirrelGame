package de.hsa.fatsquirrel.console;

import de.hsa.fatsquirrel.core.Game;
import de.hsa.fatsquirrel.util.ui.console.CommandTypeInfo;

public enum GameCommandType implements CommandTypeInfo {
    HELP("help", "  * list all commands"),
    EXIT("exit", "  * exit program"),
    ALL("all", " ??? "),
    LEFT("a", "  * moves the squirrel to the left"),
    RIGHT("d", "  * moves the squirrel to the right"),
    UP("w", "  * moves the squirrel up"),
    DOWN("s", "  * moves the squirrel down"),
    MASTER_ENERGY("e", "  * shows energy of the master squirrel"),
    SPAWN_MINI("mini", "<param1>  * spawns a mini squirrel with a starting energy", int.class);

    private final String commandName;
    private final String helpText;
    private final Class[] params;

    GameCommandType(String commandName, String helpText, Class param1) {
        this.commandName = commandName;
        this.helpText = helpText;
        this.params = new Class[]{param1};
    }

    GameCommandType(String commandName, String helpText) {
        this.commandName = commandName;
        this.helpText = helpText;
        this.params = new Class[0];
    }

    public String getName() {
        return commandName;
    }

    public String getHelpText() {
        return helpText;
    }

    public Class<?>[] getParamTypes() {
        return params;
    }


}

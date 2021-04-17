package de.hsa.fatsquirrel.util.ui.consoletest;

import de.hsa.fatsquirrel.util.ui.console.CommandTypeInfo;

public enum MyFavouriteCommandType implements CommandTypeInfo {
    HELP("help", "  * list all commands"),
    EXIT("exit", "  * exit program"),
    ADDI("addi", "<param1>  <param2>   * simple integer add ", int.class, int.class),
    ADDF("addf", "<param1>  <param2>   * simple float add ", float.class, float.class),
    ECHO("echo", "<param1>  <param2>   * echos param1 string param2 times ", String.class, int.class);

    private final String commandName;
    private final String helpText;
    private final Class param1;
    private final Class param2;

    MyFavouriteCommandType(String commandName, String helpText, Class param1, Class param2) {
        this.commandName = commandName;
        this.helpText = helpText;
        this.param1 = param1;
        this.param2 = param2;
    }

    MyFavouriteCommandType(String commandName, String helpText) {
        this.commandName = commandName;
        this.helpText = helpText;
        this.param1 = null;
        this.param2 = null;
    }


    @Override
    public String getName() {
        return commandName;
    }

    @Override
    public String getHelpText() {
        return helpText;
    }

    @Override
    public Class<?>[] getParamTypes() {
        Class<?>[] params = {param1, param2};
        return params;

    }
}

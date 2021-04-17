package de.hsa.fatsquirrel.util.ui.console;

public interface CommandTypeInfo {
    String getName();
    public String getHelpText();
    Class <?> [] getParamTypes();

}

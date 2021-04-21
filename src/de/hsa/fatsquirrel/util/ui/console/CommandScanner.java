package de.hsa.fatsquirrel.util.ui.console;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.Scanner;


public class CommandScanner {
    private final CommandTypeInfo[] commandTypes;
    private final BufferedReader inputReader;
    private final PrintStream outputStream;

    public CommandScanner(CommandTypeInfo[] commandTypes, BufferedReader inputReader, PrintStream outputStream) {
        this.commandTypes = commandTypes;
        this.inputReader = inputReader;
        this.outputStream = outputStream;
    }
    public Command next() {
        Scanner scanner = new Scanner(inputReader);
        String[] input = scanner.nextLine().split("\\s+");
        CommandTypeInfo commandType = null;

        // find our command
        for (int i = 0; i < commandTypes.length; i++) {
            if (input[0].equals(commandTypes[i].getName())) {
                commandType = commandTypes[i];
                break;
            }
        }

        if (commandType == null) throw new ScanException("Didn't find your command.");

        // convert input to paramtype
        Object[] params = new Object[commandType.getParamTypes().length];
        for (int i = 0; i < commandType.getParamTypes().length; i++) {

            Class type = commandType.getParamTypes()[i];
            if (type == null) continue;

            if (type == int.class) {
            	if(input[0].equals("spawn_mini")) {
	            	if(input.length == 1) {
	            		System.out.println("no parameter passed, mini gets the default amount of energy (100) if Squirrel has enough energy");
	            		params[i] = 100;
	            	}
	            	else if(Integer.parseInt(input[i + 1]) >= 0 && 99 >= Integer.parseInt(input[i + 1])) {
	            		System.out.println("passed energy should be at least 100, mini gets the default amount of energy (100) if Squirrel has enough energy");
	            		params[i] = 100;
	            	}
	            	else {
	                params[i] = Integer.parseInt(input[i + 1]);
	            	}
            	}
            	else {
            		params[i] = Integer.parseInt(input[i + 1]);
            	}
            }
            else if (type == float.class) {
                params[i] = Float.parseFloat(input[i + 1]);
            }
            else {
                params[i] = input[i + 1];
            }
        }

        return new Command(commandType, params);
    }

    public CommandTypeInfo[] getCommandTypes() {
        return this.commandTypes;
    }

}



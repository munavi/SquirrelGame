package de.hsa.fatsquirrel.util.ui.consoletest;

import de.hsa.fatsquirrel.util.ui.console.Command;
import de.hsa.fatsquirrel.util.ui.console.CommandScanner;
import de.hsa.fatsquirrel.util.ui.console.CommandTypeInfo;
import de.hsa.fatsquirrel.util.ui.console.ScanException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class MyFavouriteCommandsProcessor {
    private final PrintStream outputStream = System.out;
    private final BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
    private final CommandScanner commandScanner = new CommandScanner(MyFavouriteCommandType.values(), inputReader, outputStream);

    public static void main(String[] args) {
        MyFavouriteCommandsProcessor processor = new MyFavouriteCommandsProcessor();
        processor.process();
    }

    public void process() {

        Command command;

        while (true) {

            try {
                command = commandScanner.next();
            } catch (ScanException e) {
                System.out.println(e.getMessage());
                continue;
            }

            Object[] params = command.getParams();
            MyFavouriteCommandType commandType = (MyFavouriteCommandType) command.getCommandType();

            switch (commandType) {
                case EXIT -> System.exit(0);
                case HELP -> help();
                case ADDI -> addi(params[0], params[1]);
                case ADDF -> addf(params[0], params[1]);
                case ECHO -> echo(params[0], params[1]);
            }
        }
    }


    private void echo(Object param1, Object param2) {
        for (int i = 0; i < (int) param2; i++) {
            System.out.println((String) param1);
        }
    }

    private void addf(Object param1, Object param2) {
        System.out.println((float) param1 + (float) param2);
    }

    private void addi(Object param1, Object param2) {
        System.out.println((int) param1 + (int) param2);
    }

    private void help() {
        CommandTypeInfo[] commandTypes = commandScanner.getCommandTypes();
        for (CommandTypeInfo commandType : commandTypes) {
            System.out.println(commandType.getName() + commandType.getHelpText());
        }
    }

}

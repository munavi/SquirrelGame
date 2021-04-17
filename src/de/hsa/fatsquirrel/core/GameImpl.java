package de.hsa.fatsquirrel.core;

import de.hsa.fatsquirrel.console.GameCommandType;
import de.hsa.fatsquirrel.console.UI;
import de.hsa.fatsquirrel.util.ui.console.*;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;


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
        Command command = ui.getCommand();
        if (command == null) return;

        Object[] params = command.getParams();
        GameCommandType commandType = (GameCommandType) command.getCommandType();

        try {

            Class clazz = Class.forName("core.GameImpl");
            Method method = clazz.getDeclaredMethod(commandType.getName(), commandType.getParamTypes());
            method.invoke(this, params);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //state.setInput(ui.getCommand());
    }

    private void exit() {
        System.exit(0);
    }

    private void help() {
        CommandTypeInfo[] commandTypes = GameCommandType.values();
        String message = "";
        for (int i = 0; i < commandTypes.length; i++) {
            message += commandTypes[i].getName() + commandTypes[i].getHelpText() + '\n';
        }
        ui.message(message);
    }

    private void all() {

    }

    private void left() {
        player.setMoveCommand(new XY(-1, 0));
    }

    private void right() {
        player.setMoveCommand(new XY(1, 0));
    }

    private void up() {
        player.setMoveCommand(new XY(0, -1));
    }

    private void down() {
        player.setMoveCommand(new XY(0, 1));
    }

    private void master_energy() {
        ui.message("Your current energy: " + player.getEnergy());
    }

    private void spawn_mini(int energy) {
        try {
            state.flattenedBoard().spawnMiniSquirrel(player, energy);
        } catch (NotEnoughEnergyException e) {
            ui.message(e.getMessage());
        }
    }

}

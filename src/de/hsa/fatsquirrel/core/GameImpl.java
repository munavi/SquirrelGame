package de.hsa.fatsquirrel.core;

import de.hsa.fatsquirrel.console.GameCommandType;
import de.hsa.fatsquirrel.console.UI;
import de.hsa.fatsquirrel.util.ui.console.*;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;


public class GameImpl extends Game {
//    HandOperatedMasterSquirrel player;
    MasterSquirrelBot bot;
    MasterSquirrel player;
    


    public GameImpl(State state, UI ui, HandOperatedMasterSquirrel player) {
        super(state, ui);
        this.player = player;

    }


    public GameImpl(State state, UI ui, MasterSquirrelBot bot) {
        super(state, ui);
        this.bot = bot;

    }

    public GameImpl(State state, UI ui, MasterSquirrel player) {
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
            // laden und die Beschreibung unserer Klasse als Referenz clazz vom Typ Class erhalten
            Class clazz = Class.forName("de.hsa.fatsquirrel.core.GameImpl"); // lokalisiert, lädt und bindet clazz- Instanz zum GameImpl
            // Zum Aufruf des Methodenobjekts method verwenden - invoke
            Method method = clazz.getDeclaredMethod(commandType.getName(), commandType.getParamTypes()); // returns an array with methods
            method.invoke(this, params);

        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

//        switch(commandType){
//            case EXIT:
//                exit();
//            case HELP:
//                help();
//            case ALL:
//                // TO DO
//            case LEFT:
//                left();
//                break;
//            case RIGHT:
//                right();
//                break;
//            case UP:
//                up();
//                break;
//            case DOWN:
//                down();
//                break;
//            case SPAWN_MINI:
//                try{
//                    state.flattenedBoard().spawnMiniSquirrel(player, (int) params[0]);
//                } catch( NotEnoughEnergyException e){
//                    System.out.println(e.getMessage());
//                }
//                break;
//            case MASTER_ENERGY:
//                System.out.println(player.getEnergy());
//                break;
//
//
//        }
    }

    private void exit() {
        System.exit(0);
    }

    private void help() {
        CommandTypeInfo[] commandTypes = GameCommandType.values();
        String message = "";
        for (CommandTypeInfo commandType : commandTypes) {
            message += commandType.getName() + commandType.getHelpText() + '\n';
        }
        ui.message(message);
        processInput();
    }

    private void all() {

    }

    private void a() {
        player.setMoveCommand(new XY(-1, 0));
    }

    private void d() {
        player.setMoveCommand(new XY(1, 0));
    }

    private void w() { player.setMoveCommand(new XY(0, -1)); }

    private void s() {
        player.setMoveCommand(new XY(0, 1));
    }

    private void e() {	//masterenergy
        player.setMoveCommand(new XY(0,0));
        ui.message("Your current energy: " + player.getEnergy());
        processInput();
    }

    private void spawn_mini(int energy) {
        try {
            player.setMoveCommand(new XY(0,0));
            state.flattenedBoard().spawnMiniSquirrel(player, energy);
        } catch (NotEnoughEnergyException e) {
            ui.message(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
        	System.out.println("(GameImpl line 127; sollte nicht vorkommen");
        }
    }
    
    private void f() {	//gehoert zu spawnminifx in GameCommandType
        try {
            player.setMoveCommand(new XY(0,0));
            state.flattenedBoard().spawnMiniSquirrel(player, 100);
        } catch (NotEnoughEnergyException e) {
            ui.message(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
        	System.out.println("(GameImpl line 127; sollte nicht vorkommen");
        }
    }
}

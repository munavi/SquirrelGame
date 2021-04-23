package de.hsa.fatsquirrel.core;

import de.hsa.fatsquirrel.botapi.BotController;
import de.hsa.fatsquirrel.botapi.BotControllerFactoryImpl;
import de.hsa.fatsquirrel.botapi.ControllerContext;
import de.hsa.fatsquirrel.botapi.OutOfViewException;

public class MasterSquirrelBot extends MasterSquirrel{

    private BotController masterBotController;


    public MasterSquirrelBot(int id, XY loc) {
        super(id, loc);
        masterBotController = new BotControllerFactoryImpl().createMasterBotController();
    }

    @Override
    public int getStartEnergy() {
        return 0;
    }

    public class ControllerContextImpl implements ControllerContext {

        @Override
        public XY getViewLowerLeft() {
            return null;
        }

        @Override
        public XY getViewUpperRight() {
            return null;
        }

        @Override
        public Entity getEntityAt(XY position) {
            return null;
        }

        @Override
        public void move(XY direction) {

        }

        @Override
        public void spawnMiniBot(XY direction, int energy) {

        }

        @Override
        public int getEnergy() {
            return 0;
        }
    }
}

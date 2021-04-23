package de.hsa.fatsquirrel.core;

import de.hsa.fatsquirrel.botapi.BotController;
import de.hsa.fatsquirrel.botapi.BotControllerFactoryImpl;
import de.hsa.fatsquirrel.botapi.ControllerContext;

public class MiniSquirrelBot extends MiniSquirrel{

    private BotController miniSquirrelBotController;

    public MiniSquirrelBot(int id, int energy, XY loc, MasterSquirrel master) {
        super(id, energy, loc, master);
        miniSquirrelBotController = new BotControllerFactoryImpl().createMiniBotController();
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

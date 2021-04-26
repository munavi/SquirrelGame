package de.hsa.fatsquirrel.core;

import de.hsa.fatsquirrel.botapi.BotController;
import de.hsa.fatsquirrel.botapi.BotControllerFactoryImpl;
import de.hsa.fatsquirrel.botapi.ControllerContext;

public class MasterSquirrelBot extends MasterSquirrel{

    private BotController masterBotController;


    public MasterSquirrelBot(int id, XY loc) {
        super(id, loc);
        masterBotController = new BotControllerFactoryImpl().createMasterBotController();
    }

    @Override
    public int getStartEnergy() {
       return this.getEnergy();
    }


    public class ControllerContextImpl implements ControllerContext {

        private EntityContext context;
        private XY lowerLeft;
        private XY upperRight;

        public ControllerContextImpl(EntityContext context, XY lowerLeft, XY upperRight) {
            this.context = context;
            this.lowerLeft = lowerLeft;
            this.upperRight = upperRight;
        }


        @Override
        public XY getViewLowerLeft() {
            return lowerLeft;
        }

        @Override
        public XY getViewUpperRight() {
            return upperRight;
        }

        @Override
        public Entity getEntityAt(XY position) {
             return context.getEntityType(position.getX(), position.getY());
        }

        @Override
        public void move(XY direction) {
            context.tryMove(MasterSquirrelBot.this, direction);
        }

        @Override
        public void spawnMiniBot(XY direction, int energy) {
            try {
                context.spawnMiniSquirrel(MasterSquirrelBot.this, energy);
            } catch (NotEnoughEnergyException e) {
                System.out.println(e.getMessage());
            }
        }

        @Override
        public int getEnergy() {
            return MasterSquirrelBot.this.getEnergy();
        }
    }
}

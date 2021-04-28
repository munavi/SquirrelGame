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
            Entity entity = null;
            try {
                entity = context.getEntityType(position.getX(), position.getY());
            } catch (ArrayIndexOutOfBoundsException e) {

            }
            return entity;

        }

        @Override
        public void move(XY direction) {
            context.tryMove(MiniSquirrelBot.this, direction);

        }

        @Override
        public void spawnMiniBot(XY direction, int energy) {
            try {
                context.spawnMiniSquirrel(MiniSquirrelBot.this.getMaster(), energy);
            } catch (NotEnoughEnergyException e) {
                System.out.println(e.getMessage());
            }
        }

        @Override
        public int getEnergy() {
            return MiniSquirrelBot.this.getEnergy();
        }
    }
}

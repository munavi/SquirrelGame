package de.hsa.fatsquirrel.core;

import de.hsa.fatsquirrel.botapi.*;

public class MasterSquirrelBot extends MasterSquirrel{

    private BotController masterBotController;
    private BotControllerFactory botControllerFactory;
    private int viewRadius = 5;


    public MasterSquirrelBot(int id, XY loc, BotControllerFactory botControllerFactory) {
        super(id, loc);
        this.botControllerFactory = botControllerFactory;
        this.masterBotController = botControllerFactory.createMasterBotController();
    }

    public void nextStep(EntityContext context){
        masterBotController.nextStep(new ControllerContextImpl(context,
                new XY(this.getPosition().getX() - viewRadius, this.getPosition().getY() - viewRadius),
                new XY(this.getPosition().getX() + viewRadius, this.getPosition().getY() + viewRadius)));
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

package de.hsa.fatsquirrel.botapi;

public class BotControllerFactoryImpl implements BotControllerFactory{
    @Override
    public BotController createMasterBotController() {
        return new MasterSquirrelBotController();
    }

    @Override
    public BotController createMiniBotController() {
        return new MiniSquirrelBotController();
    }
}

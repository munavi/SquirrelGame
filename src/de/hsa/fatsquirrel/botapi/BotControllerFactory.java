package de.hsa.fatsquirrel.botapi;

public interface BotControllerFactory {
     BotController createMasterBotController();
     BotController createMiniBotController();
}

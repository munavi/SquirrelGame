package de.hsa.fatsquirrel.botapi;

import de.hsa.fatsquirrel.core.XY;

public class HandOperatedController implements BotController{
    private XY moveCommand = new XY(0,0);

    @Override
    public void nextStep(ControllerContext view) {
        view.move(moveCommand);
    }

    public void setMoveCommand(XY moveCommand) {
        this.moveCommand = moveCommand;
    }
}

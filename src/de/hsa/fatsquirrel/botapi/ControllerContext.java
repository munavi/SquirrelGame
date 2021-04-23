package de.hsa.fatsquirrel.botapi;

import de.hsa.fatsquirrel.core.Entity;
import de.hsa.fatsquirrel.core.XY;

public interface ControllerContext {
    XY getViewLowerLeft();
    XY getViewUpperRight();
    Entity getEntityAt(XY position);
    void move(XY direction);
    void spawnMiniBot(XY direction, int energy);
    int getEnergy();

}

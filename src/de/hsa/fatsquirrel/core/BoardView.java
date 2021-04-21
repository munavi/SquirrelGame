package de.hsa.fatsquirrel.core;

import de.hsa.fatsquirrel.core.EntityType;
import de.hsa.fatsquirrel.core.XY;

public interface BoardView {
    public EntityType getEntityType(int x, int y);
    public XY getSize();
    public void visualize();
}

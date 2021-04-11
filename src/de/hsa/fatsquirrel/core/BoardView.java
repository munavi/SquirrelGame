package de.hsa.fatsquirrel.core;

public interface BoardView {
    public EntityType getEntityType(int x, int y);
    public XY getSize();
}

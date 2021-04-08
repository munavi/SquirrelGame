package de.hsa.fatsquirrel;

public interface BoardView {
    public EntityType getEntityType(int x, int y);
    public XY getSize();
}

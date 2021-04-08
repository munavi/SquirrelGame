package de.hsa.fatsquirrel;

public class Board {
    private Entity[][] entities;
    private BoardConfig boardConfig;

    public Board(BoardConfig config) {
        this.boardConfig = config;
        this.entities = new Entity[config.getSize().getX()][config.getSize().getY()];
    }

    public XY getSize() {
        return boardConfig.getSize();
    }

    public FlattenedBoard flatten() {
        return new FlattenedBoard(this);
    }

    public Entity[][] getEntities() {
        return entities;
    }

}

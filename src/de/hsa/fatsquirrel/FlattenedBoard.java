package de.hsa.fatsquirrel;

public class FlattenedBoard implements EntityContext, BoardView{
    private Board board;
    private Entity[][] cells;

    public FlattenedBoard(Board board) {
        this.board = board;
        cells = board.getEntities();
    }


    @Override
    public XY getSize() {
        return board.getSize();
    }

    @Override
    public EntityType getEntityType(XY xy) {
        return EntityType.fromEntity(cells[xy.getX()][xy.getY()]);
    }


    // TODO

    @Override
    public void tryMove(MiniSquirrel miniSquirrel, XY moveDirection) {

    }

    @Override
    public void tryMove(GoodBeast goodBeast, XY moveDirection) {

    }

    @Override
    public void tryMove(BadBeast badBeast, XY moveDirection) {

    }

    @Override
    public void tryMove(MasterSquirrel masterSquirrel, XY moveDirection) {

    }

    @Override
    public PlayerEntity nearestPlayerEntity(XY pos) {
        return null;
    }

    @Override
    public void kill(Entity entity) {

    }

    @Override
    public void killAndReplace(Entity entity) {

    }


}

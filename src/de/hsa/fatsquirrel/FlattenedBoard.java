package de.hsa.fatsquirrel;

public class FlattenedBoard implements EntityContext, BoardView{
    private Board board;
    private Entity[][] cells;
    private XY xy;

    public FlattenedBoard(Board board) {
        this.board = board;
        cells = new Entity[board.getSize().getX()][board.getSize().getY()];
        for (Entity e : board.getEntitySet().getContainer()) {
            cells[e.getPosition().getX()][e.getPosition().getY()] = e;
        }

    }


    @Override
    public EntityType getEntityType(int x, int y) {
        return getEntityType(new XY(x, y));
    }

    @Override
    public XY getSize() {
        return new XY(cells.length, cells[0].length);
    }

    @Override
    public void tryMove(MiniSquirrel miniSquirrel, XY moveDirection) {
        XY nextPosition = new XY(miniSquirrel.getPosition().getX() + moveDirection.getX(),
                miniSquirrel.getPosition().getY() + moveDirection.getY());
        Entity nextEntity = cells[nextPosition.getX()][nextPosition.getY()];
        if (nextEntity instanceof Wall) {
            miniSquirrel.updateEnergy(nextEntity.getEnergy());
            miniSquirrel.paralyze();
            return;
        } else if (nextEntity instanceof MiniSquirrel &&
                miniSquirrel.getMaster() != ((MiniSquirrel) nextEntity).getMaster()) {
            kill(miniSquirrel);
        } else if (nextEntity instanceof MasterSquirrel &&
                miniSquirrel.getMaster() != nextEntity) {
            kill(miniSquirrel);
        } else if (nextEntity instanceof GoodPlant ||
                nextEntity instanceof BadPlant) {
            miniSquirrel.updateEnergy(nextEntity.getEnergy());
            killAndReplace(nextEntity); // Nicht nur instanz gelöscht und null gesetzt, sondern auch neu erstellen
        } else if (nextEntity instanceof GoodBeast ||
                nextEntity instanceof BadBeast) {
            miniSquirrel.updateEnergy(nextEntity.getEnergy());
            killAndReplace(nextEntity);
        }

        cells[miniSquirrel.getPosition().getX()][miniSquirrel.getPosition().getY()] = null;
        nextEntity = miniSquirrel;
        miniSquirrel.setPosition(nextPosition);
    }

    @Override
    public void tryMove(GoodBeast goodBeast, XY moveDirection) {
        XY nextPosition = new XY(goodBeast.getPosition().getX() + moveDirection.getX(),
                goodBeast.getPosition().getY() + moveDirection.getY());
        Entity nextEntity = cells[nextPosition.getX()][nextPosition.getY()];
        CHECK:
        if (nextEntity == null)
            break CHECK;
        else if (nextEntity instanceof Wall)
            return;
        else if (nextEntity instanceof MiniSquirrel ||
                nextEntity instanceof MasterSquirrel) {
            nextEntity.updateEnergy(goodBeast.getEnergy());
            killAndReplace(goodBeast);
        }
        cells[goodBeast.getPosition().getX()][goodBeast.getPosition().getY()] = null;
        cells[nextPosition.getX()][nextPosition.getY()] = goodBeast;
        goodBeast.setPosition(nextPosition);
    }

    @Override
    public void tryMove(BadBeast badBeast, XY moveDirection) {
        XY nextPosition = new XY(badBeast.getPosition().getX() + moveDirection.getX(),
                badBeast.getPosition().getY() + moveDirection.getY());
        Entity nextEntity = cells[nextPosition.getX()][nextPosition.getY()];
        CHECK:
        if (nextEntity == null)
            break CHECK;
        else if (nextEntity instanceof Wall)
            return;
        else if (nextEntity instanceof MiniSquirrel ||
                nextEntity instanceof MasterSquirrel) {
            nextEntity.updateEnergy(badBeast.getEnergy());
            killAndReplace(badBeast);
        }
        cells[badBeast.getPosition().getX()][badBeast.getPosition().getY()] = null;
        cells[nextPosition.getX()][nextPosition.getY()] = badBeast;
        badBeast.setPosition(nextPosition);
    }

    @Override
    public void tryMove(MasterSquirrel masterSquirrel, XY moveDirection) {
        XY nextPosition = new XY(masterSquirrel.getPosition().getX() + moveDirection.getX(),
                masterSquirrel.getPosition().getY() + moveDirection.getY());
        Entity nextEntity = cells[nextPosition.getX()][nextPosition.getY()];
        CHECK:
        if (nextEntity == null)
            break CHECK;
        else if (nextEntity instanceof Wall)
        {
            masterSquirrel.updateEnergy(nextEntity.getEnergy());
            masterSquirrel.paralyze();
            return;
        }
        else if (nextEntity instanceof MasterSquirrel) {
            // TODO:....
        }
        else {
            masterSquirrel.updateEnergy(nextEntity.getEnergy());
            killAndReplace(nextEntity);
        }
        cells[masterSquirrel.getPosition().getX()][masterSquirrel.getPosition().getY()] = null;
        cells[nextPosition.getX()][nextPosition.getY()] = masterSquirrel;
        masterSquirrel.setPosition(nextPosition);
    }

    @Override
    public PlayerEntity nearestPlayerEntity(XY pos) {
        return null;
    }

    @Override
    public void kill(Entity entity) {
        // TODO: No sense....
    }

    @Override
    public void killAndReplace(Entity entity) {
        entity.updateEnergy(entity.getStartEnergy() - entity.getEnergy());
        XY randomPos = XY.getRandomPosition(board.getSize().getX(), board.getSize().getY());
        while (getEntityType(randomPos) != null)
            randomPos = XY.getRandomPosition(board.getSize().getX(), board.getSize().getY());
        entity.setPosition(new XY(randomPos.getX(), randomPos.getY()));
        cells[randomPos.getX()][randomPos.getY()] = entity;
    }


    @Override
    public EntityType getEntityType(XY xy) {
        return EntityType.fromEntity(cells[xy.getX()][xy.getY()]);
    }


}

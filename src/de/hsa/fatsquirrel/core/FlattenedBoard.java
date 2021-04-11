package de.hsa.fatsquirrel.core;

import java.util.ArrayList;
import java.util.Random;

public class FlattenedBoard implements EntityContext, BoardView {
    private Board board;
    private Entity[][] cells;
    private XY xy;

    public FlattenedBoard(Board board) {
        cells = new Entity[board.getSize().getX() + 1][board.getSize().getY() + 1];
        for (Entity e : board.getEntitySet().getContainer()) {
            if(e !=null)
            cells[e.getPosition().getX()][e.getPosition().getY()] = e;
        }
        this.board = board;

    }


    @Override
    public EntityType getEntityType(int x, int y) {
        return getEntityType(new XY(x, y));
    }

    @Override
    public XY getSize() {
        return board.getSize();
    }

    @Override
    public void tryMove(MiniSquirrel miniSquirrel, XY moveDirection) {
        if (miniSquirrel.getEnergy() <= 0) {
            kill(miniSquirrel);
            return;
        }

        Entity entity = cells[miniSquirrel.getPosition().getX() + moveDirection.getX()]
                [miniSquirrel.getPosition().getY() + moveDirection.getY()];

        if (entity instanceof Wall) {
            miniSquirrel.updateEnergy(entity.getEnergy());
            miniSquirrel.paralyze();
        } else if (entity instanceof GoodPlant) {
            miniSquirrel.updateEnergy(entity.getEnergy());
            killAndReplace(entity);
            move(miniSquirrel, moveDirection);
        } else if (entity instanceof BadPlant) {
            miniSquirrel.updateEnergy(entity.getEnergy());
            killAndReplace(entity);
            move(miniSquirrel, moveDirection);
        } else if (entity instanceof GoodBeast) {
            miniSquirrel.updateEnergy(entity.getEnergy());
            killAndReplace(entity);
            move(miniSquirrel, moveDirection);
        } else if (entity instanceof BadBeast) {
            miniSquirrel.updateEnergy(entity.getEnergy());
            ((BadBeast) entity).reduceBites();
        } else if (entity instanceof MasterSquirrel) {
            if (miniSquirrel.getMaster().equals(entity)) {
                entity.updateEnergy(miniSquirrel.getEnergy());
            }
            kill(miniSquirrel);
        } else if (entity instanceof MiniSquirrel) {
            if (!miniSquirrel.getMaster().equals(((MiniSquirrel) entity).getMaster())) {
                kill(miniSquirrel);
                kill(entity);
            }
        } else if (entity == null) {
            move(miniSquirrel, moveDirection);
        }
    }


    @Override
    public void tryMove(GoodBeast goodBeast, XY moveDirection) {
        goodBeast.updateWaitTime();
        PlayerEntity nearestSquirrel = nearestPlayerEntity(goodBeast.getPosition());
        if (nearestSquirrel != null) {
            if (nearestSquirrel.getPosition().getX() < goodBeast.getPosition().getX()) {
                moveDirection = new XY(1, moveDirection.getY());
            } else if (nearestSquirrel.getPosition().getX() > goodBeast.getPosition().getX()) {
                moveDirection = new XY(-1, moveDirection.getY());
            } else if (nearestSquirrel.getPosition().getY() < goodBeast.getPosition().getY()) {
                moveDirection = new XY(moveDirection.getX(), 1);
            } else if (nearestSquirrel.getPosition().getY() > goodBeast.getPosition().getY()) {
                moveDirection = new XY(moveDirection.getX(), -1);
            }
        }
        if (cells[goodBeast.getPosition().getX() + moveDirection.getX()]
                [goodBeast.getPosition().getY() + moveDirection.getY()] == null) {
            move(goodBeast, moveDirection);
        }

    }

    @Override
    public void tryMove(BadBeast badBeast, XY moveDirection) {
        badBeast.updateWaitTime();
        if (badBeast.getRemainingBites() == 0) {
            killAndReplace(badBeast);
            return;
        }

        PlayerEntity nearestSquirrel = nearestPlayerEntity(badBeast.getPosition());
        if (nearestSquirrel != null) {
            if (nearestSquirrel.getPosition().getX() < badBeast.getPosition().getX()) {
                moveDirection = new XY(-1, moveDirection.getY());
            }
            else if (nearestSquirrel.getPosition().getX() > badBeast.getPosition().getX()) {
                moveDirection = new XY(1, moveDirection.getY());
            }

            else if (nearestSquirrel.getPosition().getY() < badBeast.getPosition().getY()) {
                moveDirection = new XY(moveDirection.getX(), -1);
            }
            else if (nearestSquirrel.getPosition().getY() > badBeast.getPosition().getY()) {
                moveDirection = new XY(moveDirection.getX(), 1);
            }
        }
        Entity entity = cells[badBeast.getPosition().getX() + moveDirection.getX()]
                [badBeast.getPosition().getY() + moveDirection.getY()];

        if (entity instanceof MiniSquirrel) {
            entity.updateEnergy(badBeast.getEnergy());
            badBeast.reduceBites();
        }
        else if (entity instanceof MasterSquirrel) {
            entity.updateEnergy(badBeast.getEnergy());
            badBeast.reduceBites();
        }
        else if (entity == null) {
            move(badBeast, moveDirection);
        }
    }


        @Override
    public void tryMove(MasterSquirrel masterSquirrel, XY moveDirection) {
        Entity entity = cells[masterSquirrel.getPosition().getX() + moveDirection.getX()]
                [masterSquirrel.getPosition().getY() + moveDirection.getY()];

        if (entity instanceof Wall) {
            masterSquirrel.updateEnergy(entity.getEnergy());
            masterSquirrel.paralyze();

        } else if (entity instanceof GoodPlant) {
            masterSquirrel.updateEnergy(entity.getEnergy());
            killAndReplace(entity);
            move(masterSquirrel, moveDirection);

        } else if (entity instanceof BadPlant) {
            masterSquirrel.updateEnergy(entity.getEnergy());
            killAndReplace(entity);
            move(masterSquirrel, moveDirection);

        } else if (entity instanceof GoodBeast) {
            masterSquirrel.updateEnergy(entity.getEnergy());
            killAndReplace(entity);
            move(masterSquirrel, moveDirection);

        } else if (entity instanceof BadBeast) {
            masterSquirrel.updateEnergy(entity.getEnergy());
            ((BadBeast) entity).reduceBites();

        } else if (entity instanceof MiniSquirrel) {
            if (masterSquirrel == ((MiniSquirrel) entity).getMaster()) {
                masterSquirrel.updateEnergy(entity.getEnergy());
            } else {
                masterSquirrel.updateEnergy(150);
            }
            kill(entity);
            move(masterSquirrel, moveDirection);
        } else if (entity == null) {
            move(masterSquirrel, moveDirection);
        }


    }

    @Override
    public PlayerEntity nearestPlayerEntity(XY pos) {
        int radius = 6;
        ArrayList <PlayerEntity> nearestPlayers = new ArrayList<>();
        for (int x = pos.getX() - radius; x < pos.getX() + radius; x++) {
            for (int y = pos.getY() - radius; y < pos.getY() + radius; y++) {
                    if (cells[x][y] instanceof PlayerEntity) {
                        nearestPlayers.add((PlayerEntity) cells[x][y]);
                    }
            }
        }
        if (nearestPlayers.size() > 0) {
            Random random = new Random();
            return nearestPlayers.get(random.nextInt(nearestPlayers.size()));
        }
        return null;

    }

    @Override
    public void kill(Entity entity) {
        cells[entity.getPosition().getX()][entity.getPosition().getY()] = null;
    }

    @Override
    public void killAndReplace(Entity entity) {
        kill(entity);
        XY position = getValidPosition();
        Entity newEntity = null;

        if (entity instanceof GoodPlant) {
            newEntity = new GoodPlant(0, position);
        } else if (entity instanceof BadPlant) {
            newEntity = new BadPlant(0, position);
        } else if (entity instanceof GoodBeast) {
            newEntity = new GoodBeast(0, position);
        } else if (entity instanceof BadBeast) {
            newEntity = new BadBeast(0, position);
        }
        cells[position.getX()][position.getY()] = newEntity;

    }


    @Override
    public EntityType getEntityType(XY xy) {
        return EntityType.fromEntity(cells[xy.getX()][xy.getY()]);
    }

    private XY getValidPosition() {
        XY randomPosition;
        do {
            randomPosition = new XY((int) (Math.random() * (cells.length - 2)) + 1,
                    (int) (Math.random() * (cells[0].length - 2)) + 1);
        } while (cells[randomPosition.getX()][randomPosition.getY()] != null);
        return randomPosition;
    }

    public void move(Entity entity, XY moveCommand) {
        cells[entity.getPosition().getX()][entity.getPosition().getY()] = null;
        entity.setPosition(entity.getPosition().add(moveCommand));
        cells[entity.getPosition().getX()][entity.getPosition().getY()] = entity;
    }

    public void spawnMiniSquirrel(MasterSquirrel master, int energy) {

        // find a valid position around the mastersquirrel
        XY position = null;
        for (int i = - 1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (cells[master.getPosition().getX() + i][master.getPosition().getY() + j] == null) {
                    position = new XY(master.getPosition().getX() + i, master.getPosition().getY() + j);
                    break;
                }
            }
        }

        master.updateEnergy(energy * -1);
        MiniSquirrel newEntity = new MiniSquirrel(0, energy, position, master);

        cells[position.getX()][position.getY()] = newEntity;
    }

    public String toString(){
        return board.toString();
    }



}

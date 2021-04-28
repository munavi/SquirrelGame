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
            if (e != null)
                cells[e.getPosition().getX()][e.getPosition().getY()] = e;
        }
        this.board = board;

    }


    @Override
//    public EntityType getEntityType(int x, int y) {
//        return getEntityType(new XY(x, y));
//    }

    public Entity getEntityType(int x, int y) {
        return cells[x][y];
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
            System.out.println("Bad Beast died !");
            return;
        }

        PlayerEntity nearestSquirrel = nearestPlayerEntity(badBeast.getPosition());
        if (nearestSquirrel != null) {
            if (nearestSquirrel.getPosition().getX() < badBeast.getPosition().getX()) {
                moveDirection = new XY(-1, moveDirection.getY());
            } else if (nearestSquirrel.getPosition().getX() > badBeast.getPosition().getX()) {
                moveDirection = new XY(1, moveDirection.getY());
            } else if (nearestSquirrel.getPosition().getY() < badBeast.getPosition().getY()) {
                moveDirection = new XY(moveDirection.getX(), -1);
            } else if (nearestSquirrel.getPosition().getY() > badBeast.getPosition().getY()) {
                moveDirection = new XY(moveDirection.getX(), 1);
            }
        }
        Entity entity = cells[badBeast.getPosition().getX() + moveDirection.getX()]
                [badBeast.getPosition().getY() + moveDirection.getY()];

        if (entity instanceof MiniSquirrel) {
            entity.updateEnergy(badBeast.getEnergy());
            badBeast.reduceBites();
        } else if (entity instanceof MasterSquirrel) {
            entity.updateEnergy(badBeast.getEnergy());
            badBeast.reduceBites();
        } else if (entity == null) {
            move(badBeast, moveDirection);
        }
    }


    @Override
    public void tryMove(MasterSquirrel masterSquirrel, XY moveDirection) {
        if (masterSquirrel.getWaitTime() == 0) {
            Entity entity = cells[masterSquirrel.getPosition().getX() + moveDirection.getX()]
                    [masterSquirrel.getPosition().getY() + moveDirection.getY()];

            if (entity instanceof Wall) {
                masterSquirrel.updateEnergy(entity.getEnergy());
                masterSquirrel.paralyze();
                System.out.println("You hit a wall!");

            } else if (entity instanceof GoodPlant) {
                masterSquirrel.updateEnergy(entity.getEnergy());
                killAndReplace(entity);
                move(masterSquirrel, moveDirection);
                System.out.println("You ate a good plant!");

            } else if (entity instanceof BadPlant) {
                masterSquirrel.updateEnergy(entity.getEnergy());
                killAndReplace(entity);
                move(masterSquirrel, moveDirection);
                System.out.println("You ate a bad plant!");

            } else if (entity instanceof GoodBeast) {
                masterSquirrel.updateEnergy(entity.getEnergy());
                killAndReplace(entity);
                move(masterSquirrel, moveDirection);
                System.out.println("You ate a Good Beast");

            } else if (entity instanceof BadBeast) {
                if (((BadBeast) entity).getRemainingBites() == 0) {
                    killAndReplace(entity);
                    System.out.println("Bad Beast died !");
                    return;
                }
                masterSquirrel.updateEnergy(entity.getEnergy());
                ((BadBeast) entity).reduceBites();
                System.out.println("You were bitten by a Bad Beast");

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
        } else {
            masterSquirrel.reduceWaitTime();
        }


    }

    @Override
    public PlayerEntity nearestPlayerEntity(XY pos) {
        int radius = 6;
        ArrayList<PlayerEntity> nearestPlayers = new ArrayList<>();
        for (int x = pos.getX() - radius; x < pos.getX() + radius; x++) {
            if (x > 0 && x < board.getSize().getX()) {
                for (int y = pos.getY() - radius; y < pos.getY() + radius; y++) {
                    if (y > 0 && y < board.getSize().getY()) {
                        if (cells[x][y] instanceof PlayerEntity) {
                            nearestPlayers.add((PlayerEntity) cells[x][y]);
                        }
                    }
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
        board.getEntitySet().delete(entity);
        cells[entity.getPosition().getX()][entity.getPosition().getY()] = null;
    }

    @Override
    public void killAndReplace(Entity entity) {
        kill(entity);
        XY position = getValidPosition();
        Entity newEntity = null;

        if (entity instanceof GoodPlant) {
            newEntity = new GoodPlant(board.getId(), position);
            board.setId(board.getId() + 1);
            board.getEntitySet().push(newEntity);
        } else if (entity instanceof BadPlant) {
            newEntity = new BadPlant(board.getId(), position);
            board.setId(board.getId() + 1);
            board.getEntitySet().push(newEntity);
        } else if (entity instanceof GoodBeast) {
            newEntity = new GoodBeast(board.getId(), position);
            board.setId(board.getId() + 1);
            board.getEntitySet().push(newEntity);
        } else if (entity instanceof BadBeast) {
            newEntity = new BadBeast(board.getId(), position);
            board.setId(board.getId() + 1);
            board.getEntitySet().push(newEntity);
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

    private boolean testValidPosition(Entity entity) {
        if (entity.getPosition().getY() < 0 || entity.getPosition().getY() > board.getSize().getY() || entity.getPosition().getX() < 0 || entity.getPosition().getX() > board.getSize().getX()) {
            return false;
        }
        return true;
    }

    public void move(Entity entity, XY moveCommand) {
        cells[entity.getPosition().getX()][entity.getPosition().getY()] = null;
        entity.setPosition(entity.getPosition().add(moveCommand));
        cells[entity.getPosition().getX()][entity.getPosition().getY()] = entity;
    }

    public void spawnMiniSquirrel(MasterSquirrel master, int energy) throws NotEnoughEnergyException {

        if (master.getEnergy() - energy <= 0)
            throw new NotEnoughEnergyException("Squirrel doesn't have enough energy.");

        // find a valid position around the mastersquirrel
        XY position = null;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (cells[master.getPosition().getX() + i][master.getPosition().getY() + j] == null) {
                    position = new XY(master.getPosition().getX() + i, master.getPosition().getY() + j);
                    break;
                }
            }
        }

        master.updateEnergy(energy * -1);
        MiniSquirrel newEntity = new MiniSquirrel((board.getId() + 1), energy, position, master);

        cells[position.getX()][position.getY()] = newEntity;
        board.getEntitySet().push(newEntity);
    }

    public String toString() {
        return board.toString();
    }

    @Override
    public void visualize() {
        board.showInConsole();
    }


}

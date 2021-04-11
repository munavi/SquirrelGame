package de.hsa.fatsquirrel.core;

import java.util.Random;

public class Board {
    private Entity[][] entities;
    private BoardConfig boardConfig;
    private EntitySet entityBasket;
    private int id;

//    public static void main(String[] args) {        //testen
//        Board board = new Board(new BoardConfig());
//        board.toString();
//    }


    public Board(BoardConfig config) {
        this.boardConfig = config;
        this.entities = new Entity[config.getSize().getX()][config.getSize().getY()];
        this.entityBasket = new EntitySet(boardConfig.getHeight() * boardConfig.getWidth());
        init();


        // Ich würde es in einer Methode reinpacken, so z.B init() und die dann im Kostruktor ausführen
        // In der Methode init() werden dann 2 Methoden aufgerufen : createWalls() und andere platzierung entites ramdomly
        // z. b putEntitiesRandomly oder sowas
//        int id = 0;
//        int x = boardConfig.getSize().getX();
//        int y = boardConfig.getSize().getY();
//        entityBasket = new EntitySet((x * y));
//        for (int i = 0; i <= x; i++) {
//            entityBasket.push(new Wall(id, new XY(0, i)));    //populate first row with wall
//            id++;
//            entityBasket.push(new Wall(id, new XY(y, i)));    //populate last row with wall
//            id++;
//        }
//        for (int i = 1; i <= (y - 1); i++) {
//            entityBasket.push(new Wall(id, new XY(i, 0)));    //populate remaining left side with wall
//            id++;
//            entityBasket.push(new Wall(id, new XY(i, x)));    //populate remaining right side with wall
//            id++;
//        }
//
//        entityBasket.push(new GoodPlant(id, randomLocation()));
//        id++;
//        entityBasket.push(new BadPlant(id, randomLocation()));
//        id++;
//        entityBasket.push(new BadBeast(id, randomLocation()));
//        id++;

    }

    private void init() {
        createWalls(boardConfig.getWidth(), boardConfig.getHeight());
        //spawnEntitiesRandomly();
    }

//    private void spawnEntitiesRandomly(EntitySet entityBasket, int width, int height) {
//            XY randomPosition;
//            do {
//                randomPosition = new XY((int) (Math.random() * (width - 2)) + 1,
//                        (int) (Math.random() * (height - 2)) + 1);
//            } while (!entityBasket.isValidPosition(randomPosition));
//
//            switch (key) {
//                case "GoodPlant":
//                    entityBasket.push(new GoodPlant(0, randomPosition));
//                    break;
//                case "BadPlant":
//                    entityBasket.push(new BadPlant(0, randomPosition));
//                    break;
//                case "Wall":
//                    entityBasket.push(new Wall(0, randomPosition));
//                    break;
//                case "GoodBeast":
//                    entityBasket.push(new GoodBeast(0, randomPosition));
//                    break;
//                case "BadBeast":
//                    entityBasket.push(new BadBeast(0, randomPosition));
//                    break;
//                case "HandOperatedMasterSquirrel":
//                    entityBasket.push(new HandOperatedMasterSquirrel(0, randomPosition));
//                    break;
//                default:
//                    System.err.println(key);
//            }
//        }


    public XY randomLocation() {
        XY xy = new XY(randomXPosition(), randomYPosition());
        for (int i = 0; i < entityBasket.getContainer().length; i++) {
            if (entityBasket.getContainer()[i] != null) {
                if (entityBasket.getContainer()[i].position.equals(xy)) {
                    xy = randomLocation();
                }
            }
        }
        return xy;
    }

    public int randomXPosition() {
        Random rnd = new Random();
        int x = rnd.nextInt(boardConfig.getSize().getX());
        return x;
    }

    public int randomYPosition() {
        Random rnd = new Random();
        int y = rnd.nextInt(boardConfig.getSize().getY());
        return y;
    }

    private void createWalls(int width, int height) {
        // horizontal walls
        for (int x = 0; x < width; x++) {
            entityBasket.push(new Wall(id, new XY(x, 0)));
            id++;
            entityBasket.push(new Wall(id, new XY(x, height - 1)));
            id++;
        }

        // vertical walls
        for (int y = 1; y < height - 1; y++) {
            entityBasket.push(new Wall(id, new XY(0, y)));
            id++;
            entityBasket.push(new Wall(id, new XY(width - 1, y)));
            id++;
        }
    }

    public EntitySet getEntitySet() {
        return entityBasket;
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

    public String toString() {
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < entityBasket.getContainer().length; i++) {
            if (entityBasket.getElement(i) != null) {
                content.append(entityBasket.getElement(i).toString());
                content.append("\n");
            }
        }
        System.out.println(content);
        return content.toString();
    }


}

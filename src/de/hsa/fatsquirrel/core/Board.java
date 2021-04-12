package de.hsa.fatsquirrel.core;

//import java.util.Random;

public class Board {
    private BoardConfig boardConfig;
    private EntitySet entityBasket;
    private int id = 1;


    public Board(BoardConfig config) {
        this.boardConfig = config;
        this.entityBasket = new EntitySet(boardConfig.getHeight() * boardConfig.getWidth());
        init();
    
    }

    private void init() {

    	createWalls(boardConfig.getWidth(), boardConfig.getHeight());
    	generateAll("GoodPlant");
    	generateAll("BadPlant");
    	generateAll("BadBeast");
    	generateSingleEntity("Hamster");	//test invalid input
    
    }

    private void generateAll(String entityType) {	//generate the max number of one of the entity types (max number defined in BoardConfig)
    	int amount = 0;
    	switch(entityType) {
	    	case "GoodPlant":
	    		amount = boardConfig.getNumberGoodPlants();
	    		break;
	    	case "BadPlant":
	    		amount = boardConfig.getNumberBadPlants();
	    		break;
	    	case "GoodBeast":
	    		amount = boardConfig.getNumberGoodBeasts();
	    		break;
	    	case "BadBeast":
	    		amount = boardConfig.getNumberBadBeasts();
	    		break;
	    	default:
	    		System.err.println(entityType);
	    		return;
	     }
    	for(int i=0; i<amount; i++) {
    		generateSingleEntity(entityType);
    	}
    	
    }

    private void generateSingleEntity(String entityType) {
            XY randomPosition;
            do {
                randomPosition = new XY((int) (Math.random() * (boardConfig.getSize().getX() - 2)) + 1,
                        (int) (Math.random() * (boardConfig.getSize().getY() - 2)) + 1);
            }
            while (!entityBasket.isValidPosition(randomPosition));

            switch (entityType) {
                case "GoodPlant":
                    entityBasket.push(new GoodPlant(id, randomPosition));
                    id++;
                    break;
                case "BadPlant":
                    entityBasket.push(new BadPlant(id, randomPosition));
                    id++;
                    break;
                case "Wall":
                    entityBasket.push(new Wall(id, randomPosition));
                    id++;
                    break;
                case "GoodBeast":
                    entityBasket.push(new GoodBeast(id, randomPosition));
                    id++;
                    break;
                case "BadBeast":
                    entityBasket.push(new BadBeast(id, randomPosition));
                    id++;
                    break;
                case "HandOperatedMasterSquirrel":
                    entityBasket.push(new HandOperatedMasterSquirrel(id, randomPosition));
                    id++;
                    break;
                default:
                    System.err.println(entityType + " ist kein gültiges Wesen.");
                    return;
            }
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

    public void showInConsole() {
		String[][] visual = new String[boardConfig.getSize().getX()][boardConfig.getSize().getY()];

		for(int i=0; i < entityBasket.getContainer().length; i++) {
			if(entityBasket.getElement(i)!=null) {
			int x = entityBasket.getElement(i).getPosition().getX();
			int y = entityBasket.getElement(i).getPosition().getY();
			
				if(entityBasket.getElement(i) instanceof Wall) {
					visual[x][y] = "[]";
				}
				if(entityBasket.getElement(i) instanceof GoodBeast) {
					visual[x][y] = "GB";
				}
				if(entityBasket.getElement(i) instanceof BadBeast) {
					visual[x][y] = "BB";
				}
				if(entityBasket.getElement(i) instanceof GoodPlant) {
					visual[x][y] = "GP";
				}
				if(entityBasket.getElement(i) instanceof BadPlant) {
					visual[x][y] = "BP";
				}
				if(entityBasket.getElement(i) instanceof MasterSquirrel) {
					visual[x][y] = "Ma";
				}
				if(entityBasket.getElement(i) instanceof MiniSquirrel) {
					visual[x][y] = "Mi";
				}
			}
		}
		
		for(int i=0; i<boardConfig.getSize().getX(); i++) {
    		for(int j=0; j<boardConfig.getSize().getY(); j++) {
    			if(visual[i][j]==null) {
    				visual[i][j] = "  ";
    			}
    		}
		}
		
		for(int i=0; i<boardConfig.getSize().getX(); i++) {
			System.out.println();
    		for(int j=0; j<boardConfig.getSize().getY(); j++) {
    			System.out.print(visual[i][j]);
    		}
		}
		System.out.println();
    }

    public int getId(){return id;}

    public void setId(int id){
        this.id = id;
    }
}

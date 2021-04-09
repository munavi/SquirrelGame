package de.hsa.fatsquirrel;

import java.util.Random;

public class Board {
    private Entity[][] entities;
    private BoardConfig boardConfig;
    private EntitySet entityBasket;
    
    public static void main(String[] args) {		//testen
    	Board board = new Board(new BoardConfig(new XY(5, 6), 10));
    	board.toString();
    }
    

    public Board(BoardConfig config) {
        this.boardConfig = config;
        this.entities = new Entity[config.getSize().getX()][config.getSize().getY()];
        
        int id = 0;
        int x = boardConfig.getSize().getX();
        int y = boardConfig.getSize().getY();
        entityBasket = new EntitySet((x*y));
        for(int i=0; i<=x; i++) {
        	entityBasket.push(new Wall(id, new XY(0, i)));	//populate first row with wall
        	id++;
        	entityBasket.push(new Wall(id, new XY(y ,i)));	//populate last row with wall
        	id++;
        }
        for(int i=1; i<=(y-1); i++) {
        	entityBasket.push(new Wall(id, new XY(i, 0)));	//populate remaining left side with wall
        	id++;
        	entityBasket.push(new Wall(id, new XY(i, x)));	//populate remaining right side with wall
        	id++;
        }
        
        entityBasket.push(new GoodPlant(id, randomLocation()));
        id++;
        entityBasket.push(new BadPlant(id, randomLocation()));
        id++;
        entityBasket.push(new BadBeast(id, randomLocation()));
        id++;
        
    }
    
    public XY randomLocation() {
    	XY xy = new XY(randomXPosition(), randomYPosition());
    	for(int i=0; i<entityBasket.getContainer().length; i++) {
    		if (entityBasket.getContainer()[i]!= null) {
    			if(entityBasket.getContainer()[i].position.equals(xy)) {
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
		for(int i=0; i<entityBasket.getContainer().length; i++) {
			if(entityBasket.getElement(i) != null) {
				content.append(entityBasket.getElement(i).toString());
				content.append("\n");
			}
		}
		System.out.println(content);
		return content.toString();
	}
    
    

}

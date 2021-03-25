package SquirrelGame;

public class GameState {
    public static void main(String[] args){
        EntitySet container = new EntitySet(15);
        container.push(new BadPlant(0,new XY(0,0)));
        container.push(new GoodPlant(1, new XY(2,3)));
        container.push(new BadBeast(2, new XY(4,5)));
        container.push(new GoodBeast(3, new XY(1,1)));
        container.push(new Wall(4, new XY(7,7)));
        container.push(new HandOperatedMasterSquirrel(5, new XY(5,5)));

        System.out.println(container.toString());

        HandOperatedMasterSquirrel squirrel = new HandOperatedMasterSquirrel(5, new XY(0,1));
        squirrel.nextStep();




    }
}

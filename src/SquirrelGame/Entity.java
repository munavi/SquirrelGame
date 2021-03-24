package SquirrelGame;

public class Entity {
    private int id;
    private int energy;
    private XY location;

    public Entity(int id, int energy, XY loc){
        this.id = id;
        this.energy = energy;
        this.location = loc;
    }


    public int getId() {
        return id;
    }

    public int getEnergy() {
        return energy;
    }

    public XY getLocation() {
        return location;
    }


    public int updateEnergy(int delta){
        if( delta >= 0){
        return energy += delta;
        }
        return energy -= delta;
    }
}

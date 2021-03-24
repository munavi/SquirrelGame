package SquirrelGame;

public class Entity {
    private final int id;
    private int energy;
    private final XY location;

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

    public boolean equals(Object obj) { // ob zwei Entities diesselbe Entities implementieren
        if (obj instanceof Entity) {
            return obj.getClass() == this.getClass() && ((Entity) obj).getId() == this.getId();
        }
        return false;
    }
}

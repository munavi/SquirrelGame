package SquirrelGame;

public abstract class Entity {
    private final int id;
    private int energy;
    protected  XY location;
    private EntitySet set;

    public Entity(int id, int energy, XY loc){
        this.id = id;
        this.energy = energy;
        this.location = loc;
    }
    public void setEntity(EntitySet set) {
        if (set == null){
            System.err.println(this + " has been removed from the set!");
        }
        this.set = set;
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

    public abstract void nextStep();
}

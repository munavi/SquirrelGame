package de.hsa.fatsquirrel;

public abstract class Entity {
    private final int id;
    private int energy;
    protected  XY position;

    protected Entity(int id, int energy, XY loc){
        this.id = id;
        this.energy = energy;
        this.position = loc;
    }
    public Entity setEntity(Entity set) {
        if (set == null){
            System.out.flush();
            System.err.println(this + " has been removed from the set!");
            return null;
        }else{
            return this;
        }

    }

    public void setPosition(XY pos) {
        position = pos;
    }


    public int getId() {
        return id;
    }

    public int getEnergy() {
        return energy;
    }
    public abstract int getStartEnergy();

    public XY getPosition() {
        return position;
    }


    public int updateEnergy(int delta){
        return energy += delta;
    }

    public boolean equals(Object obj) { // ob zwei Entities diesselbe Entities implementieren
        if (obj instanceof Entity) {
            return obj.getClass() == this.getClass() && ((Entity) obj).getId() == this.getId();
        }
        return false;
    }

    public abstract void nextStep(); // hier steht im Klassendiagramm EntityContext context. Wieso?
}

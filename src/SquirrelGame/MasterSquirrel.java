package SquirrelGame;

public class MasterSquirrel extends Squirrel {
    public final static int START_ENERGY = 1000;

    public MasterSquirrel(int id, XY loc) {
        super(id, START_ENERGY, loc);
    }


    public boolean isMySquirrel(Entity e) {
        if (e instanceof MiniSquirrel) {
            return ((MiniSquirrel) e).getMaster() == this;
        }
        return false;
    }

    public String toString(){
        return super.toString() + " of type " + this.getClass();
    }
}

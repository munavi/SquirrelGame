package de.hsa.fatsquirrel;

public enum MoveCommand {
    LEFT,
    RIGHT,
    UP,
    DOWN;

    public XY toXY() {
        switch (this) {

            case LEFT:
                return new XY(-1, 0);
            case RIGHT:
                return new XY(1, 0);
            case UP:
                return new XY(0, 1);
            case DOWN:
                return new XY(0, -1);
        }
        return null;
    }


}

package de.hsa.fatsquirrel.core;

public class NotEnoughEnergyException extends Throwable {
    public NotEnoughEnergyException(String s) {
        super(s);
    }

    public NotEnoughEnergyException() {
        super();
    }

}

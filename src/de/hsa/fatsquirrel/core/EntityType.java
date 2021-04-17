package de.hsa.fatsquirrel.core;

public enum EntityType {
    GoodBeast,
    BadBeast,
    GoodPlant,
    BadPlant,
    Wall,
    MasterSquirrel,
    MiniSquirrel,
    HandOperatedMasterSquirrel,
    EmptyField;

    public static EntityType fromEntity(Entity entity) {
        if (entity instanceof GoodBeast)
            return EntityType.GoodBeast;
        else if (entity instanceof BadBeast)
            return EntityType.BadBeast;
        else if (entity instanceof GoodPlant)
            return EntityType.GoodPlant;
        else if (entity instanceof BadPlant)
            return EntityType.BadPlant;
        else if (entity instanceof Wall)
            return EntityType.Wall;
        else if (entity instanceof MasterSquirrel)
            return EntityType.MasterSquirrel;
        else if (entity instanceof MiniSquirrel)
            return EntityType.MiniSquirrel;
        else if (entity instanceof HandOperatedMasterSquirrel) {
            return EntityType.HandOperatedMasterSquirrel;
        } else
            return EntityType.EmptyField;
    }

}

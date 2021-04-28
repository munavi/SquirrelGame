package de.hsa.fatsquirrel.core;

public enum EntityType {
    GOOD_BEAST,
    BAD_BEAST,
    GOOD_PLANT,
    BAD_PLANT,
    WALL,
    MASTER_SQUIRREL,
    MINI_SQUIRREL,
    HAND_OPERATED_MASTER_SQUIRREL,
    EMPTY_FIELD;

    public static EntityType fromEntity(Entity entity) {
        if (entity instanceof GoodBeast)
            return EntityType.GOOD_BEAST;
        else if (entity instanceof BadBeast)
            return EntityType.BAD_BEAST;
        else if (entity instanceof GoodPlant)
            return EntityType.GOOD_PLANT;
        else if (entity instanceof BadPlant)
            return EntityType.BAD_PLANT;
        else if (entity instanceof Wall)
            return EntityType.WALL;
        else if (entity instanceof MasterSquirrel)
            return EntityType.MASTER_SQUIRREL;
        else if (entity instanceof MiniSquirrel)
            return EntityType.MINI_SQUIRREL;
        else if (entity instanceof HandOperatedMasterSquirrel) {
            return EntityType.HAND_OPERATED_MASTER_SQUIRREL;
        } else
            return EntityType.EMPTY_FIELD;
    }

//    public static Entity whichEntity(EntityType entityType){
//        Entity entity = null;
//        if(entityType == EntityType.GOOD_BEAST){
//            return new GoodBeast()
//        }
//    }

}

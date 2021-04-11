package de.hsa.fatsquirrel.console;

import de.hsa.fatsquirrel.core.BoardView;
import de.hsa.fatsquirrel.core.EntityType;

public class ConsoleUI implements UI {
    private String FIELD_SEPERATOR = "|";
    private MoveCommand puffer;

    @Override
    public MoveCommand getCommand() {
        MoveCommand command = puffer;
        puffer = null;
        return command;

    }

    @Override
    public void render(BoardView view) {
//        String result = "";
//        for (int y = 0; y < view.getSize().getY(); y++) {
//            for (int x = 0; x < view.getSize().getX(); x++) {
//                EntityType iterField = view.getEntityType(x, y);
//                if (iterField == null)
//                    result += "__";
//                else
//                    result += toString(iterField) + FIELD_SEPERATOR;
//            }
//        }
        view.toString();


    }

    private String toString(EntityType entity) {
        switch (entity) {
            case GoodBeast:
                return "GB";
            case BadBeast:
                return "BB";
            case GoodPlant:
                return "GP";
            case BadPlant:
                return "BP";
            case Wall:
                return "WA";
            case MasterSquirrel:
                return "MS";
            case MiniSquirrel:
                return "mS";
            default:
                return null;
        }
    }




}



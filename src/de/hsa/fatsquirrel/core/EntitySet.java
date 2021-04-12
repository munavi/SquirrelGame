package de.hsa.fatsquirrel.core;

public class EntitySet {
    private final Entity[] container;
    private int number;
    private boolean sort = false;

    public EntitySet(int size){
        number = 0;
        container = new Entity[size];
    }

    public void push(Entity e){
        for (Entity entity : container ) {
            if (e.equals(entity))
                return;
        }
        if (number < container.length ){
            container[number++] = e;
        }
    }

    public boolean isEmpty(){
        return number ==0;
    }

    public void delete(Entity e){
        for (int i = 0; i < number; i++) {
            if (container[i] != null && container[i].equals(e)){
                container[i] = e.setEntity(null);
                break;
            }
        }
    }

    public void sort(){
        for (int i = 0; i < number - 1; i++) { //System.arraycopy()?
            if(container[i] == null) {
                container[i] = container[i + 1];
                container[i+1] = null;
            }
        }
        number--;
    }

//    public void nextStep(){
//        for(int i = 0; i<container.length;i++){
//            if (container[i]!= null) {
//
//                container[i].nextStep();
//
//                if (container[i] instanceof HandOperatedMasterSquirrel) {
//                    for (int j = 0; j < container.length; j++) {
//                        if (container[j] instanceof GoodPlant && ((HandOperatedMasterSquirrel) container[i]).newLocation.equals(container[j].getPosition()) ) {
//                            container[i].updateEnergy(container[j].getEnergy());
//                            delete(container[j]);
//                            sort = true;
//                            System.out.println("Eine Pflanze wurde gefressen");
//                        }
//                        container[i].position = ((HandOperatedMasterSquirrel) container[i]).newLocation;
//                    }
//                }
//            }
//        }
//        if(sort){
//            sort();
//            sort = false;
//        }
//    }

    // nextStep nur für Character
    public void nextStep(State state){
        for (Entity entity: container){
            if( entity instanceof Character){
                ((Character) entity).nextStep(state.flattenedBoard());
            }
        }
    }

    public String toString() {
        StringBuilder content = new StringBuilder();

        for (int i = 0; i < number; i++) {
            content.append(container[i].toString());
            content.append("\n");
        }
        return content.toString();
    }

    public Entity[] getContainer() {
    	return container;
    }
    
    public Entity getElement(int elementIndex) {
    	return container[elementIndex];
    }

    public boolean isValidPosition(XY xy) {
        for (Entity e : container) {
        	if(e != null) {
	            if (xy.equals(e.getPosition())) {
	                return false;
	           	}
	        }
        }
        return true;
    }


}

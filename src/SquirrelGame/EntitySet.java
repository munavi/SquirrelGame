package SquirrelGame;

public class EntitySet {
    private final Entity[] container;
    private int number;

    public EntitySet(int size){
        number = 0;
        container = new Entity[size];
    }

    public void push(Entity e){
        for (Entity entity : container) {
            if (e.equals(entity))
                return;
        }
        if (number < container.length){
            container[number++] = e;
            e.setEntity(this);
        }
    }

    public boolean isEmpty(){
        return number ==0;
    }

    public void delete(Entity e){
        for (int i = 0; i < number; i++) {
            if (container[i].equals(e)){
                e.setEntity(null);
                System.out.println(e + " has been removed from the set!");
                break;
            }
        }
        for (int i = 0; i < number - 1; i++) { //System.arraycopy()?
            container[i] = container[i + 1];
        }
        number--;
    }

    public String toString() {
        StringBuilder content = new StringBuilder();

        for (int i = 0; i < number; i++) {
            content.append(container[i].toString());
            content.append("\n");
        }
        return content.toString();
    }



}

import java.util.ArrayList; 

/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    private Room currentRoom;
    private String name;
    private Item item;
    private ArrayList<Item> items;
   public static final int MAX_INVENTORY_WEIGHT = 1500;
   private int inventoryWeight;
    
    
/**
     * Takes item from current room
     * @param item
     */
    public void takeItem(Item item){
        items.add(item);
        inventoryWeight += item.getWeight();
}

public Item getItem(){
        return item;
}
    
public Player(String name, Room startingRoom){
        this.name = name;
        this.currentRoom = startingRoom;
        items = new ArrayList<>();
        inventoryWeight = 0;
}

public int getInventoryWeight(){
        return inventoryWeight;
}

public void itemInventory(){
    System.out.println("Current player inventory:");
    for(Item i : items){
        System.out.println(i.getItemInfo());
    }
    System.out.print("Current load:" + inventoryWeight + "g.\n");
}
    
public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
}
    
public Room getCurrentRoom() {
        return currentRoom;
}
    
public String getName() {
        return name;
}
    
    /**
     * Drops item in the current room
     * @param itemName The item to be removed
     */
    public Item dropItem(String itemName){
        Item item = null;
        for(Item i : items){
        if(i.getDescription().equals(itemName)) {
        item = i;
        System.out.println(item.getDescription() + " dropped");
        inventoryWeight -= item.getWeight();
    }
}
items.remove(item);
if (item == null) {
    System.out.println("this player does not have such item.");
}
System.out.println("Current player inventory:");
for(Item i : items){
    System.out.println(i.getItemInfo());
}
System.out.print("Current carying " + "capacity: " + inventoryWeight + " g.");
return item;
}
}

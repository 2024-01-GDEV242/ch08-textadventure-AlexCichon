import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Player.java. Will stores information about the player character and his inventory.
 * 
 * @Alexei Cichon
 * @version 3/27/2024
 */
public class Player
{
    private Room currentRoom;
    private HashMap<String, Item> inventory;

    public Player()
    {
        currentRoom = new Room("starting room");
        inventory = new HashMap<String, Item>();
    }

    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    public void setCurrentRoom(Room newRoom)
    {
        currentRoom = newRoom;
    }
    
    /**
     * Goes through the inventory, adding all items
     * to a String than it returns it.
     */
    public String getInventoryString()
    {
        String returnString = "Inventory:";
        Set<String> keys = inventory.keySet();
        for(String item : keys)
            returnString += " " + item;
        return returnString;
    }
    
    /**
     * Command will check the player's inventory to see if we have a key.
     */
    public boolean checkKey()
    {
        Set<String> keys = inventory.keySet();
        for(String item : keys)
            if (item.equals("key"))
                return true;
        return false;
    }
    
    /**
     * Will return a message string of the examined items description
     */
    public String getExamineString(String name)
    {
        String returnString = "You examine the " + name + ".\n";
        Item temp = inventory.get(name);
        if (temp != null) {
            returnString += "It's " + temp.getDescription() + ".";
            return returnString;
        }
        return "You can only examine items in your inventory.";
    }
    
    /**
     * Adds the item of name/description to the players inventory.
     */
    public void addInventory(String name, String description)
    {
        Set<String> keys = inventory.keySet();
        for(String item : keys) {
            if (item.equals(name))
            {
                System.out.println("We've already got one!");
                return;
            }   
        }
        Item newItem = new Item(description, name);
        inventory.put(name, newItem);
    }
   
    /**
     * Will Directily add an item to the player inventory.
     */
    public void addInventory(Item item)
    {
        inventory.put(item.getName(), item);
    }
    
    /**
     * Will Drop an item from the inventory, returning it.
     */
    public Item dropInventory(String name)
    {
        Set<String> keys = inventory.keySet();
        for(String item : keys) {
            if (item.equals(name))
            {
                Item temp = inventory.get(name);
                inventory.remove(name);
                return temp;

            }   
        }
        System.out.println("We've haven't got one!");
        return null;

    }
   
    /**
     * Will Drop all items from the inventory, returning the first.
     */
    public Item dropInventory()
    {
        Item temp = inventory.entrySet().iterator().next().getValue();
        inventory.clear();
        return temp;
    }
}

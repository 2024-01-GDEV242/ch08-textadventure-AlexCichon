import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "A theifs trail " application. 
 * "A Theifs Trail" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Alexei Cichon
 * @version 03/27/2024
 */

public class Room 
{
    private String description;
    private HashMap<String, Exit> exits;        // allows  stores exits of this room.
    private HashMap<String, Item> items;        // allows stores items of this room.
    private HashMap<String, NPC> npcs;
    private HashMap<String, Door> doors;

    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "in a kitchen" or "in an open court 
     * yard".
     * @param description, The rooms Description
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Exit>();
        items = new HashMap<String, Item>();
        npcs = new HashMap<String, NPC>();
        doors = new HashMap<String, Door>();
    }
    
    /**
     * Adds an NPC described by a name/desc and with an item of name/desc to the room they need.
     * @param A Npc in which the player will be able to interact with
     */
    public void addNPC(String name, String description, String itemname, String itemdesc)
    {
    
        Set<String> keys = npcs.keySet();
        for(String npc : keys)
            if (npc.equals(name))
                return;
    
        NPC newNPC = new NPC(name, description, itemname, itemdesc);
        npcs.put(name, newNPC);
        
    }
    
    /**
     * @param the item
     * Adds an item to the items list for the player to interact with
     */
    public void setItem(String name, String description)
    {
        
        Set<String> keys = items.keySet();
        for(String item : keys)
            if (item.equals(name))
                return;
        
        Item newItem = new Item(description, name);
        items.put(name, newItem);
    }
    
    
    /**
     * @Param Item remove room
     * Removes an item from the room and returns it.
     */
    public Item delItem(String name)
    {
     
        Set<String> keys = items.keySet();
        for(String item : keys) {
            if (item.equals(name))
            {
                Item temp = items.get(name);
                items.remove(name);
                return temp;
            }            
        }
        System.out.println("That isn't here.");
        return null;
        
    }

    /**
     * @param Exit of room
     * Define an exit from this room.
     */
    public void setExit(String direction, Room neighbor) 
    {
        Exit temp = new Exit(direction, neighbor);
        exits.put(direction, temp);
    }
    
    /**
     * @param Locked Door/End goal
     * Defines a lockable exit, or door, for the room.
     */
    public void setDoor(String direction, Room neighbor, boolean locked)
    {
        Door temp = new Door(direction, neighbor, locked);
        doors.put(direction, temp);
    }

    /**
     * @Param Room decription
     * Return the description of the room 
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a long description of this room, in the form:
     *   @param returns long description of room form
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + "\n" + getDoorString() + "\n" + getItemString() + "\n" + getNPCString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @param The room to where needs key for exit
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys)
            returnString += " " + exit;
        return returnString;
    }
    
    /**
     * Return a string containing the room's doors, for example
     * @param Will allow the use of key for door to be used
     */
    private String getDoorString()
    {
        String returnString = "Doors:";
        Set<String> keys = doors.keySet();
        for(String door : keys)
            returnString += " " + door;
        return returnString;
    }
    
    /**
     * Return a string containing the room's items.
     */
    private String getItemString()
    {
        String returnString = "Items:";
        Set<String> keys = items.keySet();
        for(String item : keys)
            returnString += " " + item;
        return returnString;
    }
    
    /**
     * @param Npc to where player can obtain key
     * Return a string containing the NPCs in a room.
     */
    private String getNPCString()
    {
        String returnString = "NPCS:";
        Set<String> keys = npcs.keySet();
        for(String npc : keys)
            returnString += " " + npc;
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     */
    public Room getExit(String direction) 
    {
        Exit tempExit = exits.get(direction);
        if (tempExit != null)
        {
            return tempExit.getNeighbor();
        }
        return null;
    }
    
    /**
     * Return the room reached if we go from this room in direction "direction."
     * If there is no room in that direction, return null.
     */
    public Room getDoor(String direction)
    {
        Door tempDoor = doors.get(direction);
        if (tempDoor != null)
        {
            return tempDoor.getNeighbor();
        }
        return null;
    }
    
    public boolean getLocked(String direction)
    {
        return doors.get(direction).getLocked();
    }
    
    public Door getActualDoor(String direction)
    {
        return doors.get(direction);
    }
    
    public Item getItem(String name)
    {
        return items.get(name);
    }
    
    public NPC getNPC()
    {
        return npcs.entrySet().iterator().next().getValue();
    }    
}


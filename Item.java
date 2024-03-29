

/**
 * Item.java.Holds information about each item that is in the game.
 * 
 * @author Alexei Cichon
 * @version 03/27/2024
 */
public class Item
{
    private String description;
    private String name;

    /**
     * Constructor for objects of class Item
     */
    public Item(String description, String name)
    {
        this.description = description;
        this.name = name;
    }


    public String getDescription()
    {
        return description;
    }
    
    public String getName()
    {
        return name;
    }
}

/**
 * NPC.java. Will store the  information about the NPC(non playable character)
 * 
 * @author Alexei Cichon
 * @version 03/27/2024
 */
public class NPC extends Player
{

    private String name;
    private String description;


    public NPC(String name, String description, String itemname, String itemdesc)
    {
        this.description = description;
        this.name = name;
        addInventory(itemname, itemdesc);
        
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
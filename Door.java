
/**
 * Door.Java is the information that it is stored for the locked door that is locked
 * @author Alexei Cichon
 * @version 03/27/2024
 */
public class Door extends Exit
{
    private boolean locked;

    /**
     * Constructor for objects of class Door
     */
    public Door(String direction, Room neighbor, boolean locked)
    {
        this.locked = locked;
        this.setDirection(direction);
        this.setNeighbor(neighbor);
        
    }
     
    /**
     * Locks this door.
     */
    public void lock()
    {
        locked = true;
        return;
    }
    
    /**
     * Unlocks this door.
     */
    public void unlock()
    {
        locked = false;
        return;
    }
    
    public boolean getLocked()
    {
        return locked;
    }
}
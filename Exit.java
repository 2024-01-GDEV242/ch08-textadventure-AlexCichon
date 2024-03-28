/**
 * Exit.Java is to store the information about the exits
 * @Alexei Cichon
 * @3/27/2024
 */
public class Exit
{
    private String direction;
    private Room neighbor;

    /**
     * The constructor for objects of the class Exit
     */
     
     public Exit()
     {
     }
     
    public Exit(String direction, Room neighbor)
    {
        this.direction = direction;
        this.neighbor = neighbor;

    }
    
    public String getDirection()
    {
        return direction;
    }
    
    public Room getNeighbor()
    {
        return neighbor;
    }
    
    public void setDirection(String direction)
    {
        this.direction = direction;
    }
    
    public void setNeighbor(Room neighbor)
    {
        this.neighbor = neighbor;
    }
    
}

/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item {
    private String description;
    private String weight;
    
    public Item(String description, String weight){
        this.description = description;
        this.weight = weight;
    }
    
    public String getItemInfo() {
        if(description.equals(""))
        return "";
        return "there is " + description + ", weight: " + weight;
    }
}
    

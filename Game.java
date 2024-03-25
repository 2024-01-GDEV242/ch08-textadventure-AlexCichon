import java.util.Stack;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room previousRoom;
    private Stack<Room> roomPath;
    private Player player;
  
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room Entrance, Barracks, HoldingCells, Garden, CastleYard, Shrine, ArtistryHall, TreasuryRoom, FoodStorageRoom,
        Kitchen, EatingHall, Library, BasicStorageRoom, StudyRoom, NoblemenBathroom, MasterBedroom;
      
        // create the rooms
        Entrance = new Room("Entrance of the Noblemen Castle");
        Barracks = new Room("now in the Barracks of the castle");
        Kitchen = new Room("now in the kitchen of the castle");
        FoodStorageRoom = new Room("now in the food storage room of the castle");
        BasicStorageRoom = new Room("now in the basic storage room of the castle");
        EatingHall = new Room("now in the eating hall of the castle");
        ArtistryHall = new Room("now in the artistry hall of the castle");
        TreasuryRoom= new Room("now in the treasury room of the caslte");
        FoodStorageRoom = new Room("now in the food storage room of the castle");
        HoldingCells = new Room("now in the holding cells of the castle");
        Shrine = new Room("now in the shrine room of the castle");
        CastleYard = new Room("now in the castle yard");
        Library = new Room("now in the library of the castle");
        StudyRoom = new Room("now in the study room of the castle");
        NoblemenBathroom = new Room("now in the noblemens bathroom");
        MasterBedroom = new Room("now in the master bedroom of the noblemen");
        Garden  = new Room("now in the garden of the castle");
        
        // initialise room exits
        Entrance.setExit("north", Barracks);
        Entrance.setExit("east", ArtistryHall);
        Entrance.setExit("south", Kitchen);

        Barracks.setExit("east", HoldingCells);
        Barracks.setExit("south", Entrance);
        
        HoldingCells.setExit("west", Barracks);

        Kitchen.setExit("west", FoodStorageRoom);
        Kitchen.setExit("south", BasicStorageRoom);
        Kitchen.setExit("east", EatingHall);
        Kitchen.setExit("north", Entrance);
        
        FoodStorageRoom.setExit("east", Kitchen);
        
        BasicStorageRoom.setExit("north", Kitchen);
        

        EatingHall.setExit("west", Kitchen);
        
        ArtistryHall.setExit("west", Entrance);
        ArtistryHall.setExit("east", TreasuryRoom);
        ArtistryHall.setExit("south", Library);
        
        TreasuryRoom.setExit("west", ArtistryHall);
        
        Library.setExit("north", ArtistryHall);
        Library.setExit("east", StudyRoom);
        
        StudyRoom.setExit("south", MasterBedroom);
        StudyRoom.setExit("west", Library);
        StudyRoom.setExit("east", NoblemenBathroom);
        StudyRoom.setExit("north", Shrine);
        
        MasterBedroom.setExit("north", StudyRoom);
        
        NoblemenBathroom.setExit("west", StudyRoom);
        
        Shrine.setExit("south", StudyRoom);
        Shrine.setExit("west", CastleYard);
        
        CastleYard.setExit("east", Shrine);
        CastleYard.setExit("west", Garden);
        
        Garden.setExit("east", CastleYard);

       

        player = new Player("Alex", Entrance); 
        // start game outside
        roomPath = new Stack<>();
        //initialize the room path stack
        
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing" + player.getName()+ ". Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to A Theifs Trail");
        System.out.println("A Theifs Trail, is game where the theif must steal the nobles crown");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
                
            case LOOK:
                look();
                break;
            
            case EAT:
                eat();
                break;
            
            case BACK:
                back();
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are a theif. Your goal is to steal the noblemens locked crown");
        System.out.println("It is hidden next to the Artistry Hall behind a locked door.");
        //System.out.println("You are" + currentRoom.getDescription);
        System.out.println("Your command words are:");        
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }
        

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;

        if(direction.equals("north")) {
            nextRoom = player.getCurrentRoom().getExit("north");
        }
        if(direction.equals("east")) {
            nextRoom = player.getCurrentRoom().getExit("east");
        }
        if(direction.equals("south")) {
            nextRoom = player.getCurrentRoom().getExit("south");
        }
        if(direction.equals("west")) {
            nextRoom = player.getCurrentRoom().getExit("west");
        }

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            roomPath.push(player.getCurrentRoom());
            player.setCurrentRoom(nextRoom);
            System.out.println(currentRoom.getLongDescription());
        }
    }
    
    /**
     * Will allow the player to look around the room they are in and see different exits that are available.
     */
    private void look()
    {
        System.out.println(player.getCurrentRoom().getLongDescription());
    }
    
    /**
     * Will print out if player decides to use eat command to increase health
     */
    private void eat()
    {
        System.out.println("You have eaten a small snack, you are no longer hungry.");
    }
    
    private void back(){
        if(roomPath.empty()) {
            System.out.println("You are at the start, there is no previous room.");
        } else {
            // The previous room becomes the current,
            // and the vice-versa as well
           player.setCurrentRoom(roomPath.pop());
           System.out.println(currentRoom.getLongDescription());
        }
    }
    
    /**
     * Take an item from the current room
     */
    private void take(Command command){
        if(!command.hasSecondWord()) {
            System.out.println("Take what?");
            return;
        }
        String itemName = command.getSecondWord();
        Item item = player.getCurrentRoom().getItem(itemName);
        int playerCapacity = Player.MAX_INVENTORY_WEIGHT;
        if(player.getInventoryWeight() + item.getWeight() <= playerCapacity) {
            player.takeItem(item);
            System.out.println(item.getDescription() + "  taken");
            player.getCurrentRoom().removeItem(item);
        } else {
            System.out.println("The item load too heavy! Try dropping some items.");
        }
        System.out.println("Current capactity: " + player.getInventoryWeight() + " g.");
    }
    
    /**
     * Drop an item in the current room
     */
    private void drop(Command command){
        if(!command.hasSecondWord()) {
            System.out.println("Drop what?");
            return;
        }
        String itemName = command.getSecondWord();
        player.getCurrentRoom().addItem(player.dropItem(itemName));
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}

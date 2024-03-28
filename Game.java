/**
 * This class is part of the "A theifs trail" application. 
 * "A theifs trail" is a very simple, text based adventure game.
 * Users can walk around some scenery. That's all. It should really be extended to make it more interesting!
 *
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 *  
 * This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 *  
 * @author  Alexei Cichon
 * @version 03/27/2024
 */

public class Game 
{
    private Parser parser;
    private Player player;
    private NPC porter;
        
        /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        player = new Player();
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
        ArtistryHall.setDoor("locked door", TreasuryRoom, true);
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

        
        // initialise room items
        StudyRoom.setItem("Fancy Pen", "A fancy pen the noble uses to write, could be worth something");
        Kitchen.setItem("sandwich", "a yummy sandwich");
        HoldingCells.setItem("sword", "a sword uses to...you dont wanna know");
        Garden.setItem("blue flower", "a blue flower that stands out in the garden");
        Library.setItem("Gold book", "a gold book that twinkles on the shelf");
        MasterBedroom.setItem("Noble Crown", "the crown of the noble of the castle, take it if you dare...");
        TreasuryRoom.setItem("Gold Coins", "all around the room there are coins for the taking");
        EatingHall.setItem("silverware", "some fancy silverware laying about, its made from true silver");
        
        
        
        player.addInventory("bagel", "half a strawberry bagel");

        Library.addNPC("Dwarf", "A helpful Dwarf", "key", "a sweet key");

        player.setCurrentRoom(Entrance);  // start game outside
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
        System.out.println("Thank you for playing.  Goodbye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to A Theifs Trail");
        System.out.println("A Theifs Trail, is game where the theif must steal the nobles crown");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
        System.out.println(player.getInventoryString());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("drop"))
            dropItem(command);
        else if (commandWord.equals("take"))
            takeItem(command);
        else if (commandWord.equals("trade"))
            tradeItem(command);
        else if (commandWord.equals("examine"))
            examineItem(command);
        else if (commandWord.equals("lock"))
            lockDoor(command);
        else if (commandWord.equals("unlock"))
            unlockDoor(command);
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    // implementations of user commands:
    
    
    /** 
     * "Lock" was entered. Locks the specified door, if a key's in
     * the player's inventory, otherwise throws an error.
     */
    private void lockDoor(Command command)
    {
        if(!command.hasSecondWord())
        {
            System.out.println("Lock what?");
            return;
        }
        
        String desiredDoor = command.getSecondWord();
        
        if (player.checkKey())
        {    
            player.getCurrentRoom().getActualDoor(desiredDoor).lock();
            System.out.println("Door locked");
        }
        else
        {
            System.out.println("You don't have a key!");
        }
    }
    
    /** 
     * "Unlock" was entered. Unlocks the specified door, if a key's in
     * the player's inventory, otherwise throws an error.
     */
    private void unlockDoor(Command command)
    {
        if(!command.hasSecondWord())
        {
            System.out.println("Unlock what?");
            return;
        }
        
        String desiredDoor = command.getSecondWord();
        
        if (player.checkKey())
        {    
            player.getCurrentRoom().getActualDoor(desiredDoor).unlock();
            System.out.println("Door unlocked");
        }
        else
        {
            System.out.println("You don't have a key!");
        }
        
    }


    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are a theif. Your goal is to steal the gold locked in the Treasury Room, and any other things as well");
        System.out.println("It is hidden next to the Artistry Hall behind a locked door.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message. If there's a door, try
     * to go through it. If it's locked, print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord())
        {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        // This is the potential next room, via exit
        Room nextRoom = player.getCurrentRoom().getExit(direction);
        // This is the potential next room, via door
        Room nextDoor = player.getCurrentRoom().getDoor(direction);
        
        if (nextDoor != null)
        {
            // There is a door here, then
            if (player.getCurrentRoom().getLocked(direction) == true)
            {
                System.out.println("The door is locked!");
                return;
            }
            else
            {
                player.setCurrentRoom(nextDoor);
                System.out.println(player.getCurrentRoom().getLongDescription());
                System.out.println(player.getInventoryString());
                return;
            }
        }

        if (nextRoom == null)
            System.out.println("There is no exit!");
        else
            {
                player.setCurrentRoom(nextRoom);
                System.out.println(player.getCurrentRoom().getLongDescription());
                System.out.println(player.getInventoryString());
            }
    }
    
    /** 
     * "Drop" was entered. Drops the specified item if it's in 
     * the player's inventory, otherwise throws an error.
     */
    private void dropItem(Command command)
    {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Drop what?");
            return;
        }
        
        String droppedItem = command.getSecondWord();
        
        // Drop it
        
        Item temp = player.dropInventory(droppedItem);
        if (temp != null)
        {
            
            // Add it to the room's items
            player.getCurrentRoom().setItem(temp.getName(), temp.getDescription());
            
            // Refresh inventory
            System.out.println(player.getCurrentRoom().getLongDescription());
            System.out.println(player.getInventoryString());
        }      
        
    }
    
    /** 
     * "Take" was entered. Takes the specified item if it's in 
     * the room, otherwise throws an error.
     */
    private void takeItem(Command command)
    {
       if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Take what?");
            return;
        }
        
        String desiredItem = command.getSecondWord();
              
        // Remove it from the room's items
        Item temp = player.getCurrentRoom().delItem(desiredItem);
        if (temp != null)
        {     
            // Add it to player's inventory
            player.addInventory(temp.getName(), temp.getDescription());
            
            // Refresh inventory
            System.out.println(player.getCurrentRoom().getLongDescription());
            System.out.println(player.getInventoryString());
        }
    }
    
    /** 
     * "Trade" was entered. Trades the specified item if it's in 
     * the player's inventory, otherwise throws an error.
     * @param A trade method that allows the player to trade items if the player has the item of need
     */
    private void tradeItem(Command command)
    {
       if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Trade what?");
            return;
        }
        
        String tradingItem = command.getSecondWord();
        
        // get NPCs in room
        
        NPC npc = player.getCurrentRoom().getNPC();
                   
        // remove NPC item
        
        Item tempItem = npc.dropInventory();
        
        // add NPC item to player inventory
        
        player.addInventory(tempItem);
        
        // remove player item
        
        Item tempPlayerItem = player.dropInventory(tradingItem);
        
        if (tempPlayerItem == null)
        {
            npc.addInventory(tempItem);
            return;
        }
        
        // add player item to NPC inventory
        
        npc.addInventory(tempPlayerItem);
        
        // print out inventory
        
        System.out.println(player.getInventoryString());
    }
    
    
    /** 
     * "Examine" was entered. This prints out the name and description
     * of the specified item, so that everyone can see how clever I've
     * been with the descriptions.
     */
    private void examineItem(Command command)
    {
       if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Examine what?");
            return;
        }
            
        System.out.println(player.getExamineString(command.getSecondWord()));
        return;
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game. Return true, if this command
     * quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else
            return true;  // signal that we want to quit
    }
    
   
}
     

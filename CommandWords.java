import java.util.HashMap;

/**
 * This class is part of the "A Theifs Trail" application. 
 * "A Theifs Trail" is a very simple, text based adventure game.
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Alexei Cichon
 * @date 3/27/2024
 */

public class CommandWords
{
    private static final String[] validCommands = {
        "go", "quit", "help", "take", "drop", "trade", "examine", "lock", "unlock"
    };

    /**
     * Constructor - it will initialise the command words.
     */
    public CommandWords()
    {
        // reminder: put nothing here 
    }

    /**
     * Will check if the given String is a valid command word. 
     * Return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if arrives here, the string did not detect a valid command
        return false;
    }

    /**
     * It will print all the valid commands to System.out.
     */
    public void showAll() 
    {
        for(int i = 0; i < validCommands.length; i++) {
            System.out.print(validCommands[i] + "  ");
        }
        System.out.println();
    }
}

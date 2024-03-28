import java.util.Scanner;

/**
 * This class is part of the "A theifs trail" application. 
 * "A theifs trail" is a very simple, text based adventure game.  
 *
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Alexei Cichon
 * @version 03/27/2024
 */
public class Parser 
{
    private CommandWords commands;  // holds all valid command words
    private Scanner reader;         // returns user input.

    /**
     * Create a Parser for reading user input.
     */
    public Parser() 
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * Parse the next user command.
     * @return The user's command.
     */
    public Command getCommand() 
    {
        String inputLine = "";   // will hold the full input line
        String word1;
        String word2;

        System.out.print("> ");     // print prompt

        String line = reader.nextLine();
        Scanner scan = new Scanner(line);
        
        if(scan.hasNext())
            word1 = scan.next();      // will get first word
        else
            word1 = null;
        if(scan.hasNext())
            word2 = scan.next();      // will get second word
        else
            word2 = null;

        return new Command(word1, word2);
    }

    /**
     * Print out a list of valid command words.
     * @param will print out a list of valid commands for the user to use
     */
    public void showCommands()
    {
        commands.showAll();
    }
}
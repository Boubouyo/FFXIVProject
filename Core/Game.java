package Core;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author fetiveau
 */
public class Game {

	private static final String END_OF_THE_LINE_CHAR = ";";
	
	private final List<Location> locations;
	private Hero hero;
	
	private Game() throws FileNotFoundException, InitiateExitWrongException
	{
		this.locations = InitiateFromFiles.initiateLocations();
		
		try
    	{
			InitiateFromFiles.initiateExits(locations);
		}
		catch(InitiateExitWrongException err)
    	{
			System.out.println(err.getMessage());
		}
		
    	
		this.hero = initiateHero();
	}
	
	private Hero initiateHero()
	{
		String HeroName = "Ardbert";
        int heroHP = 15;
        int heroAttack = 6;
        return new Hero(HeroName, heroHP, heroAttack, locations.get(0));
	}
	
	public String[] parsedInput(Scanner scanner)
	{
		String[] commandAndArgs = {"", "", ""};
		
		int i = 0;
		boolean isLineFinished = false;
		while (!isLineFinished)
		{
			// We get the next word
			String currentWord = scanner.next();
			
			// We're splitting the word if there is the END_OF_THE_LINE_CHAR
			String[] splitedString = currentWord.split(END_OF_THE_LINE_CHAR);
			
			// If there is enough room in commandAndArgs AND splitedString is not 0 (which happen if currentWord = END_OF_THE_LINE_CHAR)
			// We add the word to the commandAndArgs array
			if (i < commandAndArgs.length && splitedString.length != 0)
				commandAndArgs[i] = splitedString[0];			
			
			// We verify if there was the END_OF_THE_LINE_CHAR 
			// If splitedString.length == 0 then the END_OF_THE_LINE_CHAR was there alone
			// If !currentWord.equals(splitedString[0]) then the END_OF_THE_LINE_CHAR was somewhere in the word
			isLineFinished = (splitedString.length == 0 || !currentWord.equals(splitedString[0]));		
			i++;
		}

		return commandAndArgs;
	}
	
	public void gameLoop() throws Exception
	{
		boolean isFinished = false;
		Scanner scannerInput = new Scanner(System.in);
        
		System.out.println("A new adventure begins.");
		
        while (!isFinished)
        {
        	System.out.println("Please input a command : ");
        	String[] commandAndArgs = parsedInput(scannerInput); 
        	
        	try
        	{
        		hero.doTheCommand(commandAndArgs);
			}
			catch(IncorrectCommandException err)
        	{
				System.out.println("This command doesn't exist.");
			}
        	
        	isFinished = hero.getIsGameFinished();
        }
        
        System.out.println("The game ends there.");
        
        scannerInput.close();
	}
	
	public static void gameStart() throws Exception
	{
		Game game = new Game();
		
		game.gameLoop();
	}

    public static void main(String[] args) throws Exception
    {   	
    	Game.gameStart();
    }
    
}

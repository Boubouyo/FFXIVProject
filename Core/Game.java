package Core;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import characters.*;
import locations.*;

/**
 *
 * @author fetiveau
 */
public class Game {

	private static final String END_OF_THE_LINE_CHAR = ";";
	
	private final List<Location> locations;
	private Hero hero;
	
	private Game() throws FileNotFoundException, InitiateFromFilesWrongException
	{
		// LOCATIONS
		this.locations = InitiateFromFiles.initiateLocations();
		
		// EXITS
		try
    	{
			InitiateFromFiles.initiateExits(locations);
		}
		catch(InitiateFromFilesWrongException err)
    	{
			System.out.println("For EXITS : " + err.getMessage());
		}
		
		// ENEMIES
				try
		    	{
					InitiateFromFiles.initiateEnemies(locations);
				}
				catch(InitiateFromFilesWrongException err)
		    	{
					System.out.println("For ENEMIES : " + err.getMessage());
				}
		
    	// HERO
		this.hero = initiateHero();
	}
	
	private Hero initiateHero()
	{
		String HeroName = "Ardbert";
        int heroHP = 30;
        int heroAttack = 6;
        return new Hero(HeroName, heroHP, heroAttack, locations.get(0));
	}
	
	public List<String> parsedInput(Scanner scanner)
	{
		List<String> commandAndArgs = new ArrayList<>();

		boolean isLineFinished = false;
		while (!isLineFinished)
		{
			// We get the next word
			String currentWord = scanner.next();
			
			// We're splitting the word if there is the END_OF_THE_LINE_CHAR
			String[] splitedString = currentWord.split(END_OF_THE_LINE_CHAR);
			
			// If there is enough room in commandAndArgs AND splitedString is not 0 (which happen if currentWord = END_OF_THE_LINE_CHAR)
			// We add the word to the commandAndArgs array
			if (splitedString.length != 0)
				commandAndArgs.add(splitedString[0]);			
			
			// We verify if there was the END_OF_THE_LINE_CHAR 
			// If splitedString.length == 0 then the END_OF_THE_LINE_CHAR was there alone
			// If !currentWord.equals(splitedString[0]) then the END_OF_THE_LINE_CHAR was somewhere in the word
			isLineFinished = (splitedString.length == 0 || !currentWord.equals(splitedString[0]));
		}
			
		return commandAndArgs;
	}
	
	public void gameLoop() throws Exception
	{
		boolean isFinished = false;
		Scanner scannerInput = new Scanner(System.in);
        
		System.out.println("A new adventure begins.");
		
		hero.currentLocation.setHero(hero);
		
        while (!isFinished)
        {        		
        	System.out.print(">> ");
        	List<String> commandAndArgs = parsedInput(scannerInput); 
        	
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

package Core;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import characters.Hero;
import items.Weapon;
import locations.Location;

/**
 * The class that monitors the whole game : it initiates everything and has the main game loop.
 * @author Victor
 */
public class Game {
	// ---------------------------ATTRIBUTS------------------------------------//
	private static final String HERO_NAME = "Hero";
	private static final int HERO_HP = 20;
	private static final int HERO_ATTACK = 1;
	private static final int HERO_INDEX_FIRSTLOCATION = 0;
	private static final Weapon HERO_WEAPON = new Weapon("ardbertsAxe", 5, 9999, "The battleaxe of a future hero... unless ?", null, true);
    
	private static final String END_OF_THE_LINE_CHAR = ";";
	
	private final List<Location> locations;
	private final Hero hero;
	
	
	// --------------------------CONSTRUCTEUR-------------------------------//
	/**
     * Constructor method for the class Game : instantiate everything to set up the game
     * 
     * @exception FileNotFoundException catch if the file was not found 
     * @exception InitiateFromFilesWrongException catch if datas from the files are incorrect
     */
	private Game() throws FileNotFoundException, InitiateFromFilesWrongException 
	{
		
		//  LOCATIONS 
		this.locations = InitiateFromFiles.initiateLocations();
		
		// EXITS 
		try {
			InitiateFromFiles.initiateExits(locations);
		}
		catch(InitiateFromFilesWrongException err) {
			System.out.println("For EXITS : " + err.getMessage());
		}
		
		// ENEMIES
		try {
			InitiateFromFiles.initiateEnemies(locations);
		}
		catch(InitiateFromFilesWrongException err){
			System.out.println("For ENEMIES : " + err.getMessage());
		}
				
		// ITEMS
		try {
			InitiateFromFiles.initiateItems(locations);
		}
		catch(InitiateFromFilesWrongException err){
			System.out.println("For ITEMS : " + err.getMessage());
		}
		
    	// HERO 
		this.hero = new Hero(HERO_NAME, HERO_HP, HERO_ATTACK, this.locations.get(HERO_INDEX_FIRSTLOCATION), HERO_WEAPON);
		this.hero.getWeapon().setHero(this.hero);
	}
	
	
	// ---------------------------OPERATIONS-----------------------------------//
	/**
     * Parse an input into a list of strings
     * @param scanner (Scanner) : to have access to the input
     * @return the list of strings parsed
     */
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
	
	// Main loop of the game
	/**
     * The main loop of the game from were everything happens
     * @exception Exception catch incorrect commands 
     */
	public void gameLoop() throws Exception
	{
		boolean isFinished = false;
		Scanner scannerInput = new Scanner(System.in);
        
		System.out.println("A new adventure begins.");
		
		hero.getCurrentLocation().setHero(hero);
		
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
        
        System.out.println("Thank you for playing !");
        
        scannerInput.close();
	}
	
	/**
     * Create a new game and launch the gameLoop
     * @see gameLoop() 
     * @throws Exception : IncorrectCommandException and InitiateFromFilesWrongException
     */
	public static void gameStart() throws Exception
	{
		Game game = new Game();
		
		game.gameLoop();
	}
    
}

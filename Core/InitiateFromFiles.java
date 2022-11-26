package Core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import locations.Exit;
import locations.Location;

public class InitiateFromFiles {
	
	private static final String CHAR_DELIMITER = ";";
	
	private static final String PATH_LOCATIONS = "src/Core/Locations/";
	private static final File FILE_LOCATIONS = new File ("src/Core/Locations/LOCATIONS");
	
	private static final File FILE_EXITS = new File ("src/Core/Exits/EXITS");
	
	private static final String PATH_ENEMIES = "src/Core/Enemies/";
	
	private static final String PATH_ITEMS = "src/Core/Items/";	
	private static final String PATH_STATUETTE = "Statuettes/";

	// For below
	private static Location stringToLocation(String locationString, List<Location> locations, Scanner scanner) throws InitiateFromFilesWrongException
	{
		Location location = null;
		
		for(Location loc : locations){
            if(loc.getName().equalsIgnoreCase(locationString))
            	location = loc;
        }
		
		// If the name hasn't been associated with the location then it's still null and there is an error
		if (location == null)
		{
			scanner.close();
			throw new InitiateFromFilesWrongException("The name " + locationString + " doesn't correspond to any location.");
		}
		
		return location;
	}	
	
	// --------------------------- LOCATIONS  ------------------------------
	public static List<Location> initiateLocations() throws FileNotFoundException {
		List<Location> locations = new ArrayList<>();
		
		// Get the list of all the locations' name		
		Scanner scannerNames = new Scanner(FILE_LOCATIONS);
		List<String> locationNames = new ArrayList<>();
		
		while (scannerNames.hasNext())
			locationNames.add(scannerNames.nextLine());
		
		scannerNames.close();
		
		// Create each locations
		for (String loc : locationNames) {
			Scanner scanner = new Scanner(new File(PATH_LOCATIONS + loc));
			
			String locationName = scanner.nextLine();
			String locationDescription = scanner.nextLine();
			String locationStartingDescription = scanner.nextLine();
			
			locations.add(new Location(locationName, locationDescription, locationStartingDescription));
			
			scanner.close();
		}
		
		return locations;
	}
	
	// --------------------------- EXITS ------------------------------
	public static void initiateExits(List<Location> locations) throws FileNotFoundException, InitiateFromFilesWrongException
	{
		Scanner scanner = new Scanner(FILE_EXITS);
		
		while (scanner.hasNext())
		{
			String currentExit = scanner.nextLine();
			String[] parsedExit = currentExit.split(CHAR_DELIMITER);
			
			// If the number of arguments is not 3 then it's incorrect !
			if (parsedExit.length != 3)
			{
				scanner.close();
				throw new InitiateFromFilesWrongException("The number of argument is wrong. It must be 3 not " + parsedExit.length);
			}
			else
			{
				String firstLocation = parsedExit[1];
				String secondLocation = parsedExit[2];
				
				// We must associate each name with a location
				Location locA, locB;
				try {
					locA = stringToLocation(firstLocation, locations, scanner);
				} catch (InitiateFromFilesWrongException e) {
					throw new InitiateFromFilesWrongException(e.getMessage());
				}
				try {
					locB = stringToLocation(secondLocation, locations, scanner);
				} catch (InitiateFromFilesWrongException e) {
					throw new InitiateFromFilesWrongException(e.getMessage());
				}
				
				// We create our exit
				new Exit(locA, locB);				
			}
		}
		
		scanner.close();
	}
	
	// --------------------------- ENEMIES ------------------------------
	public static void initiateEnemies(List<Location> locations) throws FileNotFoundException, InitiateFromFilesWrongException
	{
		File fileEnemies = new File(PATH_ENEMIES + "ENEMIES");
		Scanner scanner = new Scanner(fileEnemies);
		
		while (scanner.hasNext())
		{
			String currentEnemy = scanner.nextLine();
			String[] parsedEnemy = currentEnemy.split(CHAR_DELIMITER);
			
			// If the number of arguments is not 3 then it's incorrect !
			if (parsedEnemy.length != 3)
			{
				scanner.close();
				throw new InitiateFromFilesWrongException("The number of argument is wrong. It must be 3 not " + parsedEnemy.length);
			}
			else
			{
				String enemyName = parsedEnemy[0];
				File enemyFile = new File(PATH_ENEMIES + parsedEnemy[1]);
				String locationString = parsedEnemy[2];
				
				
				// We get the data from the enemy base file
				Scanner scannerBase = new Scanner(enemyFile);

				int enemyHealthPoints = Integer.parseInt(scannerBase.nextLine());
				int enemyAttack = Integer.parseInt(scannerBase.nextLine());
				String enemyDescription = scannerBase.nextLine();
				
				scannerBase.close();
				
				// We must associate the name with the location
				Location enemyLocation; 
				try {
					enemyLocation = stringToLocation(locationString, locations, scanner);
				} catch (InitiateFromFilesWrongException e) {
					throw new InitiateFromFilesWrongException(e.getMessage());
				}
				
				enemyLocation.addEnemy(enemyName, enemyHealthPoints, enemyAttack, enemyDescription);
			}
		}
		
		scanner.close();
	}

	// --------------------------- STATUETTES ------------------------------
	public static void initiateStatuettes(List<Location> locations) throws FileNotFoundException, InitiateFromFilesWrongException
	{
		File fileStatuettes = new File (PATH_ITEMS + PATH_STATUETTE + "STATUETTES");
		Scanner scanner = new Scanner(fileStatuettes);
		
		while (scanner.hasNext())
		{
			String currentStatuette = scanner.nextLine();
			String[] parsedStatuette = currentStatuette.split(CHAR_DELIMITER);
			
			// If the number of arguments is not 3 then it's incorrect !
			if (parsedStatuette.length != 1)
			{
				scanner.close();
				throw new InitiateFromFilesWrongException("The number of argument is wrong. It must be 1 not " + parsedStatuette.length);
			}
			else
			{
				File statuetteFile = new File(PATH_ITEMS + PATH_STATUETTE + parsedStatuette[0]);
				
				// We get the data from the enemy base file
				Scanner scannerBase = new Scanner(statuetteFile);
				
				String statuetteName = scannerBase.nextLine();
				int statuetteId = Integer.parseInt(scannerBase.nextLine());
				String statuetteDescription = scannerBase.nextLine();
				String statuetteLocationString = scannerBase.nextLine();
				boolean statuetteIsPickable = scannerBase.nextLine().equalsIgnoreCase("true");
				scannerBase.close();
				
				// We must associate the name with the location
				Location statuetteLocation = null;
				
				try {
					statuetteLocation = stringToLocation(statuetteLocationString, locations, scanner);
				} catch (InitiateFromFilesWrongException e) {
					throw new InitiateFromFilesWrongException(" for STATUETTES : " + e.getMessage());
				}
				
				statuetteLocation.addStatuette(statuetteName, statuetteId, statuetteDescription, statuetteIsPickable);
			}
		}
		
		scanner.close();
	}	
	
	// --------------------------- ITEMS ------------------------------
	public static void initiateItems(List<Location> locations) throws FileNotFoundException, InitiateFromFilesWrongException
	{
		initiateStatuettes(locations);
	}
}

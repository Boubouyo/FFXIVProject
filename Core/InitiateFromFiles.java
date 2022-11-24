package Core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InitiateFromFiles {
	
	private static final String CHAR_DELIMITER = ";";
	
	private static final String PATH_LOCATIONS = "src/Core/Locations/";
	private static final String[] STRING_LOCATIONS = {"entrance", "firstRoom"};
	
	private static final File FILE_EXITS = new File ("src/Core/Exits/EXITS");
	
	private static final String PATH_ENEMIES = "src/Core/Enemies/";
	private static final File FILE_ENEMIES = new File ("src/Core/Enemies/ENEMIES");

	public static List<Location> initiateLocations() throws FileNotFoundException
	{
		List<Location> locations = new ArrayList<>();
		
		for (String loc : STRING_LOCATIONS) 
		{
			Scanner scanner = new Scanner(new File(PATH_LOCATIONS + loc));
			
			String locationName = scanner.nextLine();
			String locationDescription = scanner.nextLine();
			String locationStartingDescription = scanner.nextLine();
			
			locations.add(new Location(locationName, locationDescription, locationStartingDescription));
			
			scanner.close();
		}
		
		return locations;
	}
	
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
				String secondLocation = parsedExit[1];
				
				// We must associate each name with a location
				Location locA = null, locB = null;
				
				for(Location loc : locations){
		            if(loc.getName().equals(firstLocation)){
		            	locA = loc;
		            }
		            if(loc.getName().equals(secondLocation)){
		            	locB = loc;
		            }
		        }
				
				// If a name hasn't been associated with a location then it's still null and ther is an error
				if (locA != null && locB != null)
				{
					new Exit(locA, locB);
				}
				else
				{
					scanner.close();
					throw new InitiateFromFilesWrongException("The names" + firstLocation + " and/or " + secondLocation + "doesn't correspond to any location.");
				}
			}
		}
		
		scanner.close();
	}
	
	public static void initiateEnemies(List<Location> locations) throws FileNotFoundException, InitiateFromFilesWrongException
	{
		Scanner scanner = new Scanner(FILE_ENEMIES);
		
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
				
				// We must associate the name with the location
				Location location = null;
				
				for(Location loc : locations){
		            if(loc.getName().equals(locationString))
		            		location = loc;
		        }
				
				// If the name hasn't been associated with the location then it's still null and there is an error
				if (location == null)
				{
					scanner.close();
					throw new InitiateFromFilesWrongException("The name" + locationString + "doesn't correspond to any location.");
				}
				
				// TODO add enemy to location
			}
		}
		
		scanner.close();
	}
}

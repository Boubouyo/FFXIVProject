package Core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InitiateFromFiles {
	
	private static final String PATH_LOCATIONS = "src/Core/Locations/";
	private static final String[] STRING_LOCATIONS = {"entrance", "firstRoom"};
	
	private static final File FILE_EXITS = new File ("src/Core/Exits/EXITS");
	private static final String CHAR_DELIMITER_EXIT = ";";

	public static List<Location> initiateLocations() throws FileNotFoundException
	{
		List<Location> locations = new ArrayList<>();
		
		for (String loc : STRING_LOCATIONS) 
		{
			Scanner scanner = new Scanner(new File(PATH_LOCATIONS + loc));
			
			String locationName = scanner.nextLine();
			String locationDescription = scanner.nextLine();
			
			locations.add(new Location(locationName, locationDescription));
			
			scanner.close();
		}
		
		return locations;
	}
	
	public static void initiateExits(List<Location> locations) throws FileNotFoundException, InitiateExitWrongException
	{
		Scanner scanner = new Scanner(FILE_EXITS);
		
		while (scanner.hasNext())
		{
			String currentExit = scanner.nextLine();
			String[] parsedExit = currentExit.split(CHAR_DELIMITER_EXIT);
			
			// If the number of arguments is not 3 then it's incorrect !
			if (parsedExit.length != 3)
			{
				scanner.close();
				throw new InitiateExitWrongException("The number of argument is wrong. It must be 3 not " + parsedExit.length);
			}
			else
			{
				// We must associate each name with a location
				Location locA = null, locB = null;
				
				for(Location loc : locations){
		            if(loc.getName().equals(parsedExit[1])){
		            	locA = loc;
		            }
		            if(loc.getName().equals(parsedExit[2])){
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
					throw new InitiateExitWrongException("The names" + parsedExit[1] + " and/or " + parsedExit[2] + "doesn't correspond to any location.");
				}
			}
		}
		
		scanner.close();
	}
}

package Core;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import locations.Exit;
import locations.ExitEnemy;
import locations.ExitItem;
import locations.ExitPillar;
import locations.Location;

public class InitiateFromFiles {
	
	private static final String CHAR_DELIMITER = ";";
	
	private static final String PATH_LOCATIONS_FOLDER = "Locations/";
	private static final String PATH_LOCATIONS = "LOCATIONS";

	private static final String PATH_EXITS = "Exits/EXITS";
	
	private static final String PATH_ENEMIES = "Enemies/";
	
	private static final String PATH_ITEMS = "Items/";	
	private static final String PATH_STATUETTE = "Statuettes/";
	private static final String PATH_ENIGMADEVICES = "EnigmaDevices/";
	private static final String PATH_HEALINGITEMS = "HealingItems/";
	private static final String PATH_WEAPONS = "Weapons/";
	private static final String PATH_PILLARS = "Pillars/";
	
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
		InputStream filePath = InitiateFromFiles.class.getResourceAsStream(PATH_LOCATIONS_FOLDER + PATH_LOCATIONS);
		System.out.println(filePath);
		//File fileLocations = new File(filePath);
		Scanner scannerNames = new Scanner(filePath);
		List<String> locationNames = new ArrayList<>();
		
		while (scannerNames.hasNext())
			locationNames.add(scannerNames.nextLine());
		
		scannerNames.close();
		
		// Create each locations
		for (String loc : locationNames) {
			InputStream locPath = InitiateFromFiles.class.getResourceAsStream(PATH_LOCATIONS_FOLDER + loc);
			Scanner scanner = new Scanner(locPath);
			
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
		InputStream filePath = InitiateFromFiles.class.getResourceAsStream(PATH_EXITS);
		//File fileExits = new File(filePath);
		Scanner scanner = new Scanner(filePath);
		
		while (scanner.hasNext())
		{
			String currentExit = scanner.nextLine();
			String[] parsedExit = currentExit.split(CHAR_DELIMITER);
			
			// If the number of arguments is not 5 or 6 then it's incorrect !
			if (parsedExit.length != 5 && parsedExit.length != 6)
			{
				scanner.close();
				throw new InitiateFromFilesWrongException("The number of argument is wrong. It must be 5 or 6 not " + parsedExit.length);
			}
			else
			{
				String exitType = parsedExit[0];
				String firstLocation = parsedExit[1];
				String secondLocation = parsedExit[2];
				boolean fromAtoB = parsedExit[3].equals("true");
				boolean fromBtoA = parsedExit[4].equals("true");
				
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
				if (exitType.equalsIgnoreCase("exit"))
					new Exit(locA, locB, fromAtoB, fromBtoA);
				
				else if (exitType.equalsIgnoreCase("exitItem"))
					new ExitItem(locA, locB, fromAtoB, fromBtoA, parsedExit[5]);
				
				else if (exitType.equalsIgnoreCase("exitEnemy"))
					new ExitEnemy(locA, locB, fromAtoB, fromBtoA);
				
				else if (exitType.equalsIgnoreCase("exitPillar"))
					new ExitPillar(locA, locB);				
				else
					throw new InitiateFromFilesWrongException("The exitType " + exitType + "doesn't exist.");
			}
		}
		
		scanner.close();
	}
	
	// --------------------------- ENEMIES ------------------------------
	public static void initiateEnemies(List<Location> locations) throws FileNotFoundException, InitiateFromFilesWrongException
	{
		InputStream filePath = InitiateFromFiles.class.getResourceAsStream(PATH_ENEMIES + "ENEMIES");
		//File fileEnemies = new File(filePath);
		Scanner scanner = new Scanner(filePath);
		
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
				InputStream enemyPath = InitiateFromFiles.class.getResourceAsStream(PATH_ENEMIES + parsedEnemy[1]);
				//File enemyFile = new File(enemyPath);
				String locationString = parsedEnemy[2];
				
				
				// We get the data from the enemy base file
				Scanner scannerBase = new Scanner(enemyPath);

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
		InputStream filePath = InitiateFromFiles.class.getResourceAsStream(PATH_ITEMS + PATH_STATUETTE + "STATUETTES");
		//File fileStatuettes = new File (filePath);
		Scanner scanner = new Scanner(filePath);
		
		while (scanner.hasNext())
		{
			String currentStatuette = scanner.nextLine();
			String[] parsedStatuette = currentStatuette.split(CHAR_DELIMITER);
			
			// If the number of arguments is not 1 then it's incorrect !
			if (parsedStatuette.length != 1)
			{
				scanner.close();
				throw new InitiateFromFilesWrongException("The number of argument is wrong. It must be 1 not " + parsedStatuette.length);
			}
			else
			{
				InputStream statuettePath = InitiateFromFiles.class.getResourceAsStream(PATH_ITEMS + PATH_STATUETTE + parsedStatuette[0]);
				//File statuetteFile = new File(statuettePath);
				
				// We get the data from the statuette base file
				Scanner scannerBase = new Scanner(statuettePath);
				
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
	
	// --------------------------- ENIGMADEVICES ------------------------------
	public static void initiateEnigmaDevices(List<Location> locations) throws FileNotFoundException, InitiateFromFilesWrongException
	{
		InputStream filePath = InitiateFromFiles.class.getResourceAsStream(PATH_ITEMS + PATH_ENIGMADEVICES + "ENIGMADEVICES");
		//File fileEnigmaDevices = new File (filePath);
		Scanner scanner = new Scanner(filePath);
		
		while (scanner.hasNext())
		{
			String currentEnigmaDevice = scanner.nextLine();
			String[] parsedEnigmaDevices = currentEnigmaDevice.split(CHAR_DELIMITER);
			
			// If the number of arguments is not 2 then it's incorrect !
			if (parsedEnigmaDevices.length != 2)
			{
				scanner.close();
				throw new InitiateFromFilesWrongException("The number of argument is wrong. It must be 2 not " + parsedEnigmaDevices.length);
			}
			else
			{
				InputStream enigmaDevicePath = InitiateFromFiles.class.getResourceAsStream(PATH_ITEMS + PATH_ENIGMADEVICES + parsedEnigmaDevices[0]);
				//File enigmaDeviceFile = new File(enigmaDevicePath);
				String locationString = parsedEnigmaDevices[1];
				
				// We get the data from the enigmaDevice base file
				Scanner scannerBase = new Scanner(enigmaDevicePath);
				
				String name = scannerBase.nextLine();
				String description = scannerBase.nextLine();
				String descriptionResolved = scannerBase.nextLine(); 
				String descriptionAfterResolved = scannerBase.nextLine();
				String itemToGive = scannerBase.nextLine();
				String[] correctSequenceString = scannerBase.nextLine().split(CHAR_DELIMITER);
				int howManyButtons = Integer.parseInt(scannerBase.nextLine());
				String[] buttonsName = new String[howManyButtons]; 
				String[] buttonsDescription = new String[howManyButtons];
				String[] buttonsDescriptionResolved = new String[howManyButtons];
				
				for (int i = 0; i < howManyButtons; i++)
				{
					buttonsName[i] = scannerBase.nextLine(); 
					buttonsDescription[i] = scannerBase.nextLine();
					buttonsDescriptionResolved[i] = scannerBase.nextLine();
				}
				
				scannerBase.close();
				
				// Convert String[] to int[]
				int[] correctSequence = new int[correctSequenceString.length];
				for (int i = 0; i < correctSequenceString.length; i++)
					correctSequence[i] = Integer.parseInt(correctSequenceString[i]);
				
				// We must associate the name with the location
				Location location = null;
				
				try {
					location = stringToLocation(locationString, locations, scanner);
				} catch (InitiateFromFilesWrongException e) {
					throw new InitiateFromFilesWrongException(" for ENIGMADEVICES : " + e.getMessage());
				}
				
				location.addEnigmaDevice(name, description, descriptionResolved, descriptionAfterResolved, buttonsName, buttonsDescription, buttonsDescriptionResolved, itemToGive, correctSequence);
			}
		}
		
		scanner.close();
	}
	
	// --------------------------- HEALINGITEMS ------------------------------
	public static void initiateHealingItems(List<Location> locations) throws FileNotFoundException, InitiateFromFilesWrongException
	{
		InputStream filePath = InitiateFromFiles.class.getResourceAsStream(PATH_ITEMS + PATH_HEALINGITEMS + "HEALINGITEMS");
		//File fileHealingItems = new File (filePath);
		Scanner scanner = new Scanner(filePath);
		
		while (scanner.hasNext())
		{
			String currentHealingItem = scanner.nextLine();
			String[] parsedHealingItem = currentHealingItem.split(CHAR_DELIMITER);
			
			// If the number of arguments is not 3 then it's incorrect !
			if (parsedHealingItem.length != 3)
			{
				scanner.close();
				throw new InitiateFromFilesWrongException("The number of argument is wrong. It must be 3 not " + parsedHealingItem.length);
			}
			else
			{
				InputStream healingItemPath = InitiateFromFiles.class.getResourceAsStream(PATH_ITEMS + PATH_HEALINGITEMS + parsedHealingItem[0]);
				//File healingItemFile = new File(healingItemPath);
				String locationString = parsedHealingItem[1];
				boolean isPickable = parsedHealingItem[2].equalsIgnoreCase("true");
				
				// We get the data from the healingItems base file
				Scanner scannerBase = new Scanner(healingItemPath);
				
				String name = scannerBase.nextLine();
				String description = scannerBase.nextLine();
				int healingPower = Integer.parseInt(scannerBase.nextLine());
				
				scannerBase.close();
				
				// We must associate the name with the location
				Location location = null;
				
				try {
					location = stringToLocation(locationString, locations, scanner);
				} catch (InitiateFromFilesWrongException e) {
					throw new InitiateFromFilesWrongException(" for HEALINGITEMS : " + e.getMessage());
				}
				
				location.addHealingItem(name, description, isPickable, healingPower);
				}
		}
		
		scanner.close();
	}
	
	// --------------------------- WEAPONS ------------------------------
	public static void initiateWeapons(List<Location> locations) throws FileNotFoundException, InitiateFromFilesWrongException
	{
		InputStream filePath = InitiateFromFiles.class.getResourceAsStream(PATH_ITEMS + PATH_WEAPONS + "WEAPONS");
		//File fileWeapons = new File (filePath);
		Scanner scanner = new Scanner(filePath);
		
		while (scanner.hasNext())
		{
			String currentWeapon = scanner.nextLine();
			String[] parsedWeapon = currentWeapon.split(CHAR_DELIMITER);
			
			// If the number of arguments is not 3 then it's incorrect !
			if (parsedWeapon.length != 3)
			{
				scanner.close();
				throw new InitiateFromFilesWrongException("The number of argument is wrong. It must be 3 not " + parsedWeapon.length);
			}
			else
			{
				InputStream weaponPath = InitiateFromFiles.class.getResourceAsStream(PATH_ITEMS + PATH_WEAPONS + parsedWeapon[0]);
				//File weaponFile = new File(weaponPath);
				String locationString = parsedWeapon[1];
				boolean isPickable = parsedWeapon[2].equalsIgnoreCase("true");
				
				// We get the data from the weapons base file
				Scanner scannerBase = new Scanner(weaponPath);
				
				String name = scannerBase.nextLine();
				String description = scannerBase.nextLine();
				int attack = Integer.parseInt(scannerBase.nextLine());
				int durability = Integer.parseInt(scannerBase.nextLine());
				
				scannerBase.close();
				
				if (durability < 1)
					throw new InitiateFromFilesWrongException(" for WEAPONS : The durability is too small for weapon " + name + ". It must be > 0.");
				
				// We must associate the name with the location
				Location location = null;
				
				try {
					location = stringToLocation(locationString, locations, scanner);
				} catch (InitiateFromFilesWrongException e) {
					throw new InitiateFromFilesWrongException(" for WEAPONS : " + e.getMessage());
				}
				
				location.addWeapon(name, attack, durability, description, isPickable);
				}
		}
		
		scanner.close();
	}
	
	// --------------------------- PILLARS ------------------------------
		public static void initiatePillars(List<Location> locations) throws FileNotFoundException, InitiateFromFilesWrongException
		{
			InputStream filePath = InitiateFromFiles.class.getResourceAsStream(PATH_ITEMS + PATH_PILLARS + "PILLARS");
			//File filePillars = new File (filePath);
			Scanner scanner = new Scanner(filePath);
			
			while (scanner.hasNext())
			{
				String currentPillar = scanner.nextLine();
				String[] parsedPillar = currentPillar.split(CHAR_DELIMITER);
				
				// If the number of arguments is not 1 then it's incorrect !
				if (parsedPillar.length != 1)
				{
					scanner.close();
					throw new InitiateFromFilesWrongException("The number of argument is wrong. It must be 1 not " + parsedPillar.length);
				}
				else
				{
					InputStream pillarPath = InitiateFromFiles.class.getResourceAsStream(PATH_ITEMS + PATH_PILLARS + parsedPillar[0]);
					//File pillarFile = new File(pillarPath);
					
					// We get the data from the pillar base file
					Scanner scannerBase = new Scanner(pillarPath);
					
					String name = scannerBase.nextLine();
					int id = Integer.parseInt(scannerBase.nextLine());
					String description = scannerBase.nextLine();
					String locationString = scannerBase.nextLine();
					
					scannerBase.close();
					
					// We must associate the name with the location
					Location location = null;
					
					try {
						location = stringToLocation(locationString, locations, scanner);
					} catch (InitiateFromFilesWrongException e) {
						throw new InitiateFromFilesWrongException(" for PILLARS : " + e.getMessage());
					}
					
					location.addPillar(name, id, description);
				}
			}
			
			scanner.close();
		}
	
	// --------------------------- ITEMS ------------------------------
	public static void initiateItems(List<Location> locations) throws FileNotFoundException, InitiateFromFilesWrongException
	{			
		initiateStatuettes(locations);
		initiateEnigmaDevices(locations);
		initiateHealingItems(locations);
		initiateWeapons(locations);
		initiatePillars(locations);
	}
}

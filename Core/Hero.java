package Core;
import java.util.*; 

public class Hero extends Character 
{
	private List<Item> inventory; 
	private Location currentLocation;
	private final static String[] commands = {"HELP", "GO", "LOOK", "TAKE", "USE", "ATTACK", "QUIT"};
	
	private boolean isGameFinished = false;
	
	public Hero (String name, int healthPoints, int attack, Location startingLocation)
	{
		super(name, healthPoints, attack);
		this.inventory = new ArrayList<Item>();
		this.currentLocation = startingLocation;
	}
	
	public void addToInventory(Item item) 
	{
		this.inventory.add(item); 
	}
	
	public void removeFromInventory(Item item) 
	{
		this.inventory.remove(item); 
	}
	
	public Item getItemFromInventory(String itemName)
	{
		for (Item item : this.inventory) {
	        if (item.getName().equals(itemName)) {
	            return item;
	        }
	    }
	    return null;
	}
	
	public boolean getIsGameFinished()
	{
		return this.isGameFinished;
	}
	
	public void die()
	{
		System.out.println("You died lmao. Try again when you have become a man.");
		this.isGameFinished = true;
	}
	
	// Methods for the commands
	public void doTheCommand(String[] commandAndArgs)
	{
		String command = commandAndArgs[0];
		String arg1 = commandAndArgs[1];
		String arg2 = commandAndArgs[2];
		int nbArgs = commandAndArgs.length - 1;
		
		if (command == commands[0]) // HELP
		{
			printHelpCommands();
		}
		else if (command == commands[1]) // GO
		{
			if (nbArgs == 0)
				System.out.println("GO where ?");
			else
				changeLocation(arg1);
		}
		else if (command == commands[2]) // LOOK
		{
			if (nbArgs == 0)
				currentLocation.display();
			else 
				lookSomething(arg1);
		}
		else if (command == commands[3]) // TAKE
		{
			if (nbArgs == 0)
				System.out.println("TAKE what ?");
			else 
				takeItem(arg1);
		}
		else if (command == commands[4]) // USE
		{
			if (nbArgs == 0)
				System.out.println("USE what ?");
			else if (nbArgs == 1)
				useItem(arg1);
			else
				useItemOnItem(arg1, arg2);
		}
		else if (command == commands[5]) // ATTACK
		{
			if (nbArgs == 0)
				System.out.println("ATTACK who ?");
			else if (nbArgs == 1)
				attackEnemy(arg1);
		}
		else if (command == commands[6]) // QUIT
		{
			this.isGameFinished = true;
		}
		else
		{
			// TODO créer une exception
		}
	}
	
	public void changeLocation(String locationName)
	{
		currentLocation = currentLocation.takeExit(locationName);
	}
	
	public void printHelpCommands()
	{
		System.out.print("The available commands are : ");
		int i;
		for (i = 0; i < commands.length - 1; i++) 
		{
			System.out.print(commands[i] + ", ");
		}
		System.out.println(commands[i] + ". If you want to know more read the README.");
	}
	
	public void lookSomething(String somethingName)
	{
		
	}
	
	public void takeItem(String itemName)
	{
		
	}
	
	public void useItem(String itemName)
	{
		
	}
	
	public void useItemOnItem(String item1Name, String item2Name)
	{
		
	}
	
	public void attackEnemy(String enemyName)
	{
		Character enemy = null /* currentRoom.getEnemyByName(enemyName) */;
		
		attackCharacter(enemy);
	}
}

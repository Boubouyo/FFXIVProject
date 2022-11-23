package Core;
import java.util.*; 

public class Hero extends Character 
{
	private List<Item> inventory; 
	private Location currentLocation;
	private final static String[] commands = {"GO", "HELP", "LOOK", "TAKE", "QUIT", "USE"};
	
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
	
	public void die()
	{
		System.out.println("You died lmao. Try again when you have become a man.");
	}
	
	// Methods for the commands 	
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
		System.out.println(commands[i] + ".");
	}
}

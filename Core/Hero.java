package Core;
import java.util.*; 

public class Hero extends Character 
{
	private Map<String, Item> inventory; 
	private Location currentLocation;
	
	public Hero (String name, int healthPoints, int attack, Location startingLocation)
	{
		super(name, healthPoints, attack);
		this.inventory = new HashMap<>();
		this.currentLocation = startingLocation;
	}
	
	public void addToInventory(Item item) 
	{
		this.inventory.put(item.getName(), item); 
	}
	
	public void removeFromInventory(String itemName) 
	{
		this.inventory.remove(itemName); 
	}
	
	public void getItemFromInventory(String itemName)
	{
		this.inventory.get(itemName); 
	}
	
	public void die()
	{
		System.out.println("You died lmao. Try again when you have become a man.");
	}
	
	public void changeLocation(String locationName)
	{
		currentLocation = currentLocation.takeExit(locationName);
	}
}

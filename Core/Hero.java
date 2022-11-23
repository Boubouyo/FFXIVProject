package Core;
import java.util.*; 

public class Hero extends Character 
{
	private Map<String, Item> inventory; 
	
	public Hero (String name, int healthPoints)
	{
		super(name, healthPoints);
		this.inventory = new HashMap<>();
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
}

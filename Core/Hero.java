import java.util.*; 

public class Hero extends Character 
{
	private Map<String, String> inventory; // TODO change String to Item
	
	public Hero (String name, int healthPoints)
	{
		super(name, healthPoints);
		this.inventory = new HashMap<>();
	}
	
	public void addToInventory(String item) // TODO change String to Item
	{
		this.inventory.put(item, item); // TODO change item to item.getName()
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

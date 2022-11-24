package Core;

import java.util.ArrayList;
import java.util.List;

public class Hero extends Character 
{
	private final List<Item> inventory; 
	private final static String[] commands = {"HELP", "GO", "LOOK", "TAKE", "USE", "ATTACK", "QUIT"};
	
	private boolean isGameFinished = false;
	
	public Hero (String name, int healthPoints, int attack, Location startingLocation)
	{
		super(name, healthPoints, attack, startingLocation);
		this.inventory = new ArrayList<>();
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
	
	@Override
	public void die()
	{
		System.out.println("You died lmao. Try again when you have become a man.");
		this.isGameFinished = true;
	} 
	
	public Item getItemByName(String itemName) // Look for an item in both the current room and the inventory
	{
		Item itemWithName = this.currentLocation.getItemFromString(itemName);
		
		if (itemWithName == null)
		{
			itemWithName = getItemFromInventory(itemName);
		}
		
		return itemWithName;
	}
	
	// Methods for the commands
	public void doTheCommand(String[] commandAndArgs)
	{
		String command = commandAndArgs[0];
		String arg1 = commandAndArgs[1];
		String arg2 = commandAndArgs[2];
		int nbArgs = commandAndArgs.length - 1;
		
		if (command.equals(commands[0])) // HELP
		{
			printHelpCommands();
		}
		else if (command.equals(commands[1])) // GO
		{
			if (nbArgs == 0)
				System.out.println("GO where ?");
			else
				changeLocation(arg1);
		}
		else if (command.equals(commands[2])) // LOOK
		{
			if (nbArgs == 0)
				currentLocation.look();
			else 
				lookSomething(arg1);
		}
		else if (command.equals(commands[3])) // TAKE
		{
			if (nbArgs == 0)
				System.out.println("TAKE what ?");
			else 
				takeItem(arg1);
		}
		else if (command.equals(commands[4])) // USE
		{
                    switch (nbArgs) {
                        case 0 -> System.out.println("USE what ?");
                        case 1 -> useItem(arg1);
                        default -> useItemOnItem(arg1, arg2);
                    }
		}
		else if (command.equals(commands[5])) // ATTACK
		{
			if (nbArgs == 0)
				System.out.println("ATTACK whom ?");
			else if (nbArgs == 1)
				attackEnemy(arg1);
		}
		else if (command.equals(commands[6])) // QUIT
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
		this.currentLocation = this.currentLocation.takeExit(locationName, this);
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
		Item itemToLook = getItemByName(somethingName);
		if (itemToLook != null)
		{
			itemToLook.look();
		}
		else 
		{
			Enemy enemyToLook = this.currentLocation.getEnemyByName(somethingName);
			if (enemyToLook != null)
			{
				enemyToLook.look();
			}
			else 
			{
				System.out.println("You're trying really hard to look at " + somethingName + " but you can't manage to find it.");
			}
		}
	}
	
	public void takeItem(String itemName)
	{
		Item itemToTake = getItemByName(itemName);
		if (itemToTake instanceof Take take)
		{
			if (take.take())
			{
				addToInventory(itemToTake);			
			}
			else
			{
				System.out.println("The " + itemName + " cannot be picked for now.");
			}
		}
		else 
		{
			System.out.println("The " + itemName + " cannot be picked.");
		}
	}
	
	public void useItem(String itemName)
	{
		Item itemToUse = getItemByName(itemName);
		if (itemToUse instanceof Use use)
		{
			if (!use.use())
			{
				System.out.println("The " + itemName + " cannot be used for now.");
			}
		}
		else 
		{
			System.out.println("The " + itemName + " cannot be used.");
		}
	}
	
	public void useItemOnItem(String item1Name, String item2Name)
	{
		Item itemToUse = getItemByName(item1Name);
		Item itemToUseOn = getItemByName(item2Name);
		if (itemToUse instanceof UseOn useOn)
		{
			if (!useOn.useOn(itemToUseOn))
			{
				System.out.println("The " + item1Name + " cannot be used on " + item2Name + ".");
			}
		}
		else 
		{
			System.out.println("The " + item1Name + " cannot be used.");
		}
	}
	
	public void attackEnemy(String enemyName)
	{
		Enemy enemy = currentLocation.getEnemyByName(enemyName);
		
		attackCharacter((Character)enemy);
	}
}

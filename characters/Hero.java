package characters;

import locations.*;
import items.*;
import Core.*;
import commands.*;

import java.util.ArrayList;
import java.util.List;

public class Hero extends Character 
{
	
	private final static String[] commands = {"HELP", "GO", "LOOK", "TAKE", "USE", "ATTACK", "QUIT"};
	private final static String[] commands_help = 
		{
			"HELP [command] : use it to have the list of commands. If you add an arg it will describe a command.",
			"GO location : use it to go to the location.", 
			"LOOK [args] : use it to inspect. Without args it will describe the room. You can add as many args as you like to look items, enemies, the HERO or the INVENTORY.", 
			"TAKE item : use it to take the item, if possible.", 
			"USE item1 [item2] : use it to use the item1. Add item2 to use the item1 on the item2.", 
			"ATTACK enemy : use it to attack the enemy. Be careful, they might fight back !", 
			"QUIT : use it to quit the game."
		};
	
	private final List<Item> inventory; 	
	private Weapon weapon;
	
	private boolean isGameFinished = false;
	
	public Hero (String name, int healthPoints, int attack, Location startingLocation) {
		super(name, healthPoints, attack, startingLocation);
		this.inventory = new ArrayList<>();
		this.weapon = null;
	}
	
    // Getters et setters
	public boolean getIsGameFinished()
	{
		return this.isGameFinished;
	}
	
	public Weapon getWeapon()
	{
		return this.weapon;
	}
	
	public void setWeapon(Weapon weapon)
	{
		this.weapon = weapon;
	}
	
	// ----- Inventory management -----
	public void addToInventory(Item item) {
		this.inventory.add(item); 
	}
	
	public void removeFromInventory(Item item) 
	{
		this.inventory.remove(item); 
	}
	
	public Item getItemFromInventory(String itemName)
	{
		for (Item item : this.inventory) {
	        if (item.getName().equalsIgnoreCase(itemName)) {
	            return item;
	        }
	    }
	    return null;
	}
	
	// ----- Prints -----
	public void printInventory()
	{
		if (inventory.isEmpty())
		{
			System.out.println("Your inventory is empty.");
		}
		else
		{
			System.out.print("In your inventory you have :");
			for (Item item : inventory) 
			{
				System.out.print(" " + item.getName());
			}
			System.out.println(".");
		}
	}
	
	public void printHero()
	{
		System.out.print("You have ");
		printHP();
		printInventory();
	}

	// ----- Find items -----
	public Item getItemByName(String itemName) // Look for an item in both the current room and the inventory
	{
		Item itemWithName = this.getCurrentLocation().getItemFromString(itemName);
		
		if (itemWithName == null)
		{
			itemWithName = getItemFromInventory(itemName);
		}
		
		return itemWithName;
	}
	
	// ----- For combat -----
	@Override
	public int getFinalAttackPower()
	{
		int fAtk = super.getFinalAttackPower();
		
		if (weapon != null)
			fAtk += weapon.getBonusAttackPoint();
		
		return fAtk;
	}
	
	@Override
	public void die()
	{
		System.out.println("You died.");
		this.isGameFinished = true;
	}
	
	// ----- Methods for the commands -----
	public void doTheCommand(List<String> commandAndArgs) throws Exception
	{
		String command = commandAndArgs.get(0);
		int len = commandAndArgs.size();
		
		if (command.equalsIgnoreCase(commands[0])) // HELP
		{
			if (len == 1)
				printHelpCommands();
			else
				printHelpCommandsWithArg(commandAndArgs.get(1));
		}
		else if (command.equalsIgnoreCase(commands[1])) // GO
		{
			if (len == 1)
				System.out.println("GO where ?");
			else
				changeLocation(commandAndArgs.get(1));
		}
		else if (command.equalsIgnoreCase(commands[2])) // LOOK
		{
			if (len == 1)
				getCurrentLocation().look();
			else 
				{
					commandAndArgs.remove(0);
					for (String arg : commandAndArgs)
						lookSomething(arg);				
				}
		}
		else if (command.equalsIgnoreCase(commands[3])) // TAKE
		{
			if (len == 1)
				System.out.println("TAKE what ?");
			else 
				takeItem(commandAndArgs.get(1));
		}
		else if (command.equalsIgnoreCase(commands[4])) // USE
		{
                    if (len == 1)
                    	System.out.println("USE what ?");
                    else if (len == 2)
                    	useItem(commandAndArgs.get(1));
                    else 
                    	useItemOnItem(commandAndArgs.get(1), commandAndArgs.get(2));
		}
		else if (command.equalsIgnoreCase(commands[5])) // ATTACK
		{
			if (len == 1)
				System.out.println("ATTACK whom ?");
			else
				attackEnemy(commandAndArgs.get(1));
		}
		else if (command.equalsIgnoreCase(commands[6])) // QUIT
		{
			this.isGameFinished = true;
		}
		else
		{
			throw new IncorrectCommandException();
		}
	}	
	// END doTheCommands --------------------
	
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
	
	public void printHelpCommandsWithArg(String command)
	{
		int i = 0;
		for (String c : commands) 
		{
			if (command.equalsIgnoreCase(c))
			{
				System.out.println(commands_help[i]);
				break;
			}
			i++;
		}
		
		if (i == commands.length)
			System.out.println("The command " + command + " doesn't exist.");
	}
	
	public void changeLocation(String locationName)
	{
		Location previousLocation = getCurrentLocation();
		this.setCurrentLocation(previousLocation.takeExit(locationName, this));
		if (previousLocation == getCurrentLocation())
			System.out.println("You can't go there yet.");
	}
	
	public void lookSomething(String somethingName)
	{
		// SPECIAL EXCEPTION FOR INVENTORY
		if (somethingName.equalsIgnoreCase("INVENTORY"))
		{
			printInventory();
		}
		// SPECIAL EXCEPTION FOR HERO
		else if (somethingName.equalsIgnoreCase("HERO") || somethingName.equalsIgnoreCase("ME")|| somethingName.equalsIgnoreCase("SELF"))
		{
			printHero();
		}
		else
		{
			Item itemToLook = getItemByName(somethingName);
			if (itemToLook != null)
			{
				itemToLook.look();
			}
			else 
			{
				Enemy enemyToLook = this.getCurrentLocation().getEnemyByName(somethingName);
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
	}
	
	public void takeItem(String itemName)
	{
		Item itemToTake = getItemByName(itemName);
		if (itemToTake instanceof Take take)
		{
			if (take.take())
			{
				addToInventory(itemToTake);
				getCurrentLocation().removeItem(itemToTake);
				itemToTake.setHero(this);
				System.out.println("You take the " + itemName + " and put it in your inventory.");		
			}
			else
			{
				System.out.println("The " + itemName + " cannot be picked for now.");
			}
		}
		else 
		{
			System.out.println("The " + itemName + " cannot be picked. Does it even exist ?");
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
			System.out.println("The " + itemName + " cannot be used. Does it even exist ?");
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
			System.out.println("The " + item1Name + " cannot be used. Does it even exist ?");
		}
	}
	
	public void attackEnemy(String enemyName)
	{
		// SPECIAL EXCEPTION FOR SETA
		if (enemyName.equals("Seta"))
		{
			System.out.println("You trully are a monster.");
			setName("Monster");
			return;
		}
		
		Enemy enemy = getCurrentLocation().getEnemyByName(enemyName);
		
		if (enemy != null)
		{
			attackCharacter((Character)enemy);
		
			// COUNTER ATTACK
			getCurrentLocation().allEnemiesAttack();
		}
		else
			System.out.println("This enemy doesn't exist.");
	}

}

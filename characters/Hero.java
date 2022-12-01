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
			"LOOK [args] : use it to inspect. Without args it will describe the room. You can add as many args as you like to look items, enemies, the HERO, the STATS, the WEAPON or the INVENTORY.", 
			"TAKE item : use it to take the item, if possible.", 
			"USE item1 [item2] : use it to use the item1. It can be used to eat, equip, and so much more ! Add item2 to use the item1 on the item2.", 
			"ATTACK enemy : use it to attack the enemy. Be careful, they might fight back !", 
			"QUIT : use it to quit the game."
		};
	
	private final List<Item> inventory; 	
	private Weapon weapon;
	
	private boolean isGameFinished = false;
	
	/**
     * Constructor method for the class Hero.
     * @param name (String) : the character name
     * @param healthPoints (int) : the number of health points for the character
     * @param attack (int) : the value of the character attack
     * @param startingLocation (Location) : the first location of the character
     * @param startingWeapon (Weapon) : the first weapon of the character
     */
	public Hero (String name, int healthPoints, int attack, Location startingLocation, Weapon startingWeapon) {
		super(name, healthPoints, attack, startingLocation);
		this.inventory = new ArrayList<>();
		this.weapon = startingWeapon;
	}
	
    // ----- Getters and setters -----
	/**
     * Getter for the attribute isGameFinished.
     * 
     * @return boolean : the value of the name attribute
     */
	public boolean getIsGameFinished()
	{
		return this.isGameFinished;
	}
	
	/**
     * Set the attribute isGameFinished to true, makes the game end in the game main loop
     * @see Core.Game#gameLoop()
     */
	public void gameFinished()
	{
		this.isGameFinished = true;
	}
	
	/**
     * Getter for the attribute weapon.
     * 
     * @return Weapon : the value of the weapon attribute
     */
	public Weapon getWeapon()
	{
		return this.weapon;
	}
	
	/**
     * Setter for the attribute weapon.
     * 
     * @param weapon (Weapon) : the value of the weapon attribute
     */
	public void setWeapon(Weapon weapon)
	{
		this.weapon = weapon;
	}
	
	// ----- Inventory management -----
	/**
     * Add an item to the inventory list
     * 
     * @param item (Item) : the item to add
     */
	public void addToInventory(Item item) {
		this.inventory.add(item); 
	}
	
	/**
     * Remove an item to the inventory list
     * 
     * @param item (Item) : the item to remove
     */
	public void removeFromInventory(Item item) 
	{
		this.inventory.remove(item); 
	}
	
	/**
     * Get an item in the inventory list (return null if the item is not there)
     * 
     * @param itemName (String) : the name of the item to get
     */
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
	/**
     * Print the healthPoints of the character
     */
	@Override
	public void printHP()
	{
		System.out.print("You have ");
		super.printHP();
	}
	
	/**
     * Print the inventory of the character
     */
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
	
	/**
     * Print the weapon of the character
     */
	public void printWeapon()
	{
		if (this.weapon == null)
		{
			System.out.println("Your hands are empty.");
		}
		else
		{
			System.out.println("You hold your precious " + this.weapon.getName() + ". (" + this.weapon.getBonusAttackPoint() + " atk) (" + this.weapon.getNumberOfUse() + " uses)");
		}
	}
	
	/**
     * Print all the informations of the character (health points, inventory, weapon)
     */
	public void printHero()
	{
		printHP();
		printWeapon();
		printInventory();
	}

	// ----- Find items -----
	/**
     * Get an item in the inventory list AND in the currentRoom (return null if the item is not there)
     * 
     * @param itemName (String) : the name of the item to get
     */
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
	/**
     * Get the attack power after adding the attack of the weapon
     * 
     * @return int : the value of the attack power
     */
	@Override
	public int getFinalAttackPower()
	{
		int fAtk = super.getFinalAttackPower();
		
		if (this.weapon != null)		
			fAtk += this.weapon.getBonusAttackPoint();	
		
		return fAtk;
	}
	
	/**
     * Make the hero die by printing a message before calling gameFinished()
     * 
     * @see gameFinished()
     */
	@Override
	public void die()
	{
		System.out.println("You died.");
		gameFinished();
	}
	
	// ----- Methods for the commands -----
	/**
     * Calls other methods with the correct arguments depending on the command at the first position of the list in parameters
     * @param commandAndArgs (List<String>) : a list with the inputs of the player
     * @throws Exception exception for the case where the command doesn't exist
     */
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
                    switch (len) {
                        case 1 -> System.out.println("USE what ?");
                        case 2 -> useItem(commandAndArgs.get(1));
                        default -> useItemOnItem(commandAndArgs.get(1), commandAndArgs.get(2));
                    }
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
			gameFinished();
		}
		else
		{
			throw new IncorrectCommandException();
		}
	}	
	// END doTheCommands --------------------
	
	// ----- HELP -----
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
	
	// ----- GO -----
	public void changeLocation(String locationName)
	{
		this.setCurrentLocation(getCurrentLocation().takeExit(locationName, this));
	}
	
	// ----- LOOK -----
	public void lookSomething(String somethingName)
	{
		// SPECIAL CASE FOR STATS
		if (somethingName.equalsIgnoreCase("STATS") || somethingName.equalsIgnoreCase("HP"))
		{
			printHP();
		}
		// SPECIAL CASE FOR WEAPON
		else if (somethingName.equalsIgnoreCase("WEAPON"))
		{
			printWeapon();
		}
		// SPECIAL CASE FOR INVENTORY
		else if (somethingName.equalsIgnoreCase("INVENTORY"))
		{
			printInventory();
		}
		// SPECIAL CASE FOR HERO
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
	
	// ----- TAKE -----
	public void takeItem(String itemName)
	{
		Item itemToTake = this.getCurrentLocation().getItemFromString(itemName);
		if (itemToTake instanceof Take take)
		{
			if (take.take())
			{
				addToInventory(itemToTake);
				getCurrentLocation().removeItem(itemToTake);
				itemToTake.setHero(this);
				System.out.println("You take the " + itemName + " and put it in your inventory.");	
				
				// COUNTER ATTACK
				getCurrentLocation().allEnemiesAttack();
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
	
	// ----- USE -----
	public void useItem(String itemName)
	{
		Item itemToUse = getItemByName(itemName);
		if (itemToUse instanceof Use use)
		{
			if (!use.use())
			{
				System.out.println("The " + itemName + " cannot be used right now.");
			}
		}
		else 
		{
			System.out.println("The " + itemName + " cannot be used. Does it even exist ?");
		}
	}
	
	// ----- USEON -----
	public void useItemOnItem(String item1Name, String item2Name)
	{
		Item itemToUse = getItemByName(item1Name);
		Item itemToUseOn = getItemByName(item2Name);
		if (itemToUseOn instanceof UseOn useOn)
		{
			if (!useOn.useOn(itemToUse))
			{
				System.out.println("The " + item1Name + " cannot be used on " + item2Name + ".");
			}
		}
		else 
		{
			System.out.println("The " + item1Name + " cannot be used. Does it even exist ?");
		}
	}
	
	// ----- ATTACK -----
	public void attackEnemy(String enemyName)
	{   
            if(enemyName != null){
		// SPECIAL CASE FOR SETA
		if (enemyName.equalsIgnoreCase("seta"))
		{
			System.out.println("You trully are a monster.");
			setName("Monster");
			return;
		}
		
		Enemy enemy = getCurrentLocation().getEnemyByName(enemyName);
		
		if (enemy != null)
		{
			attackCharacter((Character)enemy);
			if (this.weapon != null)
				this.weapon.lessDurability(1);	
		
			// COUNTER ATTACK
			getCurrentLocation().allEnemiesAttack();
		}
		else
			System.out.println("This enemy doesn't exist.");
            }
	}

}

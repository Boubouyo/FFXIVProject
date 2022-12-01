package characters;

import commands.Look;
import locations.Location;

/**
 * A character class for the enemies.
 * 
 * @author Victor
 */

public class Enemy extends Character implements Look 
{
	
	private static final String DEFAULT_DESCRIPTION = "It's a simple enemy.";
	public String description;

	/**
     * Constructor method for the class Enemy.
     * @param name (String) : the character name
     * @param healthPoints (int) : the number of health points for the character
     * @param attack (int) : the value of the character attack
     * @param startingLocation (Location) : the first location of the character
     * @param description (String) : the description of the enemy
     */
	public Enemy (String name, int healthPoints, int attack, Location startingLocation, String description)
	{
		super(name, healthPoints, attack, startingLocation);
		this.description = description;
	}	
	
	/**
     * Constructor method for the class Enemy with a default description
     * @param name (String) : the character name
     * @param healthPoints (int) : the number of health points for the character
     * @param attack (int) : the value of the character attack
     * @param startingLocation (Location) : the first location of the character
     */
	public Enemy (String name, int healthPoints, int attack, Location startingLocation)
	{
		this(name, healthPoints, attack, startingLocation, DEFAULT_DESCRIPTION);
	}
	
	/**
     * Make the enemy attack the hero
     */
	public void attackHero()
	{
		Hero hero = getCurrentLocation().getHero();
		if (hero != null)
			attackCharacter(hero);
	}
	
	// From heritage
	/**
     * Make the enemy die and remove it from the location
     */
	@Override 
	public void die()
	{
		System.out.println(this.getName() + " is defeated.");
		this.getCurrentLocation().deleteEnemy(this);
	}
	
	// From interface Look
	/**
     * Make the enemy print it's name, description and health points.
     */
	@Override 
	public void look()
	{
		System.out.println("It's a " + getName() + ".");
		System.out.println(this.description);
		System.out.print("It has ");
		printHP();
	}
}

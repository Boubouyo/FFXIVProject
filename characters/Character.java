package characters;

import locations.Location;

/**
 * An abstract class to describe a character.
 * 
* @author Victor
*/

public abstract class Character 
{
	
	private String name;
	private Location currentLocation;

	private int maxHealthPoints;
	private int currentHealthPoints;
	private int attack;
	
	/**
     * Constructor method for the class Character.
     * @param name (String) : the character name
     * @param healthPoints (int) : the number of health points for the character
     * @param attack (int) : the value of the character attack
     * @param startingLocation (Location) : the first location of the character
     */
	public Character (String name, int healthPoints, int attack, Location startingLocation)
	{
		this.name = name;
		this.maxHealthPoints = healthPoints;
		this.currentHealthPoints = healthPoints;
		this.attack = attack;
		this.currentLocation = startingLocation;
	}
	
    // Getters and setters 
	/**
     * Getter for the attribute name.
     * 
     * @return String : the value of the name attribute
     */
	public String getName()
	{
		return this.name;
	}
	
	/**
     * Setter for the attribute name.
     * 
     * @param name (String) : the value of the name attribute
     */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
     * Getter for the attribute currentHealthPoints.
     * 
     * @return int : the value of the currentHealthPoints attribute
     */
	public int getHealthPoints()
	{
		return this.currentHealthPoints;
	}
	
	/**
     * Getter for the attribute currentLocation.
     * 
     * @return Location : the value of the currentLocation attribute
     */
	public Location getCurrentLocation() {
		return currentLocation;
	}
	
	/**
     * Setter for the attribute currentLocation.
     * 
     * @param currentLocation (Location) : the value of the currentLocation attribute
     */
	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}
	
    // Print
	/**
     * Print the healthPoints of the character
     */
	public void printHP()
	{
		System.out.println(this.getHealthPoints() + " HP.");
	}
	
	// Combat
	/**
     * Get the attack power after potential calculations
     * 
     * @return int : the value of the attack power
     */
	public int getFinalAttackPower()
	{
		return this.attack;
	}
	
	/**
     * Make the character lose a certain amount of health points
     * 
     * @param damage (int) : the value of the damage dealt
     */
	public void damageCharacter(int damage)
	{
		System.out.println(this.name + " looses " + damage + " HP.");
		this.currentHealthPoints -= damage;
	}
	
	/**
     * Make the character recover a certain amount of health points (not more than the max amount)
     * 
     * @param heal (int) : the value of the damage healed
     */
	public void healCharacter(int heal) 
	{		
		this.currentHealthPoints += heal;
		if (this.currentHealthPoints > this.maxHealthPoints)
		{
			heal -= this.currentHealthPoints - this.maxHealthPoints;
			this.currentHealthPoints = this.maxHealthPoints;
		}
		System.out.println(this.name + " recovers " + heal + " HP.");
			
	}
	
	/**
     * Make the character attack another character and check it the other character die
     * 
     * @param c (Character) : the character to attack
     */
	public void attackCharacter(Character c)
	{
		System.out.print(this.name + " attacks " + c.getName() + " : ");
		c.damageCharacter(getFinalAttackPower());
		
		if (c.getHealthPoints() <= 0)
		{
			c.die();
		}
	}
	/**
     * Make the character "die"
     */
	// Abstract methods
	public abstract void die();
}


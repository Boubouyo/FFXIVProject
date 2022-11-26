package characters;

import locations.Location;

public abstract class Character 
{
	
	private final String name;
	private Location currentLocation;

	private int maxHealthPoints;
	private int currentHealthPoints;
	private int attack;
	
	public Character (String name, int healthPoints, int attack, Location startingLocation)
	{
		this.name = name;
		this.maxHealthPoints = healthPoints;
		this.currentHealthPoints = healthPoints;
		this.attack = attack;
		this.currentLocation = startingLocation;
	}
	
    // Getters and setters 
	public String getName()
	{
		return this.name;
	}
	
	public int getHealthPoints()
	{
		return this.currentHealthPoints;
	}
	
	public Location getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}
	
    // Print
	public void printHP()
	{
		System.out.println(this.getHealthPoints() + " HP.");
	}
	
	// Combat
	public int getFinalAttackPower()
	{
		return this.attack;
	}
	
	public void damageCharacter(int damage)
	{
		System.out.println(this.name + " looses " + damage + " HP.");
		this.currentHealthPoints -= damage;
	}

	public void healCharacter(int heal) 
	{
		System.out.println(this.name + " recovers " + heal + " HP.");
		this.currentHealthPoints += heal;
		if (this.currentHealthPoints > this.maxHealthPoints)
			this.currentHealthPoints = this.maxHealthPoints;
	}
	
	public void attackCharacter(Character c)
	{
		System.out.print(this.name + " attacks " + c.getName() + " : ");
		c.damageCharacter(getFinalAttackPower());
		
		if (c.getHealthPoints() <= 0)
		{
			c.die();
		}
	}
	
	// Abstract methods
	public abstract void die();
}


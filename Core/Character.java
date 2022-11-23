package Core;

public abstract class Character 
{
	private final String name;
	protected Location currentLocation;
	
	private int healthPoints;
	private int attack;
	
	public Character (String name, int healthPoints, int attack, Location startingLocation)
	{
		this.name = name;
		this.healthPoints = healthPoints;
		this.attack = attack;
		this.currentLocation = startingLocation;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getHealthPoints()
	{
		return this.healthPoints;
	}
	
	public void damageCharacter(int damage)
	{
		this.healthPoints -= damage;
	}

	// I'm doing another method for a similar purpose to avoid the confusion of having only one method 
	// and having to think about the minus sign
	public void healCharacter(int heal) 
	{
		damageCharacter(-heal);
	}
	
	public void attackCharacter(Character c)
	{
		c.damageCharacter(this.attack);
		
		if (c.getHealthPoints() <= 0)
		{
			c.die();
		}
	}
	
	public abstract void die();
}


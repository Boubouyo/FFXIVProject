package Core;

public abstract class Character 
{
	private final String name;
	protected Location currentLocation;
	
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
	
	public String getName()
	{
		return this.name;
	}
	
	public int getHealthPoints()
	{
		return this.currentHealthPoints;
	}
	
	public void damageCharacter(int damage)
	{
		this.currentHealthPoints -= damage;
	}

	public void healCharacter(int heal) 
	{
		damageCharacter(-heal);
		if (this.currentHealthPoints > this.maxHealthPoints)
			this.currentHealthPoints = this.maxHealthPoints;
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


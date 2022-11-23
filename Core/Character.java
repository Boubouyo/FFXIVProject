package Core;

public abstract class Character 
{
	protected final String name;
	
	protected int healthPoints;
	protected int attack;
	
	public Character (String name, int healthPoints, int attack)
	{
		this.name = name;
		this.healthPoints = healthPoints;
		this.attack = attack;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getHealthPoints()
	{
		return this.healthPoints;
	}
	
	public void reduceHealthPoints(int damage)
	{
		this.healthPoints -= damage;
	}
	
	public void attackCharacter(Character c)
	{
		c.reduceHealthPoints(this.attack);
		
		if (c.getHealthPoints() <= 0)
		{
			c.die();
		}
	}
	
	public abstract void die();
}


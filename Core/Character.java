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
	
	public void damageCharacter(int damage)
	{
		this.healthPoints -= damage;
	}

	// I'm doing another function to avoid the confusion of having only one function and having to think about the minus sign
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


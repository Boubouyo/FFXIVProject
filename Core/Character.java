package Core;

public abstract class Character 
{
	protected final String name;
	
	protected int healthPoints;
	
	public Character (String name, int healthPoints)
	{
		this.name = name;
		this.healthPoints = healthPoints;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getHealthPoints()
	{
		return this.healthPoints;
	}
}


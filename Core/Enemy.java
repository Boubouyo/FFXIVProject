package Core;

public class Enemy extends Character implements Look
{
	private static final String DEFAULT_DESCRIPTION = "It's a simple enemy.";
	
	public String description;
	
	public Enemy (String name, int healthPoints, int attack, Location startingLocation, String description)
	{
		super(name, healthPoints, attack, startingLocation);
		this.description = description;
		startingLocation.addEnemy(this);
	}	
	
	public Enemy (String name, int healthPoints, int attack, Location startingLocation)
	{
		this(name, healthPoints, attack, startingLocation, DEFAULT_DESCRIPTION);
	}
	
	public void attackHero()
	{
		attackCharacter(currentLocation.getHero());
	}
	
	@Override 
	public void die()
	{
		this.currentLocation.deleteEnemy(this);
	}
	
	@Override 
	public void look()
	{
		System.out.println("It's a " + getName() + ".");
		System.out.println(this.description);
	}
}

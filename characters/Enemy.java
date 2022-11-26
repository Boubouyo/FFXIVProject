package characters;

import commands.Look;
import locations.Location;

public class Enemy extends Character implements Look 
{
	
	private static final String DEFAULT_DESCRIPTION = "It's a simple enemy.";
	public String description;

	public Enemy (String name, int healthPoints, int attack, Location startingLocation, String description)
	{
		super(name, healthPoints, attack, startingLocation);
		this.description = description;
	}	
	
	public Enemy (String name, int healthPoints, int attack, Location startingLocation)
	{
		this(name, healthPoints, attack, startingLocation, DEFAULT_DESCRIPTION);
	}
	
	public void attackHero()
	{
		Hero hero = getCurrentLocation().getHero();
		if (hero != null)
			attackCharacter(hero);
	}
	
	// From heritage
	@Override 
	public void die()
	{
		System.out.println(this.getName() + " is defeated.");
		this.getCurrentLocation().deleteEnemy(this);
	}
	
	// From interface Look
	@Override 
	public void look()
	{
		System.out.println("It's a " + getName() + ".");
		System.out.println(this.description);
		System.out.print("It has ");
		printHP();
	}
}

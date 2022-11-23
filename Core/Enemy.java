package Core;

public class Enemy extends Character
{
	
	public Enemy (String name, int healthPoints, int attack, Location startingLocation)
	{
		super(name, healthPoints, attack, startingLocation);
	}
	
	
	
	@Override 
	public void die()
	{
		this.currentLocation.deleteCharacters(this);
	}
}

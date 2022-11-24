package Core;


public abstract class Item implements Look{
	private final String name;
	private String description;
	private Location currentLocation;
	private Hero hero;

	
	// -------------------------------------------------------------------//
	public Item(String name, String description, Location location) {
		this.name = name;
		this.description = description;
		this.currentLocation = location;
		this.hero = null;
	}
	
	public String getName() {
		return this.name;
	}
	
	
	// -------------------------------------------------------------------//
	@Override
	public void look() {
		System.out.println("You are looking at "+this.name+".");
		System.out.println(this.description);
	}
	
	public Hero getHero()
	{
		return this.hero;
	}
	
	public void setHero(Hero hero)
	{
		this.hero = hero;
	}
	
	public Location getLocation()
	{
		return this.currentLocation;
	}
	
	public void setLocation(Location loc)
	{
		this.currentLocation = loc;
	}
}

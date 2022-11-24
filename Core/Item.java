package Core;


public abstract class Item implements Look{
	private final String name;
<<<<<<< Updated upstream
	private String description;
	private Location currentLocation;
	private Hero hero;
=======
	private String description = "It's an item. It could be usefull";
	private boolean pickable;
>>>>>>> Stashed changes

	
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
<<<<<<< Updated upstream
	
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
=======

	public boolean use() {
		return false;
	}

	public boolean take() {
		return false;
>>>>>>> Stashed changes
	}
}

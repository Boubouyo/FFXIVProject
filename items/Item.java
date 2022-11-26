package items;

import characters.Hero;
import commands.Look;
import locations.Location;

public abstract class Item implements Look {
	// ---------------------------ATTRIBUTS------------------------------------//
	private final String name;
	private String description;
	private Location currentLocation;
	private Hero hero;

	
	// --------------------------CONSTRUCTEUR----------------------------------//
	public Item(String name, String description, Location location) {
		this.name = name;
		this.description = description;
		this.currentLocation = location;
		this.hero = null;
	}
	
	
	// --------------------------GET & SET-------------------------------------//
	public String getName() {
		return this.name;
	}
	
	public void setDescription(String newDescription) {
		this.description = newDescription;
	}
	
	public Hero getHero() {
		return this.hero;
	}
	
	public void setHero(Hero hero) {
		this.hero = hero;
	}
	
	public Location getLocation() {
		return this.currentLocation;
	}
	
	public void setLocation(Location loc) {
		this.currentLocation = loc;
	}
	
	
	// --------------------------OVERRIDE------------------------------------//
	@Override
	public void look() {
		System.out.println("You are looking at "+this.name+".");
		System.out.println(this.description);
	}


}

package items;

import characters.Hero;
import commands.Look;
import locations.Location;

/**
 * Abstract class that describe the basics of an item.
 * Items are composed of a name, a description, a location and a hero.
 *  
 * @author Anais
 */

public abstract class Item implements Look {
	// ---------------------------ATTRIBUTS------------------------------------//
	private final String name;
	private String description;
	private Location currentLocation;
	private Hero hero;

	
	// --------------------------CONSTRUCTEUR----------------------------------//
	/**
	 * The constructor method of the class Item.
	 * It initialize an item with a name, a description and a location.
	 * 
	 * @param name
	 * @param description
	 * @param location
	 */
	public Item(String name, String description, Location location) {
		this.name = name;
		this.description = description;
		this.currentLocation = location;
		this.hero = null;
	}
	
	
	// --------------------------GET & SET-------------------------------------//
	/** 
	 * Getter on the item name.
	 * 
	 * @return the name of the item (String)
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Setter of a new description for the item. 
	 * 
	 * @param newDescription
	 */
	public void setDescription(String newDescription) {
		this.description = newDescription;
	}
    
	/**
	 * Getter on the item description.
	 * It could be used to print the description of an item. 
	 * while using the look; command for example.
	 * 
	 * @return the description of Item
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Getter on the hero linked to item.
	 * 
	 * @return null if no hero are linked to item or the hero linked to item if picked
	 */
	public Hero getHero() {
		return this.hero;
	}
	
	/**
	 * Setter of the hero linked to item
	 */
	public void setHero(Hero hero) {
		this.hero = hero;
	}
	
	/**
	 * Getter on the location of an item.
	 * 
	 * @return the location of the item
	 */
	public Location getLocation() {
		return this.currentLocation;
	}
	
	/**
	 * Setter of the new location of an item
	 * 
	 * @param loc
	 */
	public void setLocation(Location loc) {
		this.currentLocation = loc;
	}
	
	
	// --------------------------OVERRIDE------------------------------------//
	/**
	 * Implementation of the command look;
	 */
	@Override
	public void look() {
		System.out.println("You are looking at "+this.name+".");
		System.out.println(this.description);
	}


}

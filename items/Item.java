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
	 * It initialize an item with a name, a description, a location 
	 * and hero set to null by default.
	 * 
	 * @param name 			the name of the item (each item as a different one)
	 * @param description	the description of the item (what we notice by using the command look)
	 * @param location		where the item is located
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
	 * @return the name of the item
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Setter of a new description for the item. 
	 * 
	 * @param newDescription  the new String that will be set as the description of the item
	 */
	public void setDescription(String newDescription) {
		this.description = newDescription;
	}
    
	/**
	 * Getter on the item description.
	 * It could be used to print the description of an item. 
	 * while using the look; command for example.
	 * 
	 * @return the description of the item
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Getter on the hero linked to item.
	 * 
	 * @return null if no hero are linked to item 
	 * and the hero linked to the item if there is one
	 */
	public Hero getHero() {
		return this.hero;
	}
	
	/**
	 * Setter of the hero linked to item.If the item get taken this method must be used to link the item and the hero.
         * @param hero Hero hero
	 */
	public void setHero(Hero hero) {
		this.hero = hero;
	}
	
	/**
	 * Getter on the location of an item.
	 * 
	 * @return Where the item is located, what room it is in.
	 */
	public Location getLocation() {
		return this.currentLocation;
	}
	
	/**
	 * Setter of the new location of an item
	 * 
	 * @param loc which is the new room the item will be located in
	 */
	public void setLocation(Location loc) {
		this.currentLocation = loc;
	}
	
	
	// --------------------------OVERRIDE------------------------------------//
	/**
	 * Implementation of the command look;
	 * It print the name of the item and it's description.
	 */
	@Override
	public void look() {
		System.out.println("You are looking at "+this.name+".");
		System.out.println(this.description);
	}


}

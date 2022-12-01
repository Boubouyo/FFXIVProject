package items;

import commands.Take;
import locations.Location;

/**
 * Abstract class Pickable which is here to redefine Item
 * with a new attribute that tells if an items is pickable or not.
 * 
 * @author Anais
 */

public abstract class Pickable extends Item implements Take {
	// ---------------------------ATTRIBUTS------------------------------------//
	private boolean pickable;

	
	// --------------------------CONSTRUCTEUR----------------------------------//
	/**
	 * The constructor method of the Pickable class.
	 * It uses the constructor method of item
	 * and initialize the boolean isPickable
	 * 
	 * @param name
	 * @param description
	 * @param location
	 * @param isPickable
	 */
	public Pickable(String name, String description, Location location, boolean isPickable) {
		super(name, description, location);
		this.pickable = isPickable;
	}
        
	// ---------------------------OPERATIONS-----------------------------------//
	/**
	 * A method that make an item pickable
	 */
	public void becomePickable() {
		this.pickable = true;
	}
	
	/**
	 * A method that remove the possibility to take an item
	 */
	public void lockPickable() {
		this.pickable = false;
	}
	
	
	// --------------------------OVERRIDE------------------------------------//
	/**
	 * Implementation of the method take
	 * @return : true if the item is pickable and false if not
	 */
	@Override
	public boolean take() {
		return this.pickable;
	}
	
	
	
}

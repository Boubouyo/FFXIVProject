package items;

import commands.Take;
import locations.Location;

public abstract class Pickable extends Item implements Take
{
	// ---------------------------ATTRIBUTS------------------------------------//
	private boolean pickable;

	
	// --------------------------CONSTRUCTEUR----------------------------------//
	public Pickable(String name, String description, Location location, boolean isPickable) {
		super(name, description, location);
		this.pickable = isPickable;
	}
        
	// ---------------------------OPERATIONS-----------------------------------//
	public void becomePickable() {
		this.pickable = true;
	}
	
	public void lockPickable() {
		this.pickable = false;
	}
	
	
	// --------------------------OVERRIDE------------------------------------//
	@Override
	public boolean take() {
		return this.pickable;
	}
	
	
	
}

package items;

import commands.Take;
import locations.Location;

public abstract class Pickable extends Item implements Take
{
	// ---------------------------ATTRIBUTS------------------------------------//
	private Boolean pickable;

	
	// --------------------------CONSTRUCTEUR----------------------------------//
	public Pickable(String name, String description, Location location, boolean isPickable) {
		super(name, description, location);
		this.pickable = isPickable;
	}
	
	
	// ---------------------------OPERATIONS-----------------------------------//
	public void becomePickable() {
		this.pickable = true;
	}
	
	
	// --------------------------OVERRIDE------------------------------------//
	@Override
	public boolean take() {
		if (this.pickable)
		{
			getHero().addToInventory(this);
			getLocation().removeItem(this);
		}
		return this.pickable;
	}
	
	
	
}

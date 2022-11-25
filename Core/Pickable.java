package Core;

public abstract class Pickable extends Item implements Take{
	private static final Boolean DEFAULT_PICKABLE = false;
	private Boolean pickable;

	
	// -------------------------------------------------------------------//
	public Pickable(String name, String description, Location location) {
		super(name, description, location);
		this.pickable = DEFAULT_PICKABLE;
	}
	
	
	// -------------------------------------------------------------------//
	public void becomePickable() {
		this.pickable = true;
	}
	
	
	// -------------------------------------------------------------------//
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

package Core;

public class Statuette extends Item implements UseOn, Take{
	private String skill;
	private static final boolean DEFAULT_PICKABLE = false;
	private boolean pickable;
	
	
	// -------------------------------------------------------------------//
	public Statuette(String name, String sk, String description) {
		super(name, description);
		this.skill = sk;
		this.pickable = DEFAULT_PICKABLE;
	}
	
	public void becomePickable() {
		this.pickable = true;
	}

	public String getSkill() {
		return skill;
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
	
	@Override
	
}

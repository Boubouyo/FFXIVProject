package Core;

public class Statuette extends Item implements Take{
	private String skill;
	private static final boolean DEFAULT_PICKABLE = false;
	private boolean pickable;
	
	public Statuette(String name, String sk, String description, Location location) {
		super(name, description, location);
		this.skill = sk;
		this.pickable = DEFAULT_PICKABLE;
	}
	
	@Override
	public boolean take() {
		if (this.pickable)
		{
			getHero().addToInventory(this);
			getLocation().removeItem(this);
		}
		return this.pickable;
	}
	
	public void becomePickable()
	{
		this.pickable = true;
	}

	public String getSkill() {
		return skill;
	}
	
}

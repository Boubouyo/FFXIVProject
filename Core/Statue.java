package Core;

public class Statue extends Item implements Take{
	private String skill;
	private static final boolean DEFAULT_PICKABLE = false;
	private boolean pickable;
	
	public Statue(String name, String sk, String description) {
		super(name, description);
		this.setSkill(sk);
		this.pickable = DEFAULT_PICKABLE;
	}
	
	@Override
	public boolean take() {
		return this.pickable;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}
	
	
}

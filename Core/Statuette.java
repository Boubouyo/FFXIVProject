package Core;

public class Statuette extends Item implements UseOn, Take{
	private String skill;
	private static final boolean DEFAULT_PICKABLE = false;
	private boolean pickable;
	
	public Statuette(String name, String sk, String description) {
		super(name, description);
		this.setSkill(sk);
		this.pickable = DEFAULT_PICKABLE;
	}
	
	@Override
	public boolean take() {
		return this.pickable;
	}
	
	@Override 

	public String getSkill() {
		return skill;
	}

	
	
	
}

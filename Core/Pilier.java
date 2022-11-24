package Core;

public class Pilier extends Item {
	private String skill;
	
	public void Pillier(String name, String sk, String description) {
		super(name, description);
		this.skill = sk;
	}
	
	public void becomePickable() {
		this.pickable = true;
	}

	public String getSkill() {
		return skill;
	}
}

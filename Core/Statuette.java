package Core;

public class Statuette extends Pickable implements UseOn{
	private int statuetteID;
	
	
	// -------------------------------------------------------------------//
	public Statuette(String name, int ID, String description, Location location) {
		super(name, description, location);
		this.statuetteID = ID;
	}

	public int getSkill() {
		return statuetteID;
	}

	
	// -------------------------------------------------------------------//
	@Override
	public boolean useOn(Item item) {
		return false;
	}
	
}

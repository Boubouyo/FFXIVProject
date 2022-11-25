package Core;

public class Pilier extends Item {
	private int pilierID;
	
	
	// -------------------------------------------------------------------//
	public Pilier(String name, int ID, String description, Location location) {
		super(name, description, location);
		this.pilierID = ID;
	}
	
	
	// -------------------------------------------------------------------//
	public int getSkill() {
		return pilierID;
	}
	
	
	// -------------------------------------------------------------------//
	
}

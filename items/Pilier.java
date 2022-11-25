package items;

import locations.Location;

public class Pilier extends Item {
	// ---------------------------ATTRIBUTS------------------------------------//
	private int pilierID;
	
	
    // --------------------------CONSTRUCTEUR----------------------------------//
	public Pilier(String name, int ID, String description, Location location) {
		super(name, description, location);
		this.pilierID = ID;
	}
	
	
    // ----------------------------GET & SET-----------------------------------//
	public int getSkill() {
		return pilierID;
	}
	
	
	// -------------------------------------------------------------------//
	
}

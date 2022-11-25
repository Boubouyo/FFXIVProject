package items;

import commands.UseOn;
import locations.Location;

public class Statuette extends Pickable implements UseOn{
	// ---------------------------ATTRIBUTS------------------------------------//
	private int statuetteID;
	
	
    // --------------------------CONSTRUCTEUR----------------------------------//
	public Statuette(String name, int ID, String description, Location location) {
		super(name, description, location);
		this.statuetteID = ID;
	}

	
    // ----------------------------GET & SET-------------------------------------//
	public int getSkill() {
		return statuetteID;
	}

	
	// --------------------------OVERRIDE------------------------------------//
	@Override
	public boolean useOn(Item item) {
		return false;
	}
	
}

package items;

import commands.UseOn;
import locations.Location;

public class Statuette extends Pickable implements UseOn{
	// ---------------------------ATTRIBUTS------------------------------------//
	private int statuetteID;
	
	
    // --------------------------CONSTRUCTEUR----------------------------------//
<<<<<<< Updated upstream
	public Statuette(String name, int ID, String description, Location location, boolean isPickable) {
		super(name, description, location, isPickable);
=======
	public Statuette(String name, int ID, String description, Location location) {
		super(name, description, location, false);
>>>>>>> Stashed changes
		this.statuetteID = ID;
	}

	
    // ----------------------------GET & SET-------------------------------------//
	public int getId() {
		return statuetteID;
	}

	
	// --------------------------OVERRIDE------------------------------------//
	@Override
	public boolean useOn(Item item) {
		return false;
	}
	
}

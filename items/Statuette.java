package items;

import locations.Location;

public class Statuette extends Pickable{
	// ---------------------------ATTRIBUTS------------------------------------//
	private final int statuetteId;
	
	
    // --------------------------CONSTRUCTEUR----------------------------------//
	public Statuette(String name, int Id, String description, Location location, boolean isPickable) {
		super(name, description, location, isPickable);

		this.statuetteId = Id;
	}

	
    // ----------------------------GET & SET-------------------------------------//
	public int getId() {
		return statuetteId;
        }
	
}

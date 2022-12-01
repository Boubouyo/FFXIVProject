package items;

import locations.Location;

/**
 * Class Statuette that would be used for enigma.
 * 
 * @author Anais
 */

public class Statuette extends Pickable{
	// ---------------------------ATTRIBUTS------------------------------------//
	private final int statuetteId;
	
	
    // --------------------------CONSTRUCTEUR----------------------------------//
	/**
	 * Constructor method of the class Statuette.
	 * It initialize the object by using the pickable constructor 
	 * The new attribute is here to compare with other object that has the same ID
	 * 
	 * @param name
	 * @param Id : the id the Statuette
	 * @param description
	 * @param location
	 * @param isPickable
	 */
	public Statuette(String name, int Id, String description, Location location, boolean isPickable) {
		super(name, description, location, isPickable);
		this.statuetteId = Id;
	}

	
    // ----------------------------GET & SET-------------------------------------//
	/**
	 * Getter on the ID of the class.
	 * 
	 * @return
	 */
	public int getId() {
		return statuetteId;
        }
	
}

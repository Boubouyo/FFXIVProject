package items;

import locations.Location;

/**
 * Class Statuette : a subclass of the class Pickable.
 * 
 * @author Anais
 */

public class Statuette extends Pickable{
	// ---------------------------ATTRIBUTS------------------------------------//
	private final int statuetteId;
	
	
    // --------------------------CONSTRUCTEUR----------------------------------//
	/**
	 * Constructor method of the class Statuette.
	 * It initialize the object by using the pickable constructor method.
	 * The new attribute is here to compare with other object that has the same ID
	 * 
	 * @param name			name of the Statuette 
	 * @param Id 			short key that identify a statuette from another one
	 * @param description	text that describes the pillar
	 * @param location		where the item is located
	 * @param isPickable	boolean that define is the item can be taken or not
	 */
	public Statuette(String name, int Id, String description, Location location, boolean isPickable) {
		super(name, description, location, isPickable);
		this.statuetteId = Id;
	}

	
    // ----------------------------GET & SET-------------------------------------//
	/**
	 * Getter of the ID of a statuette. 
	 * It could be used to compare with an ID of another item that as an ID too (cf. Pillar)
	 * 
	 * @return the ID of the statuette
	 */
	public int getId() {
		return statuetteId;
        }
	
}

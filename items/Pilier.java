package items;

import locations.Location;

public class Pilier extends Item {
	// ---------------------------ATTRIBUTS------------------------------------//
	private int pilierID;
        private Item onPillar = null;
	
	
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
	public boolean rightStatuetteonPillar(){
            if(this.onPillar != null){
                if(this.onPillar instanceof Statuette statuette){
                    if(statuette.getId() == this.pilierID){
                        return true;
                    }
                }
            }
            
            return false;
        }
}

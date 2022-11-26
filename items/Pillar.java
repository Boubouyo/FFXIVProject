package items;

import locations.Location;

public class Pillar extends Item { // TODO AJOUT DE L'ATTRIBUT UseOn
	// ---------------------------ATTRIBUTS------------------------------------//
	private int pilierID; // TODO renommer pillarID
	private Item onPillar = null;
	
	
    // --------------------------CONSTRUCTEUR----------------------------------//
	public Pillar(String name, int ID, String description, Location location) {
		super(name, description, location);
		this.pilierID = ID; // TODO renommer pillarID
	}
	
	
    // ----------------------------GET & SET-----------------------------------//
	public int getSkill() {
		return pilierID; // TODO renommer pillarID
	}
	
	
	
	// -------------------------------------------------------------------//
	public boolean rightStatuetteonPillar(){
            if(this.onPillar != null){
                if(this.onPillar instanceof Statuette statuette){
                    if(statuette.getId() == this.pilierID){ // TODO renommer pillarID
                        return true;
                    }
                }
            }
            
            return false;
        }
	
	public void checkPillar()
	{
		// TODO If the ID of the statuette this.onPillar is the same as pillarID then we lock the item
		// Pour préciser, tu dois passer la statuette en isPickable = false 
		// Aussi si tu peux faire des prints si c'est bon genre "le pilier se met à briller"
	}
	
	//TODO rajouter l'override
	public boolean UseOn(Item item)
	{
		// TODO We test if the item can be placed on the pillar (instanceof Statuette) if true then we place it on the pillar and we return true
		// TODO If the statuette is placed then we call check pillar
		return true; // TODO <-- pour pas que ça mette d'erreur pour le moment (à changer si bsn)
	}
}

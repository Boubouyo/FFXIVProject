package items;

import locations.Location;
import commands.UseOn;

public class Pillar extends Item implements UseOn {
	// ---------------------------ATTRIBUTS------------------------------------//
	private int pillarID; 
	private Item onPillar = null;
	
	
    // --------------------------CONSTRUCTEUR----------------------------------//
	public Pillar(String name, int ID, String description, Location location) {
		super(name, description, location);
		this.pillarID = ID; 
	}
	
	
    // ----------------------------GET & SET-----------------------------------//
	public int getSkill() {
		return pillarID; 
	}
	
	
	// ---------------------------OPERATIONS-----------------------------------//
	public boolean rightStatuetteonPillar(){
            if(this.onPillar != null){
                if(this.onPillar instanceof Statuette statuette){
                    if(statuette.getId() == this.pillarID){ 
                        return true;
                    }
                }
            }
            
            return false;
        }
	
	public boolean isStatuette(Item item) {
		return (item instanceof Statuette);
	}
	
	// Checking if the right Statue is on the right pillar and locking it in place if it's correct
	public void putRightStatueOnPillar(Statuette statuette) {
		this.onPillar = statuette;
		
		if (this.pillarID == statuette.getId()) {
			statuette.lockPickable();
			System.out.println("You hear a sound as if something got locked and the Statue start to shine.");
		}
		else {
			System.out.println("It seems like nothing is happening... maybe you should try again...");
		}
	}
	
	
	// ----------------------------OVERRIDE------------------------------------//
	@Override
	public boolean useOn(Item item){
		// TODO We test if the item can be placed on the pillar (instanceof Statuette) if true then we place it on the pillar and we return true
		// TODO If the statuette is placed then we call check pillar
		//return true; // TODO <-- pour pas que ça mette d'erreur pour le moment (à changer si bsn)
		
		if(this.onPillar == null){
			if (isStatuette(item)) {
				this.putRightStatueOnPillar((Statuette)item);
				return true;
			}
		}
		return false;
		
	}
}

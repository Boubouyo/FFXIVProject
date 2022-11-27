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

	public void putStatueOnPillar(Statuette statuette) {
		statuette.getHero().removeFromInventory(statuette);
		statuette.setLocation(this.getLocation());
		statuette.setHero(null);
		
		if (this.pillarID == statuette.getId()) {
			this.onPillar = statuette;
			statuette.lockPickable();

			
			System.out.println("You hear a sound as if something got locked and the pillar starts to shine.");
		}
		else {
			System.out.println("It seems like nothing is happening... maybe you should try again...");
		}
	}
	
	
	public void removeStatuetteFromPillar(){
		
	}
	
	
	// ----------------------------OVERRIDE------------------------------------//
	@Override
	public boolean useOn(Item item){	
		if(this.onPillar == null){
			if (isStatuette(item)) {
				this.putStatueOnPillar((Statuette)item);
				return true;
			}
		}
		return false;
		
	}
	
	@Override
	public void look() {
		super.look();
		if (this.onPillar != null) {
			System.out.println("It has a "+this.onPillar.getName()+" on top of it.");
		}
	}
}

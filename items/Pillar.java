package items;

import locations.Location;
import commands.UseOn;

public class Pillar extends Item implements UseOn {
	// ---------------------------ATTRIBUTS------------------------------------//
	private final int pillarID; 
	private Item onPillar = null;
	
	
    // --------------------------CONSTRUCTEUR----------------------------------//
	public Pillar(String name, int ID, String description, Location location) {
		super(name, description, location);
		this.pillarID = ID; 
	}
	
	
    // ----------------------------GET & SET-----------------------------------//
	public int getPillarId() {
		return pillarID; 
	}
        
        public Item getOnPillar(){
            return this.onPillar;
        }
	
	
	// ---------------------------OPERATIONS-----------------------------------//
	// checking if a statue that was added to a pillar is the right one
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
        
        public static boolean isStatuette(Item item) {
		return (item instanceof Statuette);
	}
	
	// Checking if the right Statue is on the right pillar and locking it in place if it's correct
	private void putStatueOnPillar(Statuette statuette) {
            if(statuette != null){
		this.onPillar = statuette;
		statuette.getHero().removeFromInventory(statuette);
		statuette.getHero().getCurrentLocation().addItem(statuette);
		statuette.setHero(null);
		
		if (this.pillarID == statuette.getId()) {
			statuette.lockPickable();
			System.out.println("You hear a sound as if something got unlocked and the pillar starts to shine.");
		}
		else {
			System.out.println("It seems like nothing is happening... maybe you should try again...");
		}
            }
	}
	
	
	// ----------------------------OVERRIDE------------------------------------//
	@Override
	public boolean useOn(Item item){
            if(item != null){
		if (item.getHero() != null)
		{
			if(this.onPillar == null){
				if (isStatuette(item)) {
					this.putStatueOnPillar((Statuette)item);
					return true;
				}
			}
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

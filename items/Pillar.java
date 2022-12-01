package items;

import locations.Location;
import commands.UseOn;


/**
 * A sub-class of the Item class.
 * It has new interaction compared to Item such as the possbility to use UseOn by the command use;
 * 
 * @author Anais
 */
public class Pillar extends Item implements UseOn {
	// ---------------------------ATTRIBUTS------------------------------------//
	private final int pillarID; 
	private Item onPillar = null;
	
	
    // --------------------------CONSTRUCTEUR----------------------------------//
	/**
	 * Constructor methode of the class Pillar.
	 * It uses the constructor method of the Item 
	 * and add it's ID
	 * 
	 * @param name
	 * @param ID : key to identify a pillar from another 
	 * @param description
	 * @param location
	 */
	public Pillar(String name, int ID, String description, Location location) {
		super(name, description, location);
		this.pillarID = ID; 
	}
	
	
    // ----------------------------GET & SET-----------------------------------//
	/**
	 * Getter of the ID of a pillar. 
	 * It could be used to compare with an ID of another item that as an ID too (cf. Statuette)
	 * 
	 * @return pillarID 
	 */
	public int getPillarId() {
		return pillarID; 
	}
    
	/**
	 * Getter of an item that is on the pillar such as a Statuette for example
	 * 
	 * @return onPillar : an Item that was used on the pillar
	 */
    public Item getOnPillar(){
        return this.onPillar;
    }
	
	
	// ---------------------------OPERATIONS-----------------------------------//
    /**
     * A method that check if a statue that was added to a pillar is the right one thanks to it's ID
     * 
     * @return
     */
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
	
	/**
	 * Check if the item is a statuette or not
	 * 
	 * @param item 
	 * @return boolean : true if the item is a statuette and false if not
	 */
    public static boolean isStatuette(Item item) {
    	return (item instanceof Statuette);
	}
	
	/** 
     * Checking if the Statuette that we are putting on the pillar is the right one not.
     * If the right Statuette is on the right pillar we lock it's possibility to be taken.
     * 
     * @param statuette
     */
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
	/**
	 * Implementation of the command useOn
	 * 
	 * @return true : the item is a statuette and the attribute hero of the item isn't empty 
	 * 						(wich means it was taken)
	 */
	@Override
	public boolean useOn(Item item){
		if(item != null){
			if (item.getHero() != null) {
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
	
	/**
	 * Override of the command look to add the description of what's on the pillar if there is something on it
	 */
	@Override
	public void look() {
		super.look();
		if (this.onPillar != null) {
			System.out.println("It has a "+this.onPillar.getName()+" on top of it.");
		}
		else {
			System.out.println("It remains empty...");
		}
	}
}

package items;

import locations.Location;
import commands.UseOn;


/**
 * Class for Pillar : a subclass of the class Item.
 * It has new interaction compared to Item such as the possbility to use UseOn with the command use;
 * 
 * @author Anais
 */
public class Pillar extends Item implements UseOn {
	// ---------------------------ATTRIBUTS------------------------------------//
	private final int pillarID; 
	private Item onPillar = null;
	
	
    // --------------------------CONSTRUCTEUR----------------------------------//
	/**
	 * Constructor method of the class Pillar.
	 * It uses the constructor method of the Item 
	 * and initialize it's ID
	 * 
	 * @param name 			the name of the pillar
	 * @param ID			short key to identify a pillar from another one
	 * @param description	text that describes the pillar
	 * @param location		where the pillar is located
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
	 * @return the ID of the pillar
	 */
	public int getPillarId() {
		return pillarID; 
	}
    
	/**
	 * Getter of an item that is on the pillar such as a Statuette for example.
	 * There can be only one at a time.
	 * 
	 * @return onPillar an Item that was used on the pillar
	 */
    public Item getOnPillar(){
        return this.onPillar;
    }
	
	
	// ---------------------------OPERATIONS-----------------------------------//
    /**
     * A method that check if a statue that was added to a pillar is the right one thanks to it's ID
     * 
     * @return true if the ID of the statue match the ID of the pillar and false if not
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
	 * Check if an item is a statuette or not
	 * 
	 * @param item the item that need to be checked
	 * @return true if the item is a statuette and false if not
	 */
    public static boolean isStatuette(Item item) {
    	return (item instanceof Statuette);
	}
	
	/** 
     * Method that check if the Statuette that we are putting on the pillar is the right one not.
     * If the right Statuette is on the right pillar we lock it's possibility to be taken to false.
     * 
     * @param statuette the statuette that's being put on the pillar
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
	 * @return true if the item is a statuette, 
	 * 				   the attribute hero of the item isn't null 
	 * 						(which means it was taken and in the inventory of the hero that want to use the item)  
	 * 			   and the top of pillar is empty 
	 * 						(if a statuette was putted on first it has to be taken back to put another one)
	 * 						
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
	 * Override of the command look to add the description of what's on the pillar if there is something on it.
	 */
	@Override
	public void look() {
		super.look();
		if (this.onPillar != null) {
			System.out.println("It has a "+this.onPillar.getName()+" on top of it.");
		}
		else {
			System.out.println("It look like something is missing on top of it...");
		}
	}
}

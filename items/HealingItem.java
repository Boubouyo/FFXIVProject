package items;

import commands.Use;
import locations.Location;


/** 
 * Class for healing items. 
 * A sub-class of item that can boost the health points of the user of the item.
 * 
 * @author Anais
 */
public class HealingItem extends Pickable implements Use{
	// ---------------------------ATTRIBUTS------------------------------------//
	private final int heal;
	
	
	// --------------------------CONSTRUCTEUR----------------------------------//
	/** 
	 * The constructor method of the class HealingItem. 
	 * It use the constructor method of the Item class and 
	 * also initialize the health point that could be given to the user.
	 * 
	 * @param name
	 * @param description
	 * @param location
	 * @param isPickable
	 * @param healPt
	 */
	public HealingItem(String name, String description, Location location, boolean isPickable, int healPt) {
		super(name, description, location, isPickable);
		this.heal = healPt;
	}
	
	
	// ---------------------------OPERATIONS-----------------------------------//
	/** 
	 * Implementation of the command use. 
	 */
	@Override 
	public boolean use() {
		if (this.getHero()!=null) {
			if (this.getHero().getHealthPoints() > 0) {
				super.getHero().healCharacter(this.heal);
			super.getHero().getCurrentLocation().allEnemiesAttack();

                    super.getHero().removeFromInventory(this);
                    return true;
                }
		}
		return false;
	}
	
	/** 
	 * Override of the command look to add the number of health point it could give if used
	 */
	@Override
	public void look() {
		super.look();
		System.out.println("It's an healing item. It could give you "+this.heal+"PV if used.");
	}
	
}

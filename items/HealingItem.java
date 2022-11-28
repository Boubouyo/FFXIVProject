package items;

import commands.Use;
import locations.Location;

public class HealingItem extends Pickable implements Use{
	// ---------------------------ATTRIBUTS------------------------------------//
	private final int heal;
	
	
	// --------------------------CONSTRUCTEUR----------------------------------//
	public HealingItem(String name, String description, Location location, boolean isPickable, int healPt) {
		super(name, description, location, isPickable);
		this.heal = healPt;
	}
	
	
	// ---------------------------OPERATIONS-----------------------------------//
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
	
	@Override
	public void look() {
		super.look();
		System.out.println("It's an healing item. It could give you "+this.heal+"PV if used.");
	}
	
}

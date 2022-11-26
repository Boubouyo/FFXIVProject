package items;

import commands.Use;
import locations.Location;

public class HealPotion extends Pickable implements Use{
	// ---------------------------ATTRIBUTS------------------------------------//
	private int heal;
	
	
	// --------------------------CONSTRUCTEUR----------------------------------//
	public HealPotion(String name, String description, Location location, Boolean isPickable, int healPt) {
		super(name, description, location, isPickable);
		this.heal = healPt;
	}
	
	
	// ---------------------------OPERATIONS-----------------------------------//
	@Override 
	public boolean use() {
		super.getHero().healCharacter(this.heal);
		super.getLocation().allEnemiesAttack();
		
		return true;
	}
	
	
}

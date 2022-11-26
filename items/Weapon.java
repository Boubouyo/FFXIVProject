package items;

import commands.Use;
import locations.Location;

public class Weapon extends Pickable implements Use{
	// ---------------------------ATTRIBUTS------------------------------------//
	private int bonusAtkPoint;
	private int durability;

	
    // --------------------------CONSTRUCTEUR----------------------------------//
<<<<<<< Updated upstream
	public Weapon(String name, int atkpt, int life, String description, Location location, boolean isPickable) {
=======
	public Weapon(String name, int atkpt, int life, String description, Location location, Boolean isPickable) {
>>>>>>> Stashed changes
		super(name, description, location, isPickable);
		this.bonusAtkPoint = atkpt;
		this.durability = life;
	}

	
	// ----------------------------GET & SET-------------------------------------//
	public int getBonusAttackPoint() {
		return bonusAtkPoint;
	}
	
	public int getNumberOfUse() {
		return durability;
	}
	
	public void lessDurability(int down) {
		this.durability -= down;
	}
	
	
	// ---------------------------OPERATIONS-----------------------------------//
	public void destroyWeapon() {
		this.setLocation(null);
		this.setHero(null);
	}
	
	public void equip() {
		if (this.durability == 0) {
			this.destroyWeapon();
		}
		else {
			super.getHero().setWeapon(this);
		}
		
	}
	
	
	// --------------------------OVERRIDE------------------------------------//
	@Override
	public boolean use() {
		if (durability==0) {
			return false;
		}
		else {
			this.lessDurability(1);
			return true;
		}
	}
}

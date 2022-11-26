package items;

import commands.Use;
import locations.Location;

public class Weapon extends Pickable implements Use{
	// ---------------------------ATTRIBUTS------------------------------------//
	private int bonusAtkPoint;
	private int durability;

	
    // --------------------------CONSTRUCTEUR----------------------------------//
	public Weapon(String name, int atkpt, int life, String description, Location location, boolean isPickable) {
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
			if (super.getHero().getWeapon() == null) {
				super.getHero().setWeapon(this);
			}
		}
		
	}
	
	
	// --------------------------OVERRIDE------------------------------------//
	@Override
	public boolean use() {
		if (durability>0 && super.getHero().getWeapon()==this) {
			this.lessDurability(1);
			return true;
		}
		return true;
	}
	
	
	@Override
	public void look() {
		super.look();
		System.out.println("This weapon can be use"+this.durability+"times.");
		System.out.println("Bonus attack"+this.bonusAtkPoint+".");
	}
}

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
		
		if (this.durability <= 0) {
			this.destroyWeapon();
		}
	}
	
	
	// ---------------------------OPERATIONS-----------------------------------//
	public void destroyWeapon() {
		super.getHero().removeFromInventory(this);
		this.setLocation(null);
		this.setHero(null);
	}
	
	public void equip() {
		if (super.getHero().getWeapon() == null) {
			super.getHero().setWeapon(this);
		}
	}
	
	
	// --------------------------OVERRIDE------------------------------------//
	@Override
	public boolean use() {
		if (this.getHero()!=null && durability>0) {
			this.equip();
			this.lessDurability(1);
			return true;
		}
		return false;
	}
	
	
	@Override
	public void look() {
		super.look();
		System.out.println("This weapon can still be used "+this.durability+" times.");
		System.out.println("It has an attack of "+this.bonusAtkPoint+".");
	}
}

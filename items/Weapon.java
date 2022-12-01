package items;

import commands.Use;
import locations.Location;

/**
 * Abstract class weapon which describes the basics of the weapons.
 * 
 * @author Anais
 */

public class Weapon extends Pickable implements Use{
	// ---------------------------ATTRIBUTS------------------------------------//
	private int bonusAtkPoint;
	private int durability;

	
    // --------------------------CONSTRUCTEUR----------------------------------//
	/**
	 * The constructor method of the class Weapon. 
	 * It use the constructor method of the Pickable class
	 * and initialize the bonusAtkPoint and durability of the weapon
	 * 
	 * @param name
	 * @param atkpt is the number of attack point it gives to the users of the item
	 * @param life is durability of the weapon, the number of time it can be used
	 * @param description
	 * @param location
	 * @param isPickable 
	 */
	public Weapon(String name, int atkpt, int life, String description, Location location, boolean isPickable) {
		super(name, description, location, isPickable);
		this.bonusAtkPoint = atkpt;
		this.durability = life;
	}

	
	// ----------------------------GET & SET-------------------------------------//
	/**
	 * Getter of number of attack point it gives to hero if equipped (by the command use)
	 * 
	 * @return bonusAtkPoint
	 */
	public int getBonusAttackPoint() {
		return bonusAtkPoint;
	}
	
	/**
	 * Getter of the number of times the weapon can be used.
	 * 
	 * @return durability
	 */
	public int getNumberOfUse() {
		return durability;
	}
	
	
	// ---------------------------OPERATIONS-----------------------------------//
	/**
	 * A method that reduce the durability of the weapon.
	 * If the durability go below 0 the weapon get destroy (as it cannot be used anymore)
	 * 
	 * @param down : the amount of durability point that will be subtracted for the durability
	 */
	public void lessDurability(int down) {
		this.durability -= down;
		
		if (this.durability <= 0) {
			this.destroyWeapon();
		}
	}
	
	/**
	 * A method that destroy a weapon.
	 * It print what happened and take the weapon off from the hero's inventory.
	 */
	public void destroyWeapon() {
		System.out.println("Oh no ! Your " + getName() + " broke !");
		super.getHero().setWeapon(null);
	}
	
	/**
	 * This method un-equip a weapon if one is already in the hand of the hero
	 * It then add the new weapon in the hand of hero.
	 */
	public void equip() {
		System.out.println("You equipped the " + getName() + ".");
		if (super.getHero().getWeapon() != null) {
			super.getHero().addToInventory(super.getHero().getWeapon());
			System.out.println("You put the " + super.getHero().getWeapon().getName() + " back to the inventory.");
		}
		super.getHero().setWeapon(this);
		super.getHero().removeFromInventory(this);
	}
	
	
	// --------------------------OVERRIDE------------------------------------//
	/**
	 * Override of the use method
	 * Using a weapon means equipping it here.
	 */
	@Override
	public boolean use() {
		if (this.getHero()!=null) {
			this.equip();
			
			return true;
		}
		return false;
	}

	/**
	 * Override of the look method.
	 */
	@Override
	public void look() {
		super.look();
		System.out.println("This weapon can still be used "+this.durability+" times.");
		System.out.println("It has an attack of "+this.bonusAtkPoint+".");
	}
}

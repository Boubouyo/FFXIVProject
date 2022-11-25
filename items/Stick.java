package items;

import locations.Location;

public class Stick extends Weapon{
	// ---------------------------ATTRIBUTS------------------------------------//
	public static final int DEFAULT_DAMAGE = 5;
	
	
	// --------------------------CONSTRUCTEUR----------------------------------//
	public Stick(String name, int atkpt, int life, String description, Location location) {
		super(name, atkpt, life, description, location);
	}
	
	
	// --------------------------OVERRIDE------------------------------------//
	@Override
	public boolean use() {
		if (super.getNumberOfUse() == 0) {
			return false;
		}
		else {
			super.lessNumberOfUse(1);
			super.getHero().damageCharacter(DEFAULT_DAMAGE);
			return true;
		}
	}
}

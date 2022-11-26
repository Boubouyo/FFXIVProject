package items;

import locations.Location;

public class Stick extends Weapon{
	// ---------------------------ATTRIBUTS------------------------------------//
	public static final int DEFAULT_DAMAGE = 5;
	public static final int DEFAULT_ATK = 0;
	public static final int DEFAULT_DURABILITY = 0;
	
	
	// --------------------------CONSTRUCTEUR----------------------------------//
	public Stick(String name, String description, Location location) {
		super(name, DEFAULT_ATK, DEFAULT_DURABILITY, description, location, true);
	}
	
	
	// --------------------------OVERRIDE------------------------------------//
	@Override
	public boolean use() {
		if (super.getNumberOfUse() == 0) {
			return false;
		}
		else {
			super.lessDurability(1);
			super.getHero().damageCharacter(DEFAULT_DAMAGE);
			return true;
		}
	}

}

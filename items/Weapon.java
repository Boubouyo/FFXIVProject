package items;

import commands.Use;
import locations.Location;

public abstract class Weapon extends Pickable implements Use{
	// ---------------------------ATTRIBUTS------------------------------------//
	private int bonusAtkPoint;
	private int numberOfUse;

	
    // --------------------------CONSTRUCTEUR----------------------------------//
	public Weapon(String name, int atkpt, int life, String description, Location location) {
		super(name, description, location);
		this.bonusAtkPoint = atkpt;
		this.numberOfUse = life;
	}

	
	// ----------------------------GET & SET-------------------------------------//
	public int getBonusAttackPoint() {
		return bonusAtkPoint;
	}
	
	public int getNumberOfUse() {
		return numberOfUse;
	}
	
	public void lessNumberOfUse(int down) {
		this.numberOfUse -= down;
	}

	
	// --------------------------OVERRIDE------------------------------------//
	@Override
	public boolean use() {
		if (numberOfUse==0) {
			return false;
		}
		else {
			this.lessNumberOfUse(1);
			//getHero().attack += this.bonusAtkPoint;
			return true;
		}
	}
}
package Core;

public abstract class Weapon extends Item implements Use{
	private int bonusAttackPoint;
	private int numberOfUse;

	
	public Weapon(String name, int atkpt, int life, String description) {
		super(name, description);
		this.setBonusAttackPoint(atkpt);
		this.numberOfUse = life;
	}


	public int getBonusAttackPoint() {
		return bonusAttackPoint;
	}

	public void setBonusAttackPoint(int bonusAttackPoint) {
		this.bonusAttackPoint = bonusAttackPoint;
	}
	
	
	@Override
	public boolean use() {
		if (numberOfUse==0) {
			return false;
		}
		else {
			this.numberOfUse-=1;
			return true;
		}
	}
}

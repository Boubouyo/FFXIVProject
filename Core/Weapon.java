package Core;

public abstract class Weapon extends Item implements Use{
	private int bonusAttackPoint;
	private int numberOfUse;
	
	public Weapon(String name, int atkpt, int life, String description) {
		super(name, description);
		this.setBonusAttackPoint(atkpt);
		this.numberOfUse = life;
	}
	

	@Override
	public boolean Use() {
		this.numberOfUse-=1;
		return true;
	}


	public int getBonusAttackPoint() {
		return bonusAttackPoint;
	}


	public void setBonusAttackPoint(int bonusAttackPoint) {
		this.bonusAttackPoint = bonusAttackPoint;
	}
	
}

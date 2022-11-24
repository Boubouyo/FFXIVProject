package Core;

public class Weapon extends Item implements Use{
	private int bonusAttackPoint;
	private int numberOfUse;
	
	public Weapon(int atkpt, int life) {
		this.bonusAttackPoint = atkpt;
		this.numberOfUse = life;
	}
	

	@Override
	public boolean Use() {
		this.numberOfUse-=1;
		
		
		return true;
	}
	
}

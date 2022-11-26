/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locations;

/**
 *
 * @author fetiveau
 */
//This Exit is open in one way and will open in the other way when the enemies in that room are all defeated
public class ExitEnemy extends Exit{
    
    //Location a needs to be the location the enemy is
    public ExitEnemy(Location a, Location b, boolean isOpenAtoB, boolean isOpenBtoA){
        super(a, b, isOpenAtoB, isOpenBtoA);
    }
    
    @Override
    public boolean ableToMoveThrough(Location locationFrom){
        if(!super.ableToMoveThrough(locationFrom)){
            if(locationFrom.getEnemyList().isEmpty()){
                this.open(locationFrom);
                return true;
            }
            else return false;
        }
        else return true;
    }
}

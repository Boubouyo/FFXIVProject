/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locations;

/**
 *
 * @author fetiveau
 */
//This Exit is closed by default and only open when an enemy is defeated
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

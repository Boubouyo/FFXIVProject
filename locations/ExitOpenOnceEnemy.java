/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locations;

import characters.Enemy;

/**
 *
 * @author fetiveau
 */
//This Exit is closed by default and only open when a particular enemy is defeated
public class ExitOpenOnceEnemy extends Exit{
    private final String enemyToDefeatName;
    
    //Location a needs to be the location the enemy is
    public ExitOpenOnceEnemy(Location a, Location b, String enemyToDefeat){
        super(a,b);
        this.enemyToDefeatName = enemyToDefeat;
        this.close();
    }
    
    @Override
    public boolean ableToMoveThrough(){
        //We're walking through the exit for the first time
        if(super.getLocationA().getEnemyByName(this.enemyToDefeatName) == null){
            this.open();
            return true;
        }
        else return super.ableToMoveThrough();
    }
    
}

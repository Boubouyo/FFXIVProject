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
public class ExitOpenOnceEnemy extends Exit{
    private final String enemyToDefeatName;
    
    
    public ExitOpenOnceEnemy(Location a, Location b, Enemy enemyToDefeat){
        super(a,b);
        this.enemyToDefeatName = enemyToDefeat.getName();
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

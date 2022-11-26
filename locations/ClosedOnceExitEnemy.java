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
public class ClosedOnceExitEnemy extends Exit{
    private boolean firstTime = true;
    private final String enemyToDefeatName;
    
    
    public ClosedOnceExitEnemy(Location a, Location b, Enemy enemyToDefeat){
        super(a,b);
        this.enemyToDefeatName = enemyToDefeat.getName();
    }
    
    @Override
    public boolean ableToMoveThrough(){
        //We're walking through the exit for the first time
        if(super.ableToMoveThrough() && this.firstTime){
            this.close();
            this.firstTime = false;
            return true;
        }
        else if(!super.ableToMoveThrough() && super.getLocationB().getEnemyByName(this.enemyToDefeatName) == null){
            this.open();
            return true;
        }
        else return super.ableToMoveThrough();
    }
}

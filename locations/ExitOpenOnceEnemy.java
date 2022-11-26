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
public class ExitOpenOnceEnemy extends Exit{
    
    //Location a needs to be the location the enemy is
    public ExitOpenOnceEnemy(Location a, Location b){
        super(a,b);
        this.close();
    }
    
    @Override
    public boolean ableToMoveThrough(){
        //We're walking through the exit for the first time
        if(super.getLocationA().getEnemyList().isEmpty()){
            this.open();
            return true;
        }
        else return super.ableToMoveThrough();
    }
    
}

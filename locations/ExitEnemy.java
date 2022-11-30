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
    
    /**
     * 
     * @param a > Location A
     * @param b > Location B
     * @param isOpenAtoB > False if the exit is closed by default between Location A to Location B else true
     * @param isOpenBtoA > False if the exit is closed by default between Location B to Location A else true
     */
    
    public ExitEnemy(Location a, Location b, boolean isOpenAtoB, boolean isOpenBtoA){
        super(a, b, isOpenAtoB, isOpenBtoA);
    }
    
    /**
     * 
     * @param locationFrom > The location from where you want to cross the exit.
     * @return true if locationFrom doesn't have any enemies or if the door is already open in that direction, else false.
     */
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

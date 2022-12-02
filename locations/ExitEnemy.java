/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locations;

/**
 * This Exit can only be crossed if all the enemies in the Location you are are all vainquished (by the Hero).
 * This Exit only link two Locations
 * 
 * @author fetiveau
 */

public class ExitEnemy extends Exit{
    // --------------------------CONSTRUCTEURS---------------------------------//
    /**
     * The constructor method of the class ExitEnemy
     * It creates an ExitEnemy between Location a and Location b and you can define if the exit from Location a to Location b is open or closed per default (and vice versa)
     * 
     * @param a Location A
     * @param b Location B
     * @param isOpenAtoB False if the exit is closed by default between Location A to Location B else true
     * @param isOpenBtoA False if the exit is closed by default between Location B to Location A else true
     */
    public ExitEnemy(Location a, Location b, boolean isOpenAtoB, boolean isOpenBtoA){
        super(a, b, isOpenAtoB, isOpenBtoA);
    }
    
    
	// --------------------------OVERRIDE------------------------------------//
    /**
     * A Method that will tell you if you're able to cross the exit from locationFrom to the other location that this ExitEnemy is linked to.
     * 
     * @param locationFrom The location from where you want to cross the exit.
     * @see locations.Exit#ableToMoveThrough(locations.Location) 
     * @return boolean: false if the door is closed and there's enemy in the Location that has the name locationFrom, else return true.
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

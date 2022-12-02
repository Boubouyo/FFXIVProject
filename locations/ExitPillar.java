/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locations;

import items.Item;
import items.Pillar;


/**
 * This Exit will open is direction A->B when all statuette are put on the right pillar in room A, and in direction B->A when the enemies in B are all defeated
 * This Exit only link two Locations
 * 
 * @author fetiveau
 */

public class ExitPillar extends Exit{
    // --------------------------CONSTRUCTEURS---------------------------------//
    /**
     * The constructor method of the class ExitPillar
     * It creates an ExitPillar between Location a and Location b, and the exit is closed in the two ways by default
     * 
     * @param a Location A
     * @param b Location B
     */
    public ExitPillar(Location a, Location b){
        super(a,b,false,false);
    }
    
    
	// --------------------------OVERRIDE------------------------------------//
    /**
     * A Method that will tell you if you're able to cross the exit from locationFrom to the other location that this ExitPilalr is linked to.
     * @param locationFrom The location from where you want to cross the exit.
     * @return a boolean which is true if the exit is already open or you're in Location B and there's no enemies left or you're in Location A and all pillars have the right statuette on them, else false.
     * @see locations.Exit#ableToMoveThrough(locations.Location) 
     */
    @Override
    public boolean ableToMoveThrough(Location locationFrom){
        //We need to check if the statuettes are all on the right pillars
        if(!super.ableToMoveThrough(locationFrom) && locationFrom.equals(super.getLocationA())){
            boolean rightStatuette = true;
            for(Item i : locationFrom.getRoomInventory()){
                if(i instanceof Pillar p){
                    if(!p.rightStatuetteonPillar()){
                        rightStatuette = false;
                    }
                }
                
            }
            if(rightStatuette){
                super.open(locationFrom);
            }
            return rightStatuette;
            
        }
        //We need to check if all enemies are defeated
        else if(!super.ableToMoveThrough(locationFrom) && locationFrom.equals(super.getLocationB())){
            if(locationFrom.getEnemyList().isEmpty()){
                this.open(locationFrom);
                return true;
            }
            else return false;
        }
        return true;
    }
}
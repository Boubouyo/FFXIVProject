/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locations;

import items.Item;
import items.Pillar;


/**
 *
 * @author fetiveau
 */
//This room will open is direction A->B when all statuette are put on the right pillar in room A, and in direction B->A when the enemies in B are all defeated
public class ExitPillar extends Exit{
    
    /**
     * 
     * @param a > location of the room containing the pillar.
     * @param b > location of the room containing the enemies.
     */
    public ExitPillar(Location a, Location b){
        super(a,b,false,false);
    }
    
    /**
     * 
     * @param locationFrom > The location from where you want to cross the exit.
     * @return a boolean which is true if the exit is already open or you're in Location B and there's no enemies left or you're in Location A and all pillars have the right statuette on them, else false.
     * @see Exit.
     */
    @Override
    public boolean ableToMoveThrough(Location locationFrom){
        //We need to check if the statuette are all on the right pillar
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
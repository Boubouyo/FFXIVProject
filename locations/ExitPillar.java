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
    
    //Location A is where the pillar are and location B is where the enemy are
    public ExitPillar(Location a, Location b){
        super(a,b,false,false);
    }
    
    @Override
    public boolean ableToMoveThrough(Location locationFrom){
        //We need to check if the statuette are all on the right pillar
        if(!super.ableToMoveThrough(locationFrom) && locationFrom.equals(super.getLocationA())){
            for(Item i : locationFrom.getRoomInventory()){
                if(i instanceof Pillar p){
                    if(!p.rightStatuetteonPillar()){
                        return false;
                    }
                }
                else
                	return false;
                
            }
            super.open(locationFrom);
            return true;
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
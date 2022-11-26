/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locations;

import items.Item;
import items.Pilier;


/**
 *
 * @author fetiveau
 */
//This Exit is open and when you cross it the first it's closed until a certain enemy is defeated
public class OpenOnceExitPillar extends Exit{
    private boolean firstTime = true;
    
    // location b is the locatoion where the enemy is
    public OpenOnceExitPillar(Location a, Location b){
        super(a,b);
        this.close();
    }
    
    @Override
    public boolean ableToMoveThrough(){
        //We're walking through the exit for the first time
        if(this.firstTime){
            boolean canOpen = true;
            for(Item i: super.getLocationA().getRoomInventory()){
                if(i instanceof Pilier pilier){
                    if(!pilier.rightStatuetteonPillar()){
                        canOpen = false;
                    }
                }
            }
            if(canOpen) this.firstTime = false;
            return canOpen;
        }
        
        return false;
    }
}

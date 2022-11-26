/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locations;

import items.Item;

/**
 *
 * @author fetiveau
 */
//This Exit is open and when you cross it the first it's closed until a certain item is taken
public class ClosedOnceExitItem extends Exit{
    private boolean firstTime = true;
    private final String itemToGetName;
    
    //Location B is the location where the item is
    public ClosedOnceExitItem(Location a, Location b, Item itemToGet){
        super(a,b);
        this.itemToGetName = itemToGet.getName();
    }
    
    @Override
    public boolean ableToMoveThrough(){
        //We're walking through the exit for the first time
        if(super.ableToMoveThrough() && this.firstTime){
            this.close();
            this.firstTime = false;
            return true;
        }
        else if(!super.ableToMoveThrough() && super.getLocationB().getEnemyByName(this.itemToGetName) == null){
            this.open();
            return true;
        }
        else return super.ableToMoveThrough();
    }
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locations;


/**
 *
 * @author fetiveau
 */
//This Exit is open in one way and will open in the other way when the item in that room is taken
public class ExitItem extends Exit{
    private final String itemToGetName;
    
    
    //The location of the item that blocks the door is on the side of the closed 
    public ExitItem(Location a, Location b, boolean isOpenAtoB, boolean isOpenBtoA, String itemToGet){
        super(a, b, isOpenAtoB, isOpenBtoA);
        this.itemToGetName = itemToGet;
    }
    
    @Override
    public boolean ableToMoveThrough(Location locationFrom){
        if(!super.ableToMoveThrough(locationFrom)){
            if(locationFrom.getItemFromString(itemToGetName) == null){
                this.open(locationFrom);
                return true;
            }
            else return false;
        }
        else return true;
    }
}


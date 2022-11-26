/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locations;


/**
 *
 * @author fetiveau
 */
//This Exit is open and when you cross it the first it's closed until a certain item is taken
public class ExitItem extends Exit{
    private final String itemToGetName;
    
    
    //The location of the item that blocks the door is on the side of the closed 
    public ExitItem(Location a, Location b, String itemToGet, boolean isOpenAtoB, boolean isOpenBtoA){
        super(a, b, isOpenAtoB, isOpenBtoA);
        this.itemToGetName = itemToGet;
    }
    
    public ExitItem(Location a, Location b, String itemToGet){
        this(a, b, itemToGet, true, true);
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


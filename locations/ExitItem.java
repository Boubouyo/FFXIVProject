/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locations;


/**
 * This Exit can only be crossed if the Item in the Location you are is taken (by the Hero).
 * This Exit only link two Locations
 * 
 * @author fetiveau
 */
public class ExitItem extends Exit{
	// ---------------------------ATTRIBUTS------------------------------------//
    private final String itemToGetName;
    
    // --------------------------CONSTRUCTEURS---------------------------------//
    /**
     * The constructor method of the class ExitItem
     * It creates an ExitEnemy between Location a and Location b and you can define if the exit from Location a to Location b is open or closed per default (and vice versa)
     * 
     * @param a Location A
     * @param b Location B
     * @param isOpenAtoB  False if the exit is closed by default between Location A to Location B else true
     * @param isOpenBtoA  False if the exit is closed by default between Location B to Location A else true
     * @param itemToGet  The name of the item that needs to be taken by the player in order to unlock the exit from the Location the item is to the other Location
     */
    public ExitItem(Location a, Location b, boolean isOpenAtoB, boolean isOpenBtoA, String itemToGet){
        super(a, b, isOpenAtoB, isOpenBtoA);
        this.itemToGetName = itemToGet;
    }
    
	// --------------------------OVERRIDE------------------------------------//
    /**
     * A Method that will tell you if you're able to cross the exit from locationFrom to the other location that this ExitItem is linked to.
     * 
     * @param locationFrom The location from where you want to cross the exit.
     * @see locations.Exit#ableToMoveThrough(locations.Location) 
     * @return boolean: false if the door is closed and there's the Item in the Location that has the name locationFrom, else return true.
     */
    
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


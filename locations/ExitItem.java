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
    
    
    /**
     * 
     * @param a > Location A
     * @param b > Location B
     * @param isOpenAtoB > False if the exit is closed by default between Location A to Location B else true
     * @param isOpenBtoA > False if the exit is closed by default between Location B to Location A else true
     * @param itemToGet > The name of the item that needs to be taken by the player in order to unlock the exit from the Location the item is to the other Location
     */
    public ExitItem(Location a, Location b, boolean isOpenAtoB, boolean isOpenBtoA, String itemToGet){
        super(a, b, isOpenAtoB, isOpenBtoA);
        this.itemToGetName = itemToGet;
    }
    /**
     * 
     * @param locationFrom > The location from where you want to cross the exit.
     * @return true if locationFrom doesn't have the itemToGet or if the exit is already open in that direction, else false.
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


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locations;

/**
 *
 * @author fetiveau
 */
public class Exit {
	// ---------------------------ATTRIBUTS------------------------------------//
    private boolean isOpen;
    private final Location locationA;
    private final Location locationB;
    
    
    // --------------------------CONSTRUCTEUR----------------------------------//
    public Exit(Location a, Location b){
        this.isOpen = true;
        this.locationA = a;
        this.locationB = b;
        //This part is to add the new exit to the two locations
        this.locationA.addExits(this);
        this.locationB.addExits(this);
    }
    
    
    // ---------------------------OPERATIONS-------------------------------------//
    //Tells if the exit is blocked or not
    public boolean ableToMoveThrough(){
        return this.isOpen;
    }
    
    
    // ----------------------------GET & SET-------------------------------------//
    public Location getLocationA(){
        return this.locationA;
    }
    
    public Location getLocationB(){
        return this.locationB;
    }
    
    //Getting the room with the name of that room
    public Location getLocation(String wantedLocation){
        if(wantedLocation != null){
            if(wantedLocation.equals(this.getLocationA().getName())){
                return this.getLocationA();
            }
            else if(wantedLocation.equals(this.getLocationB().getName())){
                return this.getLocationB();
            }
        }
        return null;
    }
    
    //Getting the other location 
    public Location getOtherLocation(String location){
        if(location != null){
            if(location.equals(this.getLocationA().getName())){
                return this.getLocationB();
            }
            else if(location.equals(this.getLocationB().getName())){
                return this.getLocationA();
            }
        }
        return null;
    }
}

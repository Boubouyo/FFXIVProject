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
    private boolean isOpenAtoB;
    private boolean isOpenBtoA;
    private final Location locationA;
    private final Location locationB;
    
    
    // --------------------------CONSTRUCTEUR----------------------------------//
    public Exit(Location a, Location b, boolean isOpenAtoB, boolean isOpenBtoA){
        this.isOpenAtoB = isOpenAtoB;
        this.isOpenBtoA= isOpenBtoA;
        this.locationA = a;
        this.locationB = b;
        //This part is to add the new exit to the two locations
        this.locationA.addExits(this);
        this.locationB.addExits(this);
    }
    public Exit(Location a, Location b){
        this(a,b,true,true);
    }
    
    
    // ---------------------------OPERATIONS-------------------------------------//
    //Tells if the exit is blocked or not
    public boolean ableToMoveThrough(Location locationFrom){
        if(locationFrom != null){
            if(locationFrom.equals(this.locationA)){
                if(!this.isOpenAtoB){
                    System.out.println("This exit seems to be locked.");
                }
                return this.isOpenAtoB;
            }
            else if(locationFrom.equals(this.locationB)){
                if(!this.isOpenBtoA){
                    System.out.println("This exit seems to be locked.");
                }
                return this.isOpenBtoA;
            }
            else{
                System.out.println("The place you're looking for doesn't exist.");
                return false;
            }
        }
        else{
            System.out.println("The place you're looking for doesn't exist.");
            return false;
        }
    }
    
    
    // ----------------------------GET & SET-------------------------------------//
    public Location getLocationA(){
        return this.locationA;
    }
    
    public Location getLocationB(){
        return this.locationB;
    }
    
    //Close the door in the direction from locationFrom to the other location
    public void close(Location locationFrom){
        if(this.getLocationA().equals(locationFrom)){
            this.isOpenAtoB = false;
        }
        else if(this.getLocationB().equals(locationFrom)){
            this.isOpenBtoA = false;
        }
    }
    
    //Open the door in the direction from locationFrom to the other location
    public void open(Location locationFrom){
        if(this.getLocationA().equals(locationFrom)){
            this.isOpenAtoB = true;
        }
        else if(this.getLocationB().equals(locationFrom)){
            this.isOpenBtoA = true;
        }
    }
    
    //Getting the room with the name of that room
    public Location getLocation(String wantedLocation){
        if(wantedLocation != null){
            if(wantedLocation.equalsIgnoreCase(this.getLocationA().getName())){
                return this.getLocationA();
            }
            else if(wantedLocation.equalsIgnoreCase(this.getLocationB().getName())){
                return this.getLocationB();
            }
        }
        return null;
    }
    
    //Getting the other location 
    public Location getOtherLocation(String location){
        if(location != null){
            if(location.equalsIgnoreCase(this.getLocationA().getName())){
                return this.getLocationB();
            }
            else if(location.equalsIgnoreCase(this.getLocationB().getName())){
                return this.getLocationA();
            }
        }
        return null;
    }
}

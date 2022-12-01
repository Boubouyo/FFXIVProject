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
    /**
     * 
     * @param a > Location A
     * @param b > Location B
     * @param isOpenAtoB > False if the exit is closed by default between Location A to Location B else true.
     * @param isOpenBtoA > False if the exit is closed by default between Location B to Location A else true.
     */
    public Exit(Location a, Location b, boolean isOpenAtoB, boolean isOpenBtoA){
        this.isOpenAtoB = isOpenAtoB;
        this.isOpenBtoA= isOpenBtoA;
        this.locationA = a;
        this.locationB = b;
        //This part is to add the new exit to the two locations
        this.locationA.addExits(this);
        this.locationB.addExits(this);
    }
    /**
     * 
     * @param a > Location A
     * @param b > Location B
     * 
     * @see The Exit is open by default in the two ways.
     */
    public Exit(Location a, Location b){
        this(a,b,true,true);
    }
    
    
    // ----------------------------GET & SET-------------------------------------//
    /**
     * 
     * @return Location A
     */
    public Location getLocationA(){
        return this.locationA;
    }
    /**
     * 
     * @return Location B 
     */
    public Location getLocationB(){
        return this.locationB;
    }
    
    
    // ---------------------------OPERATIONS-------------------------------------//
    /**
     * 
     * @param locationFrom > The location from where you want to cross the exit.
     * @return a boolean which is true if isOpen LocationFrom -> LocationTo is true, else false.
     */
    public boolean ableToMoveThrough(Location locationFrom){
        if(locationFrom != null){
            if(locationFrom.equals(this.locationA)){
                return this.isOpenAtoB;
            }
            else if(locationFrom.equals(this.locationB)){
                return this.isOpenBtoA;
            }
        }
            return false;
    }
    
    
    /**
     * 
     * @param locationFrom > the IsOpen LocationFrom -> OtherLocation will be set to false.
     */
    public void close(Location locationFrom){
        if(this.getLocationA().equals(locationFrom)){
            this.isOpenAtoB = false;
        }
        else if(this.getLocationB().equals(locationFrom)){
            this.isOpenBtoA = false;
        }
    }
    /**
     * 
     * @param locationFrom > the IsOpen LocationFrom -> OtherLocation will be set to true.
     */
    public void open(Location locationFrom){
        if(this.getLocationA().equals(locationFrom)){
            this.isOpenAtoB = true;
        }
        else if(this.getLocationB().equals(locationFrom)){
            this.isOpenBtoA = true;
        }
    }
    /**
     * 
     * @param wantedLocation > The name of the location you want to get
     * @return the Location that has the name of wantedLocation if it's link to that Exit
     */
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
    
    /**
     * 
     * @param location > The name of a Location
     * @return If location is the name of one of the two Locations (A or B) that the Exit contains, it will return the other locations, else it will return null.
     */
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

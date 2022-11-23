/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author fetiveau
 */
public class Location {
    private final String name;
    private final String description = "This is just an empty room";
    private Map<String, Exit> exits = new HashMap<>();
    
    
    public Location(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public Location takeExit(String locationName){
	if(this.exits.containsKey(locationName)){
            if(this.exits.get(locationName).ableToMoveThrough())
                return this.exits.get(locationName).getIngoingRoom(locationName);
        }
        else{
            return this;
        }
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author fetiveau
 */
public class Location implements Look{
    private final String name;
    private final String description = "This is just an empty room";
    private final Map<String, Exit> exits = new HashMap<>();
    private final List<Character> characters = new ArrayList<>();
    private final List<Item> items = new ArrayList<>();
    
    
    public Location(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    
   //Exit management methods
    
    public void addExits(Exit newExit){
        exits.put(newExit.getOtherLocation(this.name).getName(), newExit);
    }
    
    public Location takeExit(String locationName){
        Location returnedLoc = this;
	if(this.exits.containsKey(locationName)){
            if(this.exits.get(locationName).ableToMoveThrough())
                returnedLoc = this.exits.get(locationName).getLocation(locationName);
        }
        return returnedLoc;
    }
    
    //For the LOOK command
    @Override
    public void look(){
        System.out.println(this.description);
    }
    
    //Je sais pas trop faut voir plus tard
    public void addCharacters(Character newChar){
        this.characters.add(newChar);
    }
    
    public void addItems(Item newItem){
        this.items.add(newItem);
    }
}
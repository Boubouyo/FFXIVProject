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
    private Hero myHero = null;
    
    
    public Location(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public Hero getHero(){
        return this.myHero;
    }
    
    
   //Exit management methods
    
    public void addExits(Exit newExit){
        exits.put(newExit.getOtherLocation(this.name).getName(), newExit);
    }
    
    public Location takeExit(String locationName, Hero hero){
        Location returnedLoc = this;
        if(this.exits.containsKey(locationName)){
            if(this.exits.get(locationName).ableToMoveThrough()){
                returnedLoc = this.exits.get(locationName).getLocation(locationName);
                this.myHero = null;
                returnedLoc.myHero = hero;
            }
        }
        return returnedLoc;
    }
    
    
    //For the LOOK command
    @Override
    public void look(){
        System.out.println(this.description);
    }
    
    //everything about the characters in a room
    public void addCharacters(Character newChar){
        this.characters.add(newChar);
    }
    
    public void deleteCharacters(Character deletedChar){
        characters.remove(deletedChar);
    }
    
    public Character getEnemyByName(String name){
        for(Character c : characters){
            if(c.getName().equals(name)){
                return c;
            }
        }
        return null;
    }
    
    
    //Everything about the items in a room
    
    public Item getItemFromString(String name){
        for(Item i : items){
            if(i.getName().equals(name)){
                return i;
            }
        }
        return null;
    }
    
    public void addItems(Item newItem){
        this.items.add(newItem);
    }
}
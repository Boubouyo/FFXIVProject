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
    private final String description;
    private final String entryDescription;
    private final Map<String, Exit> exits = new HashMap<>();
    private final List<Enemy> enemies = new ArrayList<>();
    private final List<Item> items = new ArrayList<>();
    private Hero myHero = null;
    
    
    public Location(String name,String description, String entryDescription){
        this.entryDescription = entryDescription;
        this.name = name;
        this.description = description;
    }
    
    public Location(String name,String description){
        this.entryDescription = description;
        this.name = name;
        this.description = description;
    }
    
    public String getName(){
        return this.name;
    }
    
    public Hero getHero(){
        return this.myHero;
    }
    
    public void setHero(Hero hero){
        this.myHero = hero;
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
                returnedLoc.setHero(hero);
            }
        }
        return returnedLoc;
    }
    
    public void entryPrint(){
        System.out.println(this.entryDescription);
    }
    
    //For the LOOK command
    @Override
    public void look(){
        System.out.println(this.description);
        for(String s: exits.keySet()){
            System.out.println(s);
        }
    }
    
    //everything about the characters in a room
    public void addEnemy(String name, int healthPoints, int attack, String description){
        if(description == null){
            this.enemies.add(new Enemy(name, healthPoints, attack , this));
        }
        else this.enemies.add(new Enemy(name, healthPoints, attack, this, description));
    }
    
    public void deleteEnemy(Enemy deletedEnemy){
        enemies.remove(deletedEnemy);
    }
    
    public Enemy getEnemyByName(String name){
        for(Enemy e : enemies){
            if(e.getName().equals(name)){
                return e;
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
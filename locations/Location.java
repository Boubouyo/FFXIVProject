/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locations;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;


import characters.Hero;
import commands.Look;
import characters.Enemy;
import items.Item;
import items.Statuette;
import items.Pilier;
import items.Stick;


/**
 *
 * @author fetiveau
 */
public class Location implements Look {
    // ---------------------------ATTRIBUTS------------------------------------//
    private final String name;
    private final String description;
    private final String entryDescription;
    private final Map<String, Exit> exits = new HashMap<>();
    private final List<Enemy> enemies = new ArrayList<>();
    private final List<Item> items = new ArrayList<>();
    private Hero myHero = null;
    private boolean firstTimeInLocation = true;
    
    
    // --------------------------CONSTRUCTEURS---------------------------------//
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
    
    
    // ----------------------------GET & SET-------------------------------------//
    public String getName(){
        return this.name;
    }
    
    public Hero getHero(){
        return this.myHero;
    }
    
    public void setHero(Hero hero){
    	entryPrint();
        this.myHero = hero;
    }
    
    
    // ---------------------------OPERATIONS : EXIT---------------------------------//
    
    private Exit getExitFromLocationName(String locationName)
    {
    	for (String key : this.exits.keySet()) {
			if (key.equalsIgnoreCase(locationName))
				return this.exits.get(key);
		}
    	return null;
    }
    
   //Exit management methods
    public void addExits(Exit newExit){
        exits.put(newExit.getOtherLocation(this.name).getName(), newExit);
    }
    
    public Location takeExit(String locationName, Hero hero){
        Location returnedLoc = this;
        Exit currentExit = getExitFromLocationName(locationName);
        if(currentExit != null){
            if(currentExit.ableToMoveThrough()){
                returnedLoc = currentExit.getLocation(locationName);
                this.myHero = null;
                returnedLoc.setHero(hero);
            }
        }
        return returnedLoc;
    }
    
    // ---------------------------OPERATIONS : PRINT---------------------------------//
    public void entryPrint(){
    	System.out.println("You're entering the " + this.name + ".");
    	if (firstTimeInLocation)
    	{
    		System.out.println(this.entryDescription);
    		firstTimeInLocation = false;
    	}
    	printEnemies();
    }
    
    public void printExits()
    {
    	System.out.print("You can go to :");
        for(String s: exits.keySet()){
            System.out.print(" " + s);
        }
        System.out.println(".");
    }
    
    public void printEnemies()
    {
    	if (!enemies.isEmpty())
        {
	        System.out.print("Oh no !");
	        for(Enemy e: enemies){
	            System.out.print(" There is a " + e.getName() + " !");
	        }
	        System.out.println("");
        }
    }
    
    
    // ---------------------OPERATIONS : ENEMY------------------------------//
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
            if(e.getName().equalsIgnoreCase(name)){
                return e;
            }
        }
        return null;
    }
    
    public void allEnemiesAttack(){
        for(Enemy e : enemies){
            e.attackHero();
        }
    }
    
    
    // ---------------------------OPERATIONS : ITEMS---------------------------------//
    //Everything about the items in a room
    public List<Item> getRoomInventory(){
        return this.items;
    }
    
    public Item getItemFromString(String name){
        for(Item i : items){
            if(i.getName().equalsIgnoreCase(name)){
                return i;
            }
        }
        return null;
    }
    
    public void removeItem(Item removedItem){
        items.remove(removedItem);
    }
    
    //Add items section
    
    public void addStatuette(String name, int id, String description){
        this.items.add(new Statuette(name, id, description, this));
    }
    
    public void addPilier(String name, int id, String description){
        this.items.add(new Pilier(name, id, description, this));
    }
    
    public void addStick(){
        this.items.add(new Stick("", "", this));
    }
    
    
    
	// --------------------------OVERRIDE------------------------------------//
    //For the LOOK command
    @Override
    public void look(){
        System.out.println(this.description);       
        printExits();        
        printEnemies();
    }
}
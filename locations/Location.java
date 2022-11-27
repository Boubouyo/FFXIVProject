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
import items.Button;
import items.EnigmaDevice;
import items.HealingItem;
import items.Item;
import items.Statuette;
import items.Pillar;
import items.Weapon;


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
    	this.myHero = hero;
    	entryPrint();        
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
            if(currentExit.ableToMoveThrough(hero.getCurrentLocation())){
                returnedLoc = currentExit.getLocation(locationName);
                this.myHero = null;
                returnedLoc.setHero(hero);
            }
            else
            	System.out.println("This exit seems to be locked."); 
        }
        else
        	System.out.println("The place you're looking for doesn't exist.");
        return returnedLoc;
    }
    
    // ---------------------------OPERATIONS : PRINT---------------------------------//
    public void entryPrint(){
    	System.out.println("You're entering the " + this.name + ".");
    	// Make the game end
    	if (this.entryDescription.equals("THEEND"))
    	{    		
    		System.out.println(this.description);
    		this.myHero.gameFinished();
    		return;
    	}
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
    
    public void printItems() 
    {
    	if (!items.isEmpty())
        {
    		System.out.print("Looking around you find :");
	        for(Item i: items){
	            System.out.print(" " + i.getName());
	        }
	        System.out.println(".");
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
    
    public List<Enemy> getEnemyList(){
        return this.enemies;
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
    
    public void addStatuette(String name, int id, String description, boolean isPickable){
        this.items.add(new Statuette(name, id, description, this, isPickable));
    }
    
    public void addEnigmaDevice(String name, String description, Location location, String descriptionResolved, String descriptionAfterResolved, String[] buttonsName, String[] buttonsDescription, String[] buttonsDescriptionResolved, String itemToGive, int[] correctSequence){
        this.items.add(new EnigmaDevice(name, description, location, descriptionResolved, descriptionAfterResolved, buttonsName, buttonsDescription, buttonsDescriptionResolved, itemToGive, correctSequence));
    }
    
    public Button addButton(String name, String description, Location location, String descriptionResolved, EnigmaDevice enigmaDevice, int id)
    {
    	Button b = new Button(name, description, location, descriptionResolved, enigmaDevice, id);
    	this.items.add(b);
    	return b;
    }
    
    public void addHealingItem(String name, String description, Location location, Boolean isPickable, int healPower)
    {
    	this.items.add(new HealingItem(name, description, location, isPickable, healPower));
    }
    
    public void addWeapon(String name, int atkpt, int life, String description, Location location, boolean isPickable){
        this.items.add(new Weapon(name, atkpt, life, description, location, isPickable));
    }
    
    public void addPillar(String name, int id, String description, Location location){
        this.items.add(new Pillar(name, id, description, location));
    }
    
    
    
    
    
	// --------------------------OVERRIDE------------------------------------//
    //For the LOOK command
    @Override
    public void look(){
        System.out.println(this.description);   
        printItems();
        printExits();   
        printEnemies();
    }
}
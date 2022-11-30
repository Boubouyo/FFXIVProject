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
    /**
     * 
     * @param name > Name of the Location
     * @param description > description of the Location when you use the look command
     * @param entryDescription > description of the Location when you enter the Location
     */
    public Location(String name,String description, String entryDescription){
        this.entryDescription = entryDescription;
        this.name = name;
        this.description = description;
    }
    
    
    // ----------------------------GET & SET-------------------------------------//
    /**
     * 
     * @return The name of the Location
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * 
     * @return The Hero if the hero is in that Location, else null
     */
    public Hero getHero(){
        return this.myHero;
    }
    
    /**
     * 
     * @param hero > The hero that will be in that Location, will also print the description of when you enter the room
     */
    public void setHero(Hero hero){
    	this.myHero = hero;
    	entryPrint();        
    }
    
    
    // ---------------------------OPERATIONS : EXIT---------------------------------//
    
    /**
     * 
     * @param locationName > The name of a Location
     * @return If this Location as an exit in common with the Location that has the locationName, will send the Exit that link the two
     */
    private Exit getExitFromLocationName(String locationName)
    {
    	for (String key : this.exits.keySet()) {
			if (key.equalsIgnoreCase(locationName))
				return this.exits.get(key);
		}
    	return null;
    }
    
    /**
     * 
     * @param newExit > An Exit you want to link to that Location
     */
    public void addExits(Exit newExit){
        exits.put(newExit.getOtherLocation(this.name).getName(), newExit);
    }
    
    /**
     * 
     * @param locationName > Name of the location you want the hero to go
     * @param hero > the hero of the game
     * @return if the hero is able to go to locationName from where they currently are, returns that Location, else returns the Location the hero currently is
     */
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
    /**
     * Print the Location entry message
     */
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
    
    /**
     * Print the all the Exits where you can go from that Location
     */
    public void printExits()
    {
    	System.out.print("You can go to :");
        for(String s: exits.keySet()){
            if(exits.get(s).ableToMoveThrough(this)){
                System.out.print(" " + s);
            }
        }
        System.out.println(".");
    }
    
    /**
     * Print the enemies in the Location
     */
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
    
    /**
     * Print the Items that are in the Location
     */
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
    /**
     * 
     * @param name > Name of the enemy you wanna add
     * @param healthPoints > Health of the enemy you wanna add
     * @param attack > Attack damage of the enemy you wanna add
     * @param description > Description of the enemy you wanna add
     */
    public void addEnemy(String name, int healthPoints, int attack, String description){
        if(description == null){
            this.enemies.add(new Enemy(name, healthPoints, attack , this));
        }
        else this.enemies.add(new Enemy(name, healthPoints, attack, this, description));
    }
    
    /**
     * 
     * @param deletedEnemy > The enemy you want to remove from the Location
     */
    public void deleteEnemy(Enemy deletedEnemy){
        enemies.remove(deletedEnemy);
    }
    
    /**
     * 
     * @param name > Name of an enemy
     * @return The first enemy in that Location that has that name
     */
    public Enemy getEnemyByName(String name){
        for(Enemy e : enemies){
            if(e.getName().equalsIgnoreCase(name)){
                return e;
            }
        }
        return null;
    }
    
    /**
     * Make all enemies in the Location attack the hero
     */
    public void allEnemiesAttack(){
        for(Enemy e : enemies){
            e.attackHero();
        }
    }
    
    /**
     * 
     * @return The list of enemies in the Location
     */
    public List<Enemy> getEnemyList(){
        return this.enemies;
    }
    
    // ---------------------------OPERATIONS : ITEMS---------------------------------//
    /**
     * 
     * @return The List of Items that are in the room
     */
    public List<Item> getRoomInventory(){
        return this.items;
    }
    
    /**
     * 
     * @param name > Name of an Item
     * @return the Item from the Location that has that name, if there's none, return null
     */
    public Item getItemFromString(String name){
        for(Item i : items){
            if(i.getName().equalsIgnoreCase(name)){
                return i;
            }
        }
        return null;
    }
    
    /**
     * 
     * @param item > the Item you want to add to the Location
     */
    public void addItem(Item item){
        items.add(item);
    }
    /**
     * 
     * @param removedItem The Item you want to remove from the Location
     */
    public void removeItem(Item removedItem){
        items.remove(removedItem);
    }
    
    /**
     * 
     * @param name > Name of the Statuette
     * @param id > id of the Statuette
     * @param description > description of the Statuette
     * @param isPickable > setting if the Statuette can be picked by the hero or not per default.
     */
    public void addStatuette(String name, int id, String description, boolean isPickable){
        this.items.add(new Statuette(name, id, description, this, isPickable));
    }
    /**
     * 
     * @param name > name of the EnigmaDevice
     * @param description > description of the EnigmaDevice
     * @param descriptionResolved > description that will be display when you resolve the enigma of the EnigmaDevice
     * @param descriptionAfterResolved > description that will be display after you've resolved the enigma of the EnigmaDevice
     * @param buttonsName > String[] containing the name of each Button of the EnigmaDevice
     * @param buttonsDescription > String[] containing the description of each Button of the EnigmaDevice
     * @param buttonsDescriptionResolved > String[] containing the description of each Button after you've resolved the enigma of the EnigmaDevice
     * @param itemToGive > the name of the Item you want to make pickable after the enigma of the EnigmaDevice is solved
     * @param correctSequence > int[] containing the order in which the button has to be pressed to resolve the enugma of the EnigmaDevice
     */
    public void addEnigmaDevice(String name, String description, String descriptionResolved, String descriptionAfterResolved, String[] buttonsName, String[] buttonsDescription, String[] buttonsDescriptionResolved, String itemToGive, int[] correctSequence){
        this.items.add(new EnigmaDevice(name, description, this, descriptionResolved, descriptionAfterResolved, buttonsName, buttonsDescription, buttonsDescriptionResolved, itemToGive, correctSequence));
    }
    
    /**
     * 
     * @param name > name of the Button
     * @param description > description of the Button before the enigma has been resolved
     * @param descriptionResolved > description of the Button once the enigma has been resolved
     * @param enigmaDevice > the EnigmaDevice that his link to that Button
     * @param id > the id of that button
     * @return The Button with the parameters setted.
     */
    public Button addButton(String name, String description, String descriptionResolved, EnigmaDevice enigmaDevice, int id)
    {
    	Button b = new Button(name, description, this, descriptionResolved, enigmaDevice, id);
    	this.items.add(b);
    	return b;
    }
    
    /**
     * 
     * @param name > name of the HealingItem
     * @param description > description of the HealingItem
     * @param isPickable > define if the Item can be taken by the Hero per default
     * @param healPower > the amount of life that it restores
     */
    public void addHealingItem(String name, String description, boolean isPickable, int healPower)
    {
    	this.items.add(new HealingItem(name, description,this, isPickable, healPower));
    }
    
    /**
     * 
     * @param name > name of the Weapon
     * @param atkpt > Damage of the Weapon
     * @param life > number of time you can use the Weapon
     * @param description > description of the Weapon
     * @param isPickable > define if the Item can be taken by the Hero per default
     */
    public void addWeapon(String name, int atkpt, int life, String description, boolean isPickable){
        this.items.add(new Weapon(name, atkpt, life, description, this, isPickable));
    }
    /**
     * 
     * @param name > name of the Pillar
     * @param id > id of the Pillar (which needs to be the same as one of the Statuette ids)
     * @param description > description of the Pillar
     */
    public void addPillar(String name, int id, String description){
        this.items.add(new Pillar(name, id, description, this));
    }
    
    
    
    
    
	// --------------------------OVERRIDE------------------------------------//
    /**
     * Implementation of the look interface
     */
    @Override
    public void look(){
        System.out.println(this.description);   
        printItems();
        printExits();   
        printEnemies();
    }
}
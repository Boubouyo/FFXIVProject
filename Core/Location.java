/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core;

/**
 *
 * @author fetiveau
 */
public class Location {
    private final String name;
    
    
    public Location(String name){
        this.name = name;
    }
    
    public Location takeExit(String exitName){
	return this;
    }
}

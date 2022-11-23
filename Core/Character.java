/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core;

/**
 *
 * @author fetiveau
 */
public abstract class Character 
{
	protected final String name;
	
	protected int healthPoints;
	
	public Character (String name, int healthPoints)
	{
		this.name = name;
		this.healthPoints = healthPoints;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getHealthPoints()
	{
		return this.healthPoints;
	}
}


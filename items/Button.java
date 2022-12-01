package items;

import commands.Use;
import locations.Location;

/**
 * An item class which is a button that can be used via the commands. It is created by an enigma device.
 * 
 * @author Victor
 */

public class Button extends Item implements Use {
	// ---------------------------ATTRIBUTS------------------------------------//
	private final int buttonId;
	private final EnigmaDevice enigmaDevice;
	private final String descriptionResolved;
	
	private boolean isResolved;
	
	// --------------------------CONSTRUCTEUR----------------------------------//
	/**
     * Constructor method for the class Button.
     * @param name (String) : the name of the item (each item as a different one)
	 * @param description (String) : the description of the item (what we notice by using the command look)
	 * @param location (Location) : where the item is located
	 * @param descriptionResolved (String) : the description of the item once the enigma is resolved
	 * @param id (int) : the id of the button to be able to check which button is pressed
     */
	public Button(String name, String description, Location location, String descriptionResolved, EnigmaDevice enigmaDevice, int id) 
	{
		super(name, description, location);
		this.descriptionResolved = descriptionResolved;
		this.enigmaDevice = enigmaDevice;
		this.buttonId = id;
		this.isResolved = false;
	}

	
	// ---------------------------OPERATIONS-----------------------------------//
	/**
     * Change the description of the button to descriptionResolved
     */
	private void changeDescription()
	{
		super.setDescription(descriptionResolved);
	}
	
	/**
     * Do all actions that the button must do once the enigma is resolved
     */
	public void resolved()
	{
		this.changeDescription();
        this.isResolved = true;
	}
	
	
	// --------------------------OVERRIDE------------------------------------//
	/**
     * Implementation of the use method to push the button
     * @return boolean : returns true unless the enigma is already resolved
     */
    @Override
	public boolean use()
	{
		if (!isResolved)
		{
			System.out.println("You use the " + getName() + ". Something seems to have happened.");
			this.enigmaDevice.pushButton(this.buttonId);
			return true;
		}
		return false;
	}
	
}

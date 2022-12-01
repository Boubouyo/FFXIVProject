package items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import locations.Location;

/**
 * @author Victor
 */

public class EnigmaDevice extends Item {
	// ---------------------------ATTRIBUTS------------------------------------//
	private final String descriptionResolved;
	private final String descriptionAfterResolved;
	private final List<Button> buttons;
	
	private final String itemToGive;
	private final int[] correctSequence;
	
	private int tryingSequenceIndex;
	private final int[] tryingSequence;
	
    // --------------------------CONSTRUCTEUR----------------------------------//
	/**
     * Constructor method for the class Button.
     * @param name (String) : the name of the item (each item as a different one)
	 * @param description (String) : the description of the item (what we notice by using the command look)
	 * @param location (Location) : where the item is located
	 * @param descriptionResolved (String) : the description of the item to print the moment it is resolved
	 * @param descriptionAfterResolved (String) : the description of the item to print after it is resolved
	 * @param buttonsName (String[]) : the array of all the buttons' name
	 * @param buttonsDescription (String[]) : the array of all the buttons' description
	 * @param buttonsDescriptionResolved (String[]) : the array of all the buttons' description after it is resolved
	 * @param itemToGive (String) : the name of the item you get for resolving the enigma
	 * @param correctSequence (int[]) : the array with the id of the correct sequence of button to use to resolve the enigma
     */
	public EnigmaDevice(String name, String description, Location location, String descriptionResolved, String descriptionAfterResolved, 
			String[] buttonsName, String[] buttonsDescription, String[] buttonsDescriptionResolved, String itemToGive, int[] correctSequence) 
	{
		super(name, description, location);
		this.descriptionResolved = descriptionResolved;
		this.descriptionAfterResolved = descriptionAfterResolved;
		this.buttons = makeButtons(buttonsName, buttonsDescription, buttonsDescriptionResolved);
		this.itemToGive = itemToGive;
		this.correctSequence = correctSequence;
		this.tryingSequenceIndex = 0;
		this.tryingSequence = new int[correctSequence.length];
	}
	
	
	// ---------------------------OPERATIONS-----------------------------------//
	/**
     * Getter for the attribute tryingSequence
     * 
     * @return int[] : the value of the tryingSequence attribute
     */
	public int[] getTryingSequence(){
		return this.tryingSequence;
	}

	/**
     * Creates all the buttons with the correct values
     * @param buttonsName (String[]) : the array of all the buttons' name
	 * @param buttonsDescription (String[]) : the array of all the buttons' description
	 * @param buttonsDescriptionResolved (String[]) : the array of all the buttons' description after it is resolved 
     * @return List<Button> : the list of buttons created
     */
	private List<Button> makeButtons(String[] buttonsName, String[] buttonsDescription, String[] buttonsDescriptionResolved)
	{
		List<Button> newButtons = new ArrayList<>();
			
		for (int i = 0; i < buttonsName.length; i++) 
		{
			Button newB = this.getLocation().addButton(buttonsName[i], buttonsDescription[i], buttonsDescriptionResolved[i], this, i);
			newButtons.add(newB);
		}
		
		return newButtons;
	}

	/**
     * Change the description of the enigma device to descriptionAfterResolved
     */
	private void changeDescription()
	{
		super.setDescription(descriptionAfterResolved);
	}
	
	/**
     * Reaction to a button being pushed : storing the button id to monitor the sequence of buttons pressed and test if the sequence is correct 
     * @param buttonId (int) : the id of the button being pushed
     */
	public void pushButton(int buttonId)
	{
		this.tryingSequence[this.tryingSequenceIndex] = buttonId;
		this.tryingSequenceIndex++;
		
		if (this.tryingSequenceIndex >= this.correctSequence.length)
			this.verifyResolved();
	}
	
	/**
     * Check if the enigma is solved and act accordingly
     */
	public void verifyResolved()
	{
		if (Arrays.equals(this.correctSequence, this.tryingSequence))
		{
			this.changeDescription();
			for (Button button : this.buttons) {
				button.resolved();
			}
			
			Item item = getLocation().getItemFromString(this.itemToGive);
			if (item instanceof Pickable itemPickable)
			{
				System.out.println(this.descriptionResolved);
				itemPickable.becomePickable();
			}
			else 
				System.out.println("ERROR in EnigmaDevice " + getName() + ". The item " + this.itemToGive + "can't be picked.");
		}
		else
		{
			System.out.println("It seems like you've made an error... Try again.");
			this.tryingSequenceIndex = 0;			
		}
	}
	
}

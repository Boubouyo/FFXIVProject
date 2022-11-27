package items;

import java.util.ArrayList;
import java.util.List;

import locations.Location;

public class EnigmaDevice extends Item {

	private final String descriptionResolved;
	private final String descriptionAfterResolved;
	private final List<Button> buttons;
	
	private final String itemToGive;
	private final int[] correctSequence;
	
	private int tryingSequenceIndex;
	private int[] tryingSequence;
	
	public EnigmaDevice(String name, String description, Location location, String descriptionResolved, String descriptionAfterResolved, String[] buttonsName, String[] buttonsDescription, String[] buttonsDescriptionResolved, String itemToGive, int[] correctSequence) 
	{
		super(name, description, location);
		this.descriptionResolved = descriptionResolved;
		this.descriptionAfterResolved = descriptionAfterResolved;
		this.buttons = makeButtons(location, buttonsName, buttonsDescription, buttonsDescriptionResolved);
		this.itemToGive = itemToGive;
		this.correctSequence = correctSequence;
		this.tryingSequenceIndex = 0;
		this.tryingSequence = new int[correctSequence.length];
	}
	
	// Creates all the buttons
	private List<Button> makeButtons(Location location, String[] buttonsName, String[] buttonsDescription, String[] buttonsDescriptionResolved)
	{
		List<Button> newButtons = new ArrayList<>();
			
		for (int i = 0; i < buttonsName.length; i++) 
		{
			Button newB = getLocation().addButton(buttonsName[i], buttonsDescription[i], buttonsDescriptionResolved[i], this, i);
			newButtons.add(newB);
		}
		
		return newButtons;
	}

	private void changeDescription()
	{
		setDescription(descriptionAfterResolved);
	}
	
	// Reaction to a button being pushed
	public void pushButton(int buttonId)
	{
		this.tryingSequence[this.tryingSequenceIndex] = buttonId;
		this.tryingSequenceIndex++;
		
		if (this.tryingSequenceIndex >= this.correctSequence.length)
			verifyResolved();
	}
	
	//  To check if 2 arrays are equals
	private boolean areEqualsIntArray(int[] array1, int[] array2)
	{
		for (int i = 0; i < array1.length; i++) 
		{
			if (array1[i] != array2[i])
				return false;
		}
		return true;
	}
	
	// To see if the enigma is solved and act accordingly
	public void verifyResolved()
	{
		if (areEqualsIntArray(this.correctSequence, this.tryingSequence))
		{
			changeDescription();
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
			System.out.println("But nothing happens.");
			this.tryingSequenceIndex = 0;			
		}
	}
	
}

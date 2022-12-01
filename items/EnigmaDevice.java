package items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import locations.Location;

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
	public EnigmaDevice(String name, String description, Location location, String descriptionResolved, String descriptionAfterResolved, String[] buttonsName, String[] buttonsDescription, String[] buttonsDescriptionResolved, String itemToGive, int[] correctSequence) 
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
	public int[] getTryingSequence(){
		return this.tryingSequence;
	}
	
	// Creates all the buttons
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

	private void changeDescription()
	{
		super.setDescription(descriptionAfterResolved);
	}
	
	// Reaction to a button being pushed
	public void pushButton(int buttonId)
	{
		this.tryingSequence[this.tryingSequenceIndex] = buttonId;
		this.tryingSequenceIndex++;
		
		if (this.tryingSequenceIndex >= this.correctSequence.length)
			this.verifyResolved();
	}
	
	// To see if the enigma is solved and act accordingly
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

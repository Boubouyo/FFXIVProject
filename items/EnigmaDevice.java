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
	
	public EnigmaDevice(String name, String description, Location location, String descriptionResolved, String descriptionAfterResolved, List<String> buttonsName, List<String> buttonsDescription, List<String> buttonsDescriptionResolved, String itemToGive, int[] correctSequence) 
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
	
	private List<Button> makeButtons(Location location, List<String> buttonsName, List<String> buttonsDescription, List<String> buttonsDescriptionResolved)
	{
		List<Button> newButtons = new ArrayList<>();
		
		int i = 0;
		for (String buttonName : buttonsName) {
			Button newB = new Button(buttonName, buttonsDescription.get(i), location, buttonsDescriptionResolved.get(i), this, i);
			newButtons.add(newB);
			i++;
		}
		
		return newButtons;
	}

	private void changeDescription()
	{
		setDescription(descriptionAfterResolved);
	}
	
	public void pushButton(int buttonId)
	{
		tryingSequence[tryingSequenceIndex] = buttonId;
		tryingSequenceIndex++;
		
		if (tryingSequenceIndex >= correctSequence.length)
			verifyResolved();
	}
	
	public void verifyResolved()
	{
		if (correctSequence.equals(tryingSequence))
		{
			changeDescription();
			for (Button button : buttons) {
				button.resolved();
			}
			
			Item item = getLocation().getItemFromString(itemToGive);
			if (item instanceof Pickable itemPickable)
			{
				System.out.println(descriptionResolved);
				itemPickable.becomePickable();
			}
			else 
				System.out.println("ERROR in EnigmaDevice " + getName() + ". The item " + itemToGive + " can't be picked.");
		}
		else
		{
			System.out.println("But nothing happens.");
			this.tryingSequenceIndex = 0;			
		}
	}
	
}

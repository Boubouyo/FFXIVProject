package items;

import commands.Use;
import locations.Location;

public class Button extends Item implements Use {

	private final int buttonId;
	private final EnigmaDevice enigmaDevice;
	private final String descriptionResolved;
	
	private boolean isResolved;
	
	public Button(String name, String description, Location location, String descriptionResolved, EnigmaDevice enigmaDevice, int id) 
	{
		super(name, description, location);
		this.descriptionResolved = description;
		this.enigmaDevice = enigmaDevice;
		this.buttonId = id;
		this.isResolved = false;
	}

	private void changeDescription()
	{
		setDescription(descriptionResolved);
	}
	
	public void resolved()
	{
		changeDescription();
	}
	
	public boolean use()
	{
		if (!isResolved)
		{
			enigmaDevice.pushButton(this.buttonId);
			return true;
		}
		return false;
	}
	
}
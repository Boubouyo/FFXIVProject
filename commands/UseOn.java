package commands;

import items.Item;

public interface UseOn {

	public abstract boolean useOn(Item item); // item is the item it will be used on ; return true if it can be used

}

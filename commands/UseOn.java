package commands;

import items.Item;

/**
 * @author Victor
 */

public interface UseOn {

	/**
     * Abstract method to use the item on another item
     * @param item (Item) : the item it will be used on
     * @return boolean : returns true if the item can be used on the other one (and is used)
     */
	public abstract boolean useOn(Item item);

}

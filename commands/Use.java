package commands;

/**
 * @author Victor
 */

public interface Use {

	/**
     * Abstract method to use the item
     * @return boolean : returns true if the item can be used (and is used)
     */
	public abstract boolean use();
	
}

package commands;

/**
 * An interface for the command take. 
 * 
 * @author Victor
 */

public interface Take {

	/**
     * Abstract method to take something
     * @return boolean : returns true if the item can be taken (and is taken)
     */
	public abstract boolean take();
	
}

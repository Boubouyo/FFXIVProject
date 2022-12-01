package Core;

/**
 * An exception for an incorrect input in one of the file for the initiation.
 * @author Victor
 */

public class InitiateFromFilesWrongException extends Exception 
{
	private static final long serialVersionUID = 1L;

	/**
     * Method to print an error message for the exception
     */
	public InitiateFromFilesWrongException(String msg) 
	{
		super(msg);
	}

}

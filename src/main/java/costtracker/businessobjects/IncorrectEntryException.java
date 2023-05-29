package costtracker.businessobjects;

public class IncorrectEntryException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8388981506138789912L;

	public IncorrectEntryException(String errorMessage, Throwable error) {
		super(errorMessage, error);
	}
	
	public IncorrectEntryException(String errorMessage) {
		super(errorMessage);
	}
}

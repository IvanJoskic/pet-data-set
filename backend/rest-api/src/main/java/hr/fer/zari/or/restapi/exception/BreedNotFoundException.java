package hr.fer.zari.or.restapi.exception;

public class BreedNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1347167995774640993L;

	public BreedNotFoundException(Long id) {
		super("Could not find breed with id " + id);
	}
}

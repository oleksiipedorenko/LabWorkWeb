package ua.skillsup.practice.rest.exceptions;

/**
 * Created by oleksii on 11/15/15.
 */
public class NotEnoughBeerException extends RuntimeException {

	public NotEnoughBeerException(String message) {
		super(message);
	}
}
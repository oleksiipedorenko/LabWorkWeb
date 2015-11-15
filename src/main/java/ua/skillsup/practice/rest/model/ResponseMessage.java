package ua.skillsup.practice.rest.model;

/**
 * Created by oleksii on 11/15/15.
 */
public class ResponseMessage {

	private String status;
	private BarStatus barStatus;
	private String errorMessage;

	public static ResponseMessage okMessage(BarStatus barStatus) {
		return new ResponseMessage("OK", barStatus);
	}

	public static ResponseMessage errorMessage(String message) {
		return new ResponseMessage("ERROR", message);
	}

	private ResponseMessage(String status, BarStatus barStatus) {
		this.status = status;
		this.barStatus = barStatus;
	}

	private ResponseMessage(String status, String errorMessage) {
		this.status = status;
		this.errorMessage = errorMessage;
	}

	public String getStatus() {
		return status;
	}

	public BarStatus getBarStatus() {
		return barStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}

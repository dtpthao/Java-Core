package ua.nure.thao.SummaryTask4.exception;

public class AppException extends Exception {

	private static final long serialVersionUID = 4148901651169554400L;
	
	public AppException() {
		super();
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public AppException(String message) {
		super(message);
	}
}

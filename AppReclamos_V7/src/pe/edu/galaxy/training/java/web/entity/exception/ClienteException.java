package pe.edu.galaxy.training.java.web.entity.exception;

public class ClienteException extends Exception{
	
	private static final long serialVersionUID = 7761094474228364109L;
	
	public ClienteException(String message) {
		super(message);
	}
	
	public ClienteException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public ClienteException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public ClienteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
}

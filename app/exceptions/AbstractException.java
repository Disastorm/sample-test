package exceptions;

public abstract class AbstractException extends Exception {
	private int code;
	
	protected AbstractException(int code){
		this.code = code;
	}

	protected AbstractException(int code, Throwable cause) {
		super(cause);
		this.code = code;
	}
	
	protected AbstractException(int code, String msg){
		super(msg);
		this.code = code;
	}
	
	public int getCode(){
		return code;
	}
}

package models.data;

public class CustomCodes {
	public static final int SUCCESS_CODE = 200;
	public static final int BAD_REQUEST_CODE = 400;
	public static final int UNAUTHORIZED_CODE = 401;
	public static final int INVALID_CREDENTIALS_CODE = 500;
	public static final int ALREADY_RESERVED_CODE = 501;
	public static final int NOT_RESERVED_CODE = 502;
	
	// this one was not in the spec, but I added for case of unexpected error
	public static final int UNEXPECTED_ERROR_CODE = 999;
}

package exceptions;

import models.data.CustomCodes;

public class AuthenticationException extends AbstractException {

	public AuthenticationException() {
		super(CustomCodes.UNAUTHORIZED_CODE);
	}

}

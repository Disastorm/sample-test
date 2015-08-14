package exceptions;

import models.data.CustomCodes;

public class UnexpectedException extends AbstractException {

	public UnexpectedException(Throwable cause) {
		super(CustomCodes.UNEXPECTED_ERROR_CODE, cause);
	}

}

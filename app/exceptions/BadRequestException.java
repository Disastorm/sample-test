package exceptions;

import models.data.CustomCodes;

public class BadRequestException extends AbstractException {

	public BadRequestException(String msg) {
		super(CustomCodes.BAD_REQUEST_CODE, msg);
	}

}

package exceptions;

import models.data.CustomCodes;
import models.data.CustomMessages;

public class AlreadyReservedException extends AbstractException {

	public AlreadyReservedException() {
		super(CustomCodes.ALREADY_RESERVED_CODE, CustomMessages.ALREADY_RESERVED_MESSAGE);
	}

}

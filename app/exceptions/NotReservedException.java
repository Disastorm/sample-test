package exceptions;

import models.data.CustomCodes;
import models.data.CustomMessages;

public class NotReservedException extends AbstractException {

	public NotReservedException() {
		super(CustomCodes.NOT_RESERVED_CODE, CustomMessages.NOT_RESERVED_MESSAGE);
	}

}

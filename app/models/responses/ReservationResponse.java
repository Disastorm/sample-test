package models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class ReservationResponse extends CodeResponse {

	public ReservationResponse(int code, String message) {
		super(code, message);
	}

	/**
	 * No need for getters and setters as Play Framework automatically generates
	 * them
	 **/

}

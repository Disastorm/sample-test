package models.requests;

import java.util.Map;
import java.util.Map.Entry;

import exceptions.AuthenticationException;
import exceptions.BadRequestException;
import play.libs.F.Option;

public class ReservationRequest extends AuthenticatedRequest<ReservationRequest> {
	/** These are the query string keys for the different variables. **/
	public static final String EVENT_ID_KEY = "event_id";
	public static final String RESERVE_KEY = "reserve";

	private Integer eventId;
	private Boolean reserve;

	/**
	 * This is where our query parameters are parsed out into an instance of
	 * this Java Class
	 **/
	@Override
	public Option<ReservationRequest> bind(String keyCased, Map<String, String[]> params) {
		super.bindToken(params);
		/**
		 * ignore keyCased. Since the Routes defines the endpoint with a single
		 * Object, 'q', this method will only be called once. In this case, the
		 * keyCased will be 'q', but this is unimportant.
		 */

		for (Entry<String, String[]> entry : params.entrySet()) {
			String key = entry.getKey();
			String[] values = entry.getValue();
			if (values != null && values.length > 0) {
				switch (key) {
				case EVENT_ID_KEY:
					eventId = Integer.parseInt(values[0]);
					break;
				case RESERVE_KEY:
					reserve = Boolean.parseBoolean(values[0]);
					break;
				default:
					break;
				}
			}
		}

		return Option.Some(this);
	}

	/**
	 * This will throw an exception if there are missing fields or invalid field
	 * values
	 * 
	 */
	public void validate() throws AuthenticationException, BadRequestException {
		super.validateTokenNotNull();
		if (eventId == null) {
			throw new BadRequestException("Missing '" + EVENT_ID_KEY + "'");
		}

		if (reserve == null) {
			throw new BadRequestException("Missing '" + RESERVE_KEY + "'");
		}
	}

	@Override
	public String javascriptUnbind() {
		// Not Implemented - Not currently needed
		return null;
	}

	@Override
	public String unbind(String arg0) {
		// Not Implemented - Not currently needed
		return null;
	}

	public Integer getEventId() {
		return eventId;
	}

	public Boolean getReserve() {
		return reserve;
	}

}

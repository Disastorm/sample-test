package models.requests;

import java.time.LocalDate;
import java.util.Map;
import java.util.Map.Entry;

import exceptions.AuthenticationException;
import exceptions.BadRequestException;
import play.libs.F.Option;

public class EventsRequest extends AuthenticatedRequest<EventsRequest> {
	/** These are the query string keys for the different variables. **/
	public static final String OFFSET_KEY = "offset";
	public static final String LIMIT_KEY = "limit";
	public static final String FROM_KEY = "from";

	private Integer offset;
	private Integer limit;
	private LocalDate from;

	/**
	 * This is where our query parameters are parsed out into an instance of
	 * this Java Class
	 **/
	@Override
	public Option<EventsRequest> bind(String keyCased, Map<String, String[]> params) {
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
				case OFFSET_KEY:
					offset = Integer.parseInt(values[0]);
					break;
				case LIMIT_KEY:
					limit = Integer.parseInt(values[0]);
					break;
				case FROM_KEY:
					from = LocalDate.parse(values[0]);
					break;
				default:
					break;
				}
			}
		}

		populateDefaultsIfNeeded();
		return Option.Some(this);
	}

	/**
	 * This will populate default values if they are null
	 */
	private void populateDefaultsIfNeeded() {
		if (this.offset == null) {
			this.offset = 0;
		}
		if (this.limit == null) {
			this.limit = 1000; // arbitrary high limit if not specified
		}
	}

	/**
	 * This will throw an exception if there are missing fields or invalid field
	 * values
	 * 
	 * @param requireAuthentication
	 *            - pass true if this request requires authentication.
	 */
	public void validate(boolean requireAuthentication) throws AuthenticationException, BadRequestException {
		if (requireAuthentication) {
			super.validateTokenNotNull();
		}
		if (from == null) {
			throw new BadRequestException("Missing '" + FROM_KEY + "'");
		}

		if ((limit != null && limit < 1) || offset < 0) {
			throw new BadRequestException(
					"Invalid offset or limit. Offset cannot be negative and limit must be greater than 0.");
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

	public LocalDate getFrom() {
		return from;
	}

	public Integer getLimit() {
		return limit;
	}

	public Integer getOffset() {
		return offset;
	}

}

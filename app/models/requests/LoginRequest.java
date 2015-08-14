package models.requests;

import java.util.Map;
import java.util.Map.Entry;

import exceptions.AuthenticationException;
import play.libs.F.Option;
import play.mvc.QueryStringBindable;

public class LoginRequest implements QueryStringBindable<LoginRequest> {
	/** These are the query string keys for the different variables. **/
	public static final String EMAIL_KEY = "email";
	public static final String PASSWORD_KEY = "password";

	private String email;
	private String password;

	/**
	 * This is where our query parameters are parsed out into an instance of
	 * this Java Class
	 **/
	@Override
	public Option<LoginRequest> bind(String keyCased, Map<String, String[]> params) {
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
				case EMAIL_KEY:
					email = values[0];
					break;
				case PASSWORD_KEY:
					password = values[0];
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
	 */
	public void validate() throws AuthenticationException {
		if (email == null || password == null) {
			throw new AuthenticationException();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

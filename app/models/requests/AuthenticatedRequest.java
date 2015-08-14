package models.requests;

import java.util.Map;

import exceptions.AuthenticationException;
import play.mvc.QueryStringBindable;

public abstract class AuthenticatedRequest<T extends AuthenticatedRequest<T>> implements QueryStringBindable<T> {
	public static final String TOKEN_KEY = "token";

	private String token;

	protected void bindToken(Map<String, String[]> params) {

		String[] tokenString = params.get(TOKEN_KEY);
		if (tokenString != null && tokenString.length > 0) {
			token = tokenString[0];
		}
	}

	public String getToken() {
		return token;
	}
	
	protected void validateTokenNotNull() throws AuthenticationException{
		if(token == null){
			throw new AuthenticationException();
		}
	}
}

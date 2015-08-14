package models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import models.serialdata.User;

public class LoginResponse extends CodeResponse {
	@JsonProperty
	private String token;

	@JsonProperty
	private User user;

	public LoginResponse(int code, String token, User user) {
		super(code);
		this.token = token;
		this.user = user;
	}

	/**
	 * No need for getters and setters as Play Framework automatically generates
	 * them
	 **/
}

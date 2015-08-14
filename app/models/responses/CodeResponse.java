
package models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import exceptions.AbstractException;
import models.data.CustomCodes;
import models.data.CustomMessages;
import play.libs.Json;
import play.mvc.Results;
import play.mvc.Results.Status;

@JsonInclude(Include.NON_EMPTY)
public class CodeResponse {
	public static final Status UNAUTHORIZED = Results
			.ok(Json.toJson(new CodeResponse(CustomCodes.UNAUTHORIZED_CODE)));
	public static final Status INVALID_CREDENTIALS = Results.ok(Json.toJson(
			new CodeResponse(CustomCodes.INVALID_CREDENTIALS_CODE, CustomMessages.INVALID_CREDENTIALS_MESSAGE)));

	@JsonProperty
	private int code;
	@JsonProperty
	private String message;

	public CodeResponse(int code) {
		this.code = code;
	}

	public CodeResponse(int code, String message) {
		this(code);
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static Status generateServerError(AbstractException e) {
		return Results.internalServerError(Json.toJson(new CodeResponse(e.getCode())));
	}

	public static Status generateBadRequest(String errorMsg) {
		return Results.badRequest(Json.toJson(new CodeResponse(CustomCodes.BAD_REQUEST_CODE, errorMsg)));
	}
	
	public static Status generateUnauthorized(String errorMsg) {
		return Results.ok(Json.toJson(new CodeResponse(CustomCodes.UNAUTHORIZED_CODE, errorMsg)));
	}
}

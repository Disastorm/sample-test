package controllers;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import dao.UsersDao;
import exceptions.AuthenticationException;
import models.data.CustomCodes;
import models.requests.LoginRequest;
import models.responses.CodeResponse;
import models.responses.LoginResponse;
import models.serialdata.AuthenticationToken;
import models.serialdata.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

public class AuthenticationController extends Controller {
	/**
	 * Logs a user in.
	 */
	public Result login(LoginRequest query) {
		query.bind(null, request().body().asFormUrlEncoded());
		try {
			query.validate();
		} catch (AuthenticationException e1) {
			// some needed fields are missing
			return CodeResponse.INVALID_CREDENTIALS;
		}
		User user = UsersDao.findUser(query.getEmail(), query.getPassword());
		if (user == null) {
			return CodeResponse.INVALID_CREDENTIALS;
		}
		try {
			return Results
					.ok(Json.toJson(new LoginResponse(CustomCodes.SUCCESS_CODE, AuthenticationToken.createAuthenticationToken(user), user)));
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException
				| UnsupportedEncodingException e) {
			return Results.internalServerError();
		}
	}
}

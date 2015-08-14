package models.serialdata;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.EnumSet;
import java.util.Random;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.ArrayUtils;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import exceptions.AuthenticationException;
import exceptions.ExpiredTokenException;
import models.data.GroupId;
import play.libs.Json;
import util.CustomDateTimeDeserializer;
import util.CustomDateTimeSerializer;

@JsonInclude(Include.NON_EMPTY)
public class AuthenticationToken extends Model {
	// arbitrarily choosing 30 days for expiry
	private static final int VALIDITY_TIME_DAYS = 30;
	private static final byte[] CONSTANT_SALT = Base64.getDecoder().decode("Te+hPdXlg+Ffz5Evos5gIg==");
	private static final int VARIABLE_SALT_SIZE_BYTES = 16;
	private static final Random RANDOM = new SecureRandom();

	@JsonProperty
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	@JsonDeserialize(using = CustomDateTimeDeserializer.class)
	private LocalDateTime expiry;

	@JsonProperty
	private User user;

	private AuthenticationToken(LocalDateTime expiry, User user) {
		this.expiry = expiry;
		this.user = user;
	}

	public AuthenticationToken() {

	}

	private static byte[] generateRandomSalt(int byteCount) {
		byte[] salt = new byte[byteCount];
		RANDOM.nextBytes(salt);
		return salt;
	}

	private String encrypt() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {
		final JsonNode contextAsJsonNode = Json.toJson(this);
		final String contextAsJsonString = Json.stringify(contextAsJsonNode);
		final byte[] contextAsByteArray = contextAsJsonString.getBytes();

		byte[] salt = generateRandomSalt(VARIABLE_SALT_SIZE_BYTES);
		IvParameterSpec iv = new IvParameterSpec(salt);

		SecretKeySpec secretKeySpec = new SecretKeySpec(CONSTANT_SALT, "AES");

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);

		byte[] encryptedContext = cipher.doFinal(contextAsByteArray);
		// prepend the 16 byte salt to the context
		byte[] finalBytes = ArrayUtils.addAll(salt, encryptedContext);

		return Base64.getUrlEncoder().encodeToString(finalBytes);
	}

	private static AuthenticationToken getToken(String encryptedToken) throws AuthenticationException, ExpiredTokenException {
		try {
			byte[] finalBytes = Base64.getUrlDecoder().decode(encryptedToken);
			byte[] salt = Arrays.copyOf(finalBytes,
					VARIABLE_SALT_SIZE_BYTES); /* first 16 bytes are salt */
			if (finalBytes.length < VARIABLE_SALT_SIZE_BYTES)
				throw new AuthenticationException();
			byte[] encryptedContext = Arrays.copyOfRange(finalBytes, VARIABLE_SALT_SIZE_BYTES, finalBytes.length);

			IvParameterSpec iv = new IvParameterSpec(salt);
			SecretKeySpec secretKeySpec = new SecretKeySpec(CONSTANT_SALT, "AES");

			Cipher cipher;

			cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");

			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);

			byte[] contextAsByteArray = cipher.doFinal(encryptedContext);
			String contextAsJsonString = new String(contextAsByteArray);
			AuthenticationToken token = Json.fromJson(Json.parse(contextAsJsonString), AuthenticationToken.class);
			if(token.expiry.isBefore(LocalDateTime.now())){
				throw new ExpiredTokenException();
			}
			return token;
		} catch (BadPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidKeyException | InvalidAlgorithmParameterException e) {
			throw new AuthenticationException();
		}
	}

	public static String createAuthenticationToken(User user) throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {
		LocalDateTime expireTime = LocalDateTime.now();
		expireTime = expireTime.plusDays(VALIDITY_TIME_DAYS);
		AuthenticationToken token = new AuthenticationToken(expireTime, user);
		return token.encrypt();
	}

	/**
	 * Returns authenticated user. It will never return null.
	 */
	public static User authenticate(String encryptedToken, Set<GroupId> allowedGroupIds)
			throws AuthenticationException, ExpiredTokenException {
		AuthenticationToken token = AuthenticationToken.getToken(encryptedToken);
		if (token == null)
			throw new AuthenticationException();

		GroupId groupId = GroupId.getGroupId(token.user.getGroupId());
		if (groupId == null || !allowedGroupIds.contains(groupId))
			throw new AuthenticationException();

		return token.user;
	}

	/**
	 * Returns authenticated user. It will never return null.
	 */
	public static User authenticateAnyUser(String encryptedToken) throws AuthenticationException, ExpiredTokenException {
		return authenticate(encryptedToken, EnumSet.allOf(GroupId.class));
	}

	/**
	 * No need for getters and setters as Play Framework automatically generates
	 * them
	 **/

}

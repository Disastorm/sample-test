package dao;

import org.apache.commons.codec.digest.DigestUtils;

import models.serialdata.User;

public class UsersDao {

	/**
	 * Find user with specified email and password.
	 */
	public static User findUser(String email, String password) {
		return User.find.where().eq("email", email).eq("password", DigestUtils.sha1Hex(password)).findUnique();
	}
}

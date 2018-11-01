package com.messageserv.dao;

import java.util.HashMap;
import java.util.Map;

import com.messageserv.model.User;

/**
 * @author Satyajeet
 *This class will be used to store user profile data (In memory)
 */
public class UserProfileDao {
	private static Map<String, User> users = new HashMap<>();

	public static Map<String, User> getProfiles() {
		return users;
	}

}

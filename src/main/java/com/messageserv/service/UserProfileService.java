package com.messageserv.service;

import com.messageserv.model.User;

/**
 * @author Satyajeet
 *
 */
public interface UserProfileService {

	User getUserProfile(String userId);
	User addUserProfile(User user);
	User updateUserProfile(User user);
}

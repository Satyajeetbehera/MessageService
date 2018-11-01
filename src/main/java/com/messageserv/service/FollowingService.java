package com.messageserv.service;

import java.util.List;

import com.messageserv.model.User;

/**
 * @author Satyajeet
 *
 */
public interface FollowingService {
	
	void followUserProfile(List<String> userId);
	User addFollower(List<String> userId);

}

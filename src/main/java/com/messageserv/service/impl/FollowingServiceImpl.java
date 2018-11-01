package com.messageserv.service.impl;

import java.util.List;

import com.messageserv.model.User;

/**
 * @author Satyajeet
 * 
 * This class provides all implementation to user follow related services
 *
 */
public class FollowingServiceImpl{
	
	
	/**
	 * @param userId
	 */
	public void followUserProfile(List<String> userId){
		UserProfileServiceImpl userProfileServiceImpl = new UserProfileServiceImpl();
		User followedUser = userProfileServiceImpl.getUserProfile(userId.get(0));
//		for(int i=0;i<userId.size();i++){
//			
//		}
		
		followedUser.setFollowers(userId);
		
	}


}

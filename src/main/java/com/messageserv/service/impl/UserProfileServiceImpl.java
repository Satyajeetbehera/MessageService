package com.messageserv.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.messageserv.dao.UserProfileDao;
import com.messageserv.model.User;
import com.messageserv.service.UserProfileService;

/**
 * @author Satyajeet
 * This class provides all implementation to user profile related services
 *
 */
public class UserProfileServiceImpl implements UserProfileService {
	
	private Map<String, User> userProfiles = UserProfileDao.getProfiles();
	
	
	
	public UserProfileServiceImpl() {
		
			 List<String> followerList = new ArrayList<String>();
		for(int i = 2; i< 5;i++){
			String mail = "test"+i+"@test.com";
			followerList.add(mail);
		}
		userProfiles.put("test1@test.com", new User(1L, "test1@test.com", "test1", followerList));
	}

	/**
	 * @param emailId
	 * @return
	 */
	@Override
	public User getUserProfile(String emailId){
		return userProfiles.get(emailId);
	}
	
	/**
	 * @return
	 */
	public List<User> getAllUserProfiles() {
		return new ArrayList<User>(userProfiles.values()); 
	}
	
	/**
	 * @param user
	 * @return
	 */
	@Override
	public User addUserProfile(User user){
		System.out.println("addUserProfile");
		userProfiles.put(user.getMailId(), user);
		return user;
	}
	
	/**
	 * @param user
	 * @return
	 */
	@Override
	public User updateUserProfile(User user){
		
		if (user.getMailId().isEmpty()) {
			return null;
		}
		((Map<String, User>) user).put(user.getMailId(), user);
		return user;
	}

	/**
	 * @param followingMailId
	 * @param followerMailId
	 */
	public User addFollower(String followingMailId,String followerMailId) {
		User user = userProfiles.get(followingMailId);
//		System.out.println(followingMailId+"----------------"+followerMailId);
		
		if(user != null){
			List<String> followers = user.getFollowers();
			followers.add(followerMailId);
			userProfiles.put(followingMailId, user);
		return user;
		}
		return user;
	}
}

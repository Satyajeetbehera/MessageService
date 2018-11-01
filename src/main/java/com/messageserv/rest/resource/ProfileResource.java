package com.messageserv.rest.resource;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.messageserv.model.Message;
import com.messageserv.model.User;
import com.messageserv.service.impl.UserProfileServiceImpl;

/**
 * @author Satyajeet
 * This class will be used to provide all user profile related services
 *
 */
@Path("userprofiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {
	
	private UserProfileServiceImpl userProfileServiceImpl = new UserProfileServiceImpl();
	
	/**
	 * @param mailId
	 * @return
	 * 
	 * This method will find a user profile
	 */
	@GET
	@Path("/{mailId}")
	public User getUserProfile(@PathParam("mailId") String mailId) {
		return userProfileServiceImpl.getUserProfile(mailId);
	}
	
	/**
	 * @return
	 * 
	 * This method will get all user profiles
	 */
	@GET
	public List<User> getAllUserProfiles() {
		return userProfileServiceImpl.getAllUserProfiles();
	}
	
	/**
	 * @param user
	 * @return
	 * 
	 * This method will provide add profile service
	 */
	@POST
	public User addUserProfile(User user) {
		return userProfileServiceImpl.addUserProfile(user);
	}

	/**
	 * @param followingMailId
	 * @param followerMailId
	 * @return
	 * 
	 * This method will provide follow service
	 */
	@PUT
	@Path("/follow/{followingMailId}")
	public User followTheUser(@PathParam("followingMailId") String followingMailId,@QueryParam("followerMailId") String followerMailId) {
//		System.out.println("-----------------followTheUser--------------"+followerMailId);
		return userProfileServiceImpl.addFollower(followingMailId,followerMailId);
	}
	
	
}

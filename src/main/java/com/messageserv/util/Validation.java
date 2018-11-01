package com.messageserv.util;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * @author Satyajeet
 * 
 * This class will provide all validations around the appication
 *
 */
public class Validation {

	/**
	 * @param recipientMailId
	 * @param followerMialId
	 * @return
	 */
	public boolean checkFollower(String recipientMailId, String followerMialId) {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/messageserv/feed/").path("userprofiles").path(recipientMailId);
		Invocation.Builder invocationBuilder =  target.request(MediaType.APPLICATION_JSON);
		String response = invocationBuilder.get(String.class);
		System.out.println(response +"=========="+followerMialId);
		if (response != null || response !=""){
			if (response.contains(followerMialId))
				return true;
		}
		
		return false;
	}
}

package com.messageserv.rest.resource;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.messageserv.dao.MessageDao;
import com.messageserv.exception.ResourceNotFoundException;
import com.messageserv.model.Message;
import com.messageserv.service.MessageService;
import com.messageserv.service.impl.MessageServiceImpl;

import javax.ws.rs.core.UriInfo;


/**
 * @author Satyajeet
 * This class will serve as resource for all message related services
 *
 */
@Path("messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	MessageServiceImpl messageServiceImpl = new MessageServiceImpl();
	private static Map<Long, Message> messages = MessageDao.getMessages();
	private Map<String, List<Long>> userMessages = MessageDao.getUserMessages();
	
	/**
	 * @param mailId
	 * @return
	 * 
	 *This method will return all messages for the specific user id 
	 */
	@GET
	@Path("/{mailId}")
	public List<Message> getAllMessages(@PathParam("mailId") String mailId){
		System.out.println("-------MessageResource-------");
		List messageIdList = userMessages.get(mailId);
		if (messageIdList == null) {
			throw new ResourceNotFoundException("No message found for "+mailId);
		}
//		return "All messages";
		 List<Message> messagList = messageServiceImpl.getAllMessages(messageIdList);
		return  messagList;
		
	}
	
	
	/**
	 * @param mailId
	 * @return
	 * This method provides all messages with pagination capability
	 */
	@GET
	@Path("/pagination/{mailId}")
	public List<Message> getAllMessagesPagination(@PathParam("mailId") String mailId,@QueryParam("offset") int offset,@QueryParam("limit") int limit){
		List messageIdList = userMessages.get(mailId);
//		return "All messages";
		 List<Message> messagList = messageServiceImpl.getAllMessagesPagination(mailId,offset, limit);
		return  messagList;
		
	}
	
	/**
	 * @param message
	 * @param recipientMailId
	 * @return
	 * This method will provide add message service from a user to its follower 
	 */
	@POST
	@Path("/{recipientMailId}")
	public Response postMessage(Message message,@PathParam("recipientMailId") String recipientMailId) {
		Date now = new Date();
		message.setCreationDate(now);
		Message postedMessage = messageServiceImpl.addMessage(message,recipientMailId);
		if (postedMessage != null){
				return Response.status(Status.CREATED)
						.entity(postedMessage)
						.build();
		}
		return Response.status(Status.UNAUTHORIZED)
				.build();
	}
	
	/**
	 * @param id
	 * @param message
	 * @return
	 * This method will update a message 
	 */
	@PUT
	@Path("/{recipientMailId}")
	public Message updateMessage(@PathParam("recipientMailId") String recipientMailId, Message message) {
		Date now = new Date();
		message.setCreationDate(now);
		return messageServiceImpl.updateMessage(recipientMailId,message);
	}
	
	/**
	 * @param recipientMailId
	 * @param messageId
	 * This method will delete a message
	 */
	@DELETE
	@Path("/{recipientMailId}")
	public void deleteMessage(@PathParam("recipientMailId") String recipientMailId, @QueryParam("messageId") long messageId) {
		
		messageServiceImpl.deleteMessage(recipientMailId,messageId);
	}

}

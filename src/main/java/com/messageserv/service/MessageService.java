package com.messageserv.service;

import java.util.List;

import com.messageserv.model.Message;

/**
 * @author Satyajeet
 *
 */
public interface MessageService {
	
	List<Message> getAllMessages(List messageIdList);
	Message getMessage(long messageId);
	List<Message> getAllMessagesPagination(String mailId,int offset, int limit);
		
	Message addMessage(Message message, String mailId);
	Message updateMessage(String mailId,Message message);
	void deleteMessage(String mailId,long messageId);
	

}

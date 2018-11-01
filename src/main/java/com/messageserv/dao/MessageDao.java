package com.messageserv.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.messageserv.model.Message;
import com.messageserv.model.User;

/**
 * @author Satyajeet
 * 
 * This class will be used to store message data (In memory)
 *
 */
public class MessageDao {
	
	private static Map<Long, Message> messages = new HashMap<>();
	
	private static Map<String, List<Long> >userMessages = new HashMap<>();
	
	
	/**
	 * @return
	 * 
	 * This method will provide a message map holding messages for message id
	 */
	public static Map<Long, Message> getMessages() {
		return messages;
	}
	
	
	/**
	 * @return
	 */
	public static Map<String, List<Long>> getUserMessages() {
		return userMessages;
	}
	

}

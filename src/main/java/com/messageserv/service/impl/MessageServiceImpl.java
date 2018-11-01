package com.messageserv.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import com.messageserv.dao.MessageDao;
import com.messageserv.exception.ResourceNotFoundException;
import com.messageserv.model.Message;
import com.messageserv.service.MessageService;
import com.messageserv.util.Validation;

/**
 * @author Satyajeet
 * 
 * This class provides all implementation to message related services
 *
 */
public class MessageServiceImpl implements MessageService,Comparator<Message>{

	Validation validation   = new Validation(); 
	private Map<Long, Message> messages = MessageDao.getMessages();
	private Map<String, List<Long>> userMessages = MessageDao.getUserMessages();
	
	public MessageServiceImpl() {
		messages.put(1L, new Message(1, "Hello ", "craft demo 1"));
		messages.put(2L, new Message(2, "Hi ", "craft demo 1"));
		messages.put(3L, new Message(3, "Welcome ", "craft demo 1"));
		List messageIdList = new ArrayList();
		for (Map.Entry<Long, Message> entry : messages.entrySet()) {
			messageIdList.add(entry.getKey());
		}
		userMessages.put("test1@test.com", messageIdList);
	}
	
	/**
	 * @param messageIdList
	 * @return
	 */
	/* (non-Javadoc)
	 * @see com.service.messageserv.service.MessageService#getAllMessages(java.util.List)
	 */
	@Override
	public List<Message> getAllMessages(List messageIdList){
		List userMessageList = new ArrayList<Message>();
		System.out.println("-----------------MessageServiceImpl--getAllMessages--------------------");
		for(int i =0 ;i<messageIdList.size();i++){
//			System.out.println("-----------------messageIdList.size--------------------"+messageIdList.size());
			userMessageList.add(messages.get(messageIdList.get(i)));
		}
		Collections.sort(userMessageList,new MessageServiceImpl());
		return userMessageList; 
	}
	
	/* (non-Javadoc)
	 * @see com.service.messageserv.service.MessageService#getAllMessagesPagination(int, int)
	 * 
	 */
	@Override
	public List<Message> getAllMessagesPagination(String mailId,int offset, int limit) {
		ArrayList<Message>  userMessageList = new ArrayList<Message>();
		if (offset + limit > userMessageList.size()) return new ArrayList<Message>();
		return userMessageList.subList(offset, offset + limit); 
	}
	
	/**
	 * @param messageId
	 * @return
	 */
	@Override
	public Message getMessage(long messageId){
		Message message = messages.get(messageId);
		if (message == null) {
//			throw new DataNotFoundException("Message with id " + id + " not found");
		}
		return message;
	}
	
	/**
	 * @param message
	 * @param recipientMailId
	 * @return
	 */
	@Override
	public Message addMessage(Message message , String recipientMailId){
		System.out.println("----------------message.getMessageId()-----------"+message.getMessageId());
		String followerMialId = message.getContentAuthor();
		boolean isFollower = validation.checkFollower(recipientMailId,followerMialId);
//		System.out.println("----------------isFollower---------------"+isFollower);
		if(isFollower){
			messages.put(message.getMessageId(), message);
			List messageIdList = userMessages.get(recipientMailId);
			messageIdList.add(message.getMessageId());
			userMessages.put(recipientMailId, messageIdList);
			return message;
		}
		return null;
	}

	/**
	 * @param message
	 * @return
	 */
	@Override
	public Message updateMessage(String recipientMailId,Message message){
		List messageIdList = userMessages.get(recipientMailId);
		
		if (message.getMessageId() <= 0) {
			return null;
		}
		System.out.println(messages.get(message.getMessageId()));
		System.out.println(message.getMessageId()+"---------message------"+message);
		messageIdList.set(messageIdList.indexOf( message.getMessageId()),message.getMessageId());
		
		messages.put(message.getMessageId(), message);
		userMessages.put(recipientMailId, messageIdList);
		return message;
	}

	/**
	 * @param recipientMailId
	 * @param message
	 */
	@Override
	public void deleteMessage(String recipientMailId,long messageId){
		if (!userMessages.containsKey(recipientMailId)) {
			throw new ResourceNotFoundException("No message found for "+recipientMailId);
		}
		List messageIdList = userMessages.get(recipientMailId);
		if (messageIdList == null) {
			throw new ResourceNotFoundException("No message found for "+recipientMailId);
		}
		if(messages.containsKey(messageId)){
			messages.remove(messageId);
		}else{
			throw new ResourceNotFoundException("No message exist with id"+messageId);
		}
		messageIdList.remove(messageId);
		userMessages.put(recipientMailId, messageIdList);
		
	}
	
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
    public int compare(Message msg1, Message msg2) {
		if ((msg1.getCreationDate()).after(msg2.getCreationDate())){
			return 1;
		} else if ((msg1.getCreationDate()).before(msg2.getCreationDate())){
			return -1;
		} else
			return 0;
    }

	// To be ignored
	
	@Override
	public Comparator<Message> reversed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Message> thenComparing(Comparator<? super Message> other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> Comparator<Message> thenComparing(
			Function<? super Message, ? extends U> keyExtractor,
			Comparator<? super U> keyComparator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U extends Comparable<? super U>> Comparator<Message> thenComparing(
			Function<? super Message, ? extends U> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Message> thenComparingInt(
			ToIntFunction<? super Message> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Message> thenComparingLong(
			ToLongFunction<? super Message> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Message> thenComparingDouble(
			ToDoubleFunction<? super Message> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T> Comparator<T> nullsFirst(Comparator<? super T> comparator) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T> Comparator<T> nullsLast(Comparator<? super T> comparator) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T, U> Comparator<T> comparing(
			Function<? super T, ? extends U> keyExtractor,
			Comparator<? super U> keyComparator) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T, U extends Comparable<? super U>> Comparator<T> comparing(
			Function<? super T, ? extends U> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T> Comparator<T> comparingInt(
			ToIntFunction<? super T> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T> Comparator<T> comparingLong(
			ToLongFunction<? super T> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T> Comparator<T> comparingDouble(
			ToDoubleFunction<? super T> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}
}

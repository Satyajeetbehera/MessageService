package com.messageserv.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Satyajeet
 *
 */
@XmlRootElement
public class Message {

	private long messageId;
	private String content;
    private Date creationDate;
    private String contentAuthor;
    
    public Message(){}
    
    public Message(long messageId, String content, String contentAuthor) {
    	this.messageId = messageId;
    	this.content = content;
    	this.contentAuthor = contentAuthor;
    	this.creationDate = new Date();
    }
	
    
    public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getContentAuthor() {
		return contentAuthor;
	}

	public void setContentAuthor(String contentAuthor) {
		this.contentAuthor = contentAuthor;
	}

}

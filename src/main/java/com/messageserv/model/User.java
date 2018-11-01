package com.messageserv.model;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Satyajeet
 *
 */
@XmlRootElement
public class User {

	private long userId; // assumed to be employee id
	private String mailId;
	private String name; // assumed to be display name
    private Date created;
    private List<String> followers;
    
    public User(){}
    
    public User(long userId, String mailId, String name, List<String> followers) {
		this.userId = userId;
		this.mailId = mailId;
		this.name = name;
		this.created = new Date();
//		System.out.println("followers-----------------"+followers);
		this.followers = followers;
	}
    
    
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public List<String> getFollowers() {
		return followers;
	}
	public void setFollowers(List<String> followers) {
//		this.followers = followers;
		followers.addAll(followers);
	}
    
}

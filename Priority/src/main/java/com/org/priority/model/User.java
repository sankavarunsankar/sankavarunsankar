package com.org.priority.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class User {
	@Id
//	@GeneratedValue
	private int userId;
	private String userName;
//	@OneToMany(cascade = CascadeType.ALL)  
//	@JoinColumn(name="userId")
//	private List<UserPriority> userPriorities;
	
	public int getUserId() {
		return userId;
	}
//	public User(int userId, String userName) {
//	super();
//	this.userId = userId;
//	this.userName = userName;
//}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
//	public List<UserPriority> getUserPriorities() {
//		return userPriorities;
//	}
//	public void setUserPriorities(List<UserPriority> userPriorities) {
//		this.userPriorities = userPriorities;
//	}
}

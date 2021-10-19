package com.org.priority.model;

import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class UserPriority implements Serializable, Comparable<UserPriority>{
	
	@Override
	public String toString() {
		return "UserPriority [userName=" + userName + ", userPriorityid=" + userPriorityid + ", priorityId="
				+ priorityId + ", userId=" + userId + ", rank=" + rank + ", satisfactionRating=" + satisfactionRating
				+ "]";
	}


	@Id
//	@GeneratedValue
	private int userPriorityid;
	@Transient
	private String userName;
	private Integer priorityId;
	private Integer userId;	
	private int rank;	
	private int satisfactionRating;
	
	
//	public int getUserPriorityid() {
//		return userPriorityid;
//	}
	public void setUserPriorityid(int userPriorityid) {
		this.userPriorityid = userPriorityid;
	}
	public int getSatisfactionRating() {
		return satisfactionRating;
	}
	public void setSatisfactionRating(int satisfactionRating) {
		this.satisfactionRating = satisfactionRating;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getPriorityId() {
		return priorityId;
	}
	public void setPriorityId(Integer priorityId) {
		this.priorityId = priorityId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	
	// Sorting based on rank
	@Override
	public int compareTo(UserPriority userPriority) {
		if(rank==userPriority.rank)  return 0;  
		else if(rank>userPriority.rank)return 1;  
		else return -1;  
	}

}

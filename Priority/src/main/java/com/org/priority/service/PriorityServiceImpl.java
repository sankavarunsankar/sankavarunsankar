package com.org.priority.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.priority.dao.PriorityDao;
import com.org.priority.dao.UserDao;
import com.org.priority.dao.UserPriorityDao;
//import com.org.priority.dao.UserPriorityDao;
import com.org.priority.model.Priority;
import com.org.priority.model.User;
import com.org.priority.model.UserPriority;
@Service
public class PriorityServiceImpl implements PriorityService {
	
	@Autowired
	PriorityDao priorityRepo;
	
	@Autowired
	UserPriorityDao userPriorityRepo;
	
	@Autowired
	UserDao userRepo;
	
	/**
	 * Get All Priorities
	 */
	@Override
	public Iterable<Priority> getPriorities() {
		return  priorityRepo.findAll();
	}
	
	/**
	 * Add new Priority
	 */
	@Override
	public void addPriority(Priority priority) {
		priority.setId(priorityRepo.findMaxId()+1);
		priorityRepo.save(priority);
	}
	
	/**
	 * Get all userPriorities for given user
	 */
	@Override
	public List<UserPriority> getPrioritiesOfUser(int id) {
		List<UserPriority> listOfPriorities=userPriorityRepo.findAllByUserId(id);
		for(UserPriority priority:listOfPriorities) {priority.setUserName(userRepo.findById(id).get().getUserName());}
		Collections.sort(listOfPriorities); 
		return listOfPriorities;
	}
	
	/**
	 * Changing the priorities attributes values if already
	 * Else adding new Priorities and new User Id of new User
	 */
	@Override
	public HashMap<String,String> addPrioritiesOfUser(List<UserPriority> userPriorities) {
		int  id=userPriorityRepo.findMaxId();
		HashMap<String,String> responseMessage=new HashMap<>();
		try {
		//UserId for given userName;(Accepting userName from Request)
		Optional<Integer> userId=userRepo.findUserIdByUserName(userPriorities.get(0).getUserName());
		List<UserPriority> userPrioritiesFromDb=new ArrayList<UserPriority>();
		//checking if user is present
		if(!userId.isEmpty()) {
			//if UserPriorities for the given user were not there adding UserPriorities
			userPrioritiesFromDb=userPriorityRepo.findAllByUserId(userId.get());
			if(userPrioritiesFromDb.size()==0 ) {				
				for(UserPriority userPriority:userPriorities) {
					userPriority.setUserPriorityid(++id);
					userPriority.setUserId(userId.get());
				}
				responseMessage.put("message", "User Priorities of "+userPriorities.get(0).getUserName()+" Added successfully");			
			}
			//if UserPriorities for the given user were there updating them
			else if(userPrioritiesFromDb.size()>0){
				userPriorityRepo.deleteAll(userPrioritiesFromDb);
				for(UserPriority userPriority:userPriorities) {
					userPriority.setUserPriorityid(++id);
					userPriority.setUserId(userId.get());
				}
				responseMessage.put("message", "User Priorities of "+userPriorities.get(0).getUserName()+" updated successfully");
			}
		}
		//If User is not there. Creating new User and adding UserPriorities.
		else{
			User user=new User();
			user.setUserId(userRepo.findMaxId()+1);
			user.setUserName(userPriorities.get(0).getUserName());
			userRepo.save(user);
			for(UserPriority userPriority:userPriorities) {
				userPriority.setUserPriorityid(++id);
				userPriority.setUserId(user.getUserId());
			}
			responseMessage.put("message", "User Priorities for new User "+userPriorities.get(0).getUserName()+" updated successfully");
		}
		userPriorityRepo.saveAll(userPriorities);
		}catch(Exception e) {
			responseMessage.put("message", "Exception occured in addPrioritiesOfUser method:"+e.getMessage());
		}return responseMessage;
	}
}
	

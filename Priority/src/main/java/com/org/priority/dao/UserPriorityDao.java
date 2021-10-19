package com.org.priority.dao;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Repository;

import com.org.priority.model.Priority;
import com.org.priority.model.User;
import com.org.priority.model.UserPriority;

@Repository
public interface UserPriorityDao extends CrudRepository<UserPriority, Integer>{


	List<UserPriority> findAllByUserId(int id);
	
	//Returns Highest UserPriorityId
	@Query(value ="SELECT MAX(USER_PRIORITYID) FROM USER_PRIORITY", nativeQuery = true)
	int findMaxId();
	
	//Returns all UserPriorities for given userId
	List<UserPriority> findAllByUserId(String userName);
	
	//Delete row based on useId
	void deleteAllByUserId(int userId);
}


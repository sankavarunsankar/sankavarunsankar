package com.org.priority.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.org.priority.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer>{
	
	//Returns Highest UserId
	@Query(value ="SELECT MAX(USER_ID) FROM USER", nativeQuery = true)
	int findMaxId();
	
	// Returns UserId for UserName
	@Query(value="SELECT USER_ID FROM USER WHERE USER_NAME=:userName", nativeQuery = true)
	Optional<Integer> findUserIdByUserName(String userName);
}

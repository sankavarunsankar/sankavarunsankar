package com.org.priority.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.org.priority.model.Priority;
import com.org.priority.model.UserPriority;

@Repository
public interface PriorityDao extends CrudRepository<Priority, Integer>{
	
	//Returns highest Id of Priority
	@Query(value ="SELECT MAX(ID) FROM PRIORITY", nativeQuery = true)
	int findMaxId();

}

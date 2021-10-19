package com.org.priority.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.org.priority.model.Priority;
import com.org.priority.model.UserPriority;


public interface PriorityService {
	public Iterable<Priority> getPriorities();
	public void addPriority(Priority priority);
	public List<UserPriority> getPrioritiesOfUser(int id);
	public HashMap<String,String> addPrioritiesOfUser(List<UserPriority> userPriority);
}

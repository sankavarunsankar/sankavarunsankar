package com.org.priority.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.org.priority.customExceptions.*;
import com.org.priority.model.Priority;
import com.org.priority.model.UserPriority;
import com.org.priority.service.PriorityServiceImpl;

@RestController
public class PriorityController {
	
	@Autowired
	PriorityServiceImpl service;
	
	/**
	 * Returns all the priorities
	 */
	@GetMapping("/Priorities")
	public ResponseEntity<?> getPriorities() {
		return new ResponseEntity<>(service.getPriorities(), HttpStatus.OK);	
	}
	
	/**
	 * To add new Priority in Priorities List
	 * @param priority
	 * @return Message and Status
	 */
	@PostMapping("/Priorities")
	public ResponseEntity<?> addPriority(@RequestBody Priority priority) {
		HashMap<String,String> response=new HashMap<>();
		try {
			if(priority.getName().trim().isEmpty())throw new UserNullException();
			service.addPriority(priority);
			response.put("message",priority.getName()+" added successfully");
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		}catch(UserNullException e){
			response.put("message","Priority Name can't be Null");
			return new ResponseEntity<>(response,HttpStatus.NOT_ACCEPTABLE);
		}catch(Exception e) {
			response.put("message",e.getMessage());
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Input:
	 * 		@param id
	 * @Return all Priorites of given User
	 */
	@GetMapping("/Priorities/{id}")
	public ResponseEntity<?> getPrioritiesOfUser(@PathVariable int id){
		return new ResponseEntity<>(service.getPrioritiesOfUser(id), HttpStatus.OK);		
	}
	/**
	 * Input:
	 * 		@param id
	 * 		@param userPriorities-RequestBody
	 * @return	Message and Status
	 */
	@PostMapping("/addPriorities")
	public ResponseEntity<?> addPrioritiesOfUser(@RequestBody List<UserPriority> userPriorities){
		HashMap<String,String> response=new HashMap<>();
		try {
			
			HashMap<String,String> responseMessage=service.addPrioritiesOfUser(userPriorities);
			response.put("message",responseMessage.get("message"));
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		}catch(Exception e) {
			response.put("message",e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
	}
}

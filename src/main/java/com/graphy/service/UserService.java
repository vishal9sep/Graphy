package com.graphy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphy.entity.User;
import com.graphy.exceptions.UserException;
import com.graphy.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	

	/** 
	 * This method will save single user to the DB. 
	 */	
	public User addUser(User user) throws UserException {
			User existingUser = userRepository.findByEmail(user.getEmail());
			
			if(existingUser!=null) {
				throw new UserException("User exist with email : "+user.getEmail());
			}
			return userRepository.save(user);
	}

}

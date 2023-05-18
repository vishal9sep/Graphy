package com.graphy.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.graphy.entity.User;
import com.graphy.exceptions.UserException;
import com.graphy.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/addUser")
	public ResponseEntity<User> addUserHandler(@Valid @RequestBody User user) throws UserException {

		User savedUser = userService.addUser(user);

		return new ResponseEntity<>(savedUser, HttpStatus.OK);
	}

}

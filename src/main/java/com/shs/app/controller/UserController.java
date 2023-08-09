package com.shs.app.controller;


import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shs.app.entity.Role;
import com.shs.app.entity.User;
import com.shs.app.entity.UserRole;
import com.shs.app.exceptions.DataNotFoundException;
import com.shs.app.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User u) throws Exception {
		
//		if(!u.getEmail().endsWith("@gmail.com")) {
//			throw new DataNotFoundException("Enter a valid user email ");
//		}
		
		Role role = new Role();
		role.setRoleId(45L);
		role.setRoleName("Normal");

		Set<UserRole> userroles = new HashSet<>();
		UserRole userRole = new UserRole();
		userRole.setUser(u);
		userRole.setRole(role);
		userroles.add(userRole);
		String encode = encoder.encode(u.getPassword());
		u.setPassword(encode);
		User createUser = this.service.createUser(u, userroles);
		System.out.println(createUser);

		return new ResponseEntity<User>(createUser, HttpStatus.CREATED);
	}

	
	@GetMapping("/{username}")
	public ResponseEntity<User> getUser(@PathVariable String username) {

		return new ResponseEntity<User>(this.service.getByName(username), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{userid}")
	public ResponseEntity<String> deleteUserById(@PathVariable Long userid) {

		return new ResponseEntity<String>(this.service.deleteUserById(userid), HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateUser(@RequestBody User u) throws Exception {
		// TODO Auto-generated method stub

		Role role = new Role();
		role.setRoleId(44L);
		role.setRoleName("Normal");

		Set<UserRole> userroles = new HashSet<>();
		UserRole userRole = new UserRole();
		userRole.setUser(u);
		userRole.setRole(role);

		userroles.add(userRole);

		return new ResponseEntity<String>(this.service.updateUser(u, userroles), HttpStatus.CREATED);
	}

}


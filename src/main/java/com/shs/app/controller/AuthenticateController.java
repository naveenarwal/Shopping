package com.shs.app.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shs.app.config.JwtUtil;
import com.shs.app.entity.JwtRequest;
import com.shs.app.entity.JwtResponse;
import com.shs.app.entity.User;
import com.shs.app.serviceImpl.UserDetailsServiceImpl;


@RestController
@CrossOrigin("*")
public class AuthenticateController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		try {
			System.out.println(jwtRequest.getUserName() + jwtRequest.getPassword());
		this.authenticate(jwtRequest.getUserName(), jwtRequest.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("User not found");

		 }
		UserDetails loadUserByUsername = this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUserName());
		String token = this.jwtUtil.generateToken(loadUserByUsername);
		System.out.println(token + "token is this");
		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

	}

	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		return ((User) this.userDetailsServiceImpl.loadUserByUsername(principal.getName()));
	}
}

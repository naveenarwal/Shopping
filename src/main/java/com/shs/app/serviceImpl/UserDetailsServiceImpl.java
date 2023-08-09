package com.shs.app.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shs.app.entity.User;
import com.shs.app.repo.IUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private IUserRepository urepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.urepo.findByUserName(username);
		
		if(user == null) {
			System.out.println("udsi user not found");
			throw new UsernameNotFoundException("NO User With UserName =>"+username+"  Found");
		}
		
		return user;
	}



}

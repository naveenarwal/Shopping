package com.shs.app.service;

import java.util.Set;

import com.shs.app.entity.User;
import com.shs.app.entity.UserRole;

public interface UserService {
	User createUser(User user ,Set<UserRole> userRoles) throws Exception;
	User getByName(String username);
	String deleteUserById(Long userid);
	String updateUser(User user ,Set<UserRole> userRoles)throws Exception;
}

package com.shs.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shs.app.entity.User;

public interface IUserRepository extends JpaRepository<User, Long> {

	
	User findByUserName(String userName);
}

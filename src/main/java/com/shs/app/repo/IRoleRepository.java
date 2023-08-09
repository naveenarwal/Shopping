package com.shs.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shs.app.entity.Role;

public interface IRoleRepository extends JpaRepository<Role, Long> {

}

package com.shs.app.serviceImpl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Component;

import com.shs.app.entity.User;
import com.shs.app.entity.UserRole;
import com.shs.app.exceptions.DataNotFoundException;
import com.shs.app.repo.IRoleRepository;
import com.shs.app.repo.IUserRepository;
import com.shs.app.service.UserService;

@Component
public class UserServicesImpl implements UserService {

	@Autowired
	private IUserRepository urepo;

	@Autowired
	private IRoleRepository rrepo;

	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		User local = this.urepo.findByUserName(user.getUsername());

		if (local != null) {
			throw new DataNotFoundException("User with username already Present");
		} else {
			for (UserRole userRole : userRoles) {
				rrepo.save(userRole.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			User save = this.urepo.save(user);
			return save;
		}
	}

	@Override
	public User getByName(String username) {
		User local = this.urepo.findByUserName(username);
		if(local !=null)
		return local;
		
		else {
			throw new DataNotFoundException("No Such user With username => "+username+" found");
		}
	}

	@Override
	public String deleteUserById(Long userid) {
		if(urepo.existsById(userid)) {
			this.urepo.deleteById(userid);
			
			return "User Deleted Succesfully!!";
		}
		else {
			throw new DataNotFoundException("No Such user With userid => "+userid+" found");
		}		
	}

	@Override
	public String updateUser(User user, Set<UserRole> userRoles) throws Exception {
		User local = this.urepo.findByUserName(user.getUsername());

		if (local == null) {
			throw new DataNotFoundException("User Data Cannot Be Null ");
		} else {
			for (UserRole userRole : userRoles) {
				rrepo.save(userRole.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			User save = this.urepo.save(user);
			return "user Update Succesfully";
		}
	}

}

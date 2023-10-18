package org.java.app.db.auth.service;

import java.util.List;

import org.java.app.db.auth.pojo.User;
import org.java.app.db.auth.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	public void save(User user) {
		userRepo.save(user);
	}
	
	public List<User> findAll(){
		return userRepo.findAll();
	}
	
	
	public User findById(int id) {
		return userRepo.findById(id).get();
	}
	
	
	public List<User> deleteById(int id){
		return userRepo.deleteById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return userRepo.findByUsername(username);
	}
}

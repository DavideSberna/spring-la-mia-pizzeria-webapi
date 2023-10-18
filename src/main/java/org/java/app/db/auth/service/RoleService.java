package org.java.app.db.auth.service;

import java.util.List;

import org.java.app.db.auth.pojo.Role;
import org.java.app.db.auth.pojo.User;
import org.java.app.db.auth.repo.RoleRepo;
import org.java.app.db.pojo.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

	@Autowired
	private RoleRepo roleRepo;
	
	public void save(Role role) {
		roleRepo.save(role);
	}
	
	public List<Role> findAll(){
		return roleRepo.findAll();
	}
	
	public Role findById(int id) {
		return roleRepo.findById(id).get();
	}
	
	public List<Role> deleteById(int id){
		return roleRepo.deleteById(id);
	}
}

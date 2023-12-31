package org.java.app.db.auth.repo;

import java.util.List;

import org.java.app.db.auth.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer>{
	
	public List<Role> deleteById(int id);

}

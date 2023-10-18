package org.java.app.db.auth.repo;

import java.util.List;

import org.java.app.db.auth.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	
	public List<User> deleteById(int id);
	
	public User findByUsername(String username);

}

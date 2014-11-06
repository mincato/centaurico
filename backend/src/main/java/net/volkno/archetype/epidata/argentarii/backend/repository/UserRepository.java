package net.volkno.archetype.epidata.argentarii.backend.repository;

import net.volkno.archetype.epidata.argentarii.backend.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);
    
    User findByNtUser(String ntUser);
	
}

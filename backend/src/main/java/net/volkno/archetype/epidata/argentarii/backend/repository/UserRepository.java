package net.volkno.archetype.epidata.argentarii.backend.repository;

import net.volkno.archetype.epidata.argentarii.backend.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);
    
}

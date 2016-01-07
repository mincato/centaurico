package co.centauri.security;

import java.util.Date;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import co.centauri.map.MapBuilder;

@Service
public class UserRepository {
    
    private Map<String, User> usersByUsername;
    private Map<Long, User> usersById;
    
    public UserRepository() {
        User user = new User();
        user.setUsername("admin");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setId(getRandomId());
        user.setEmail("john.smith@centauri.co");
        create(user);
    }

    public User create(User newUser) {        
        usersByUsername = MapBuilder.concurrent(newUser.getUsername(), newUser);
        usersById = MapBuilder.concurrent(newUser.getId(), newUser);
        return newUser;
    }

    public User findByUsername(String username) {
        return usersByUsername.get(username);
    }

    public User findOne(Long userId) {
        return usersById.get(userId);
    }
    
    private static Long getRandomId() {
        return new Random(new Date().getTime()).nextLong();
    }
    
}

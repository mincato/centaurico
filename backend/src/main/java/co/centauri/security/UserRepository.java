package co.centauri.security;

import java.util.Map;

import org.springframework.stereotype.Service;

import co.centauri.map.MapBuilder;
import co.centauri.random.RandomInteger;

@Service
public class UserRepository {
    
    private static Map<String, User> usersByUsername;
    private static Map<Integer, User> usersById;
    
    static {
        User user = new User();
        user.setUsername("admin");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setId(RandomInteger.getNext());
        user.setEmail("john.smith@centauri.co");
        usersByUsername = MapBuilder.concurrent(user.getUsername(), user);
        usersById = MapBuilder.concurrent(user.getId(), user);
    }
    
    public User save(User newUser) {
        usersByUsername.put(newUser.getUsername(), newUser);
        usersById.put(newUser.getId(), newUser);
        return newUser;
    }

    public User findByUsername(String username) {
        return usersByUsername.get(username);
    }

    public User findOne(Long userId) {
        return usersById.get(userId);
    }
    
}

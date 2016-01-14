package co.centauri.security;

import java.text.MessageFormat;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    private AuthenticatorService authenticatorService;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User addUser(User newUser) {

        newUser = userRepository.save(newUser);

        LOGGER.info("User added: " + newUser.getFirstName() + " " + newUser.getLastName());
        return newUser;
    }

    @Transactional
    public User updateUser(User user) {
        user = userRepository.save(user);
        return user;
    }

    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    public User login(String username, String password) {

        LOGGER.info(MessageFormat.format("--- Login: {0} ---", username));

        User user = findByUsername(username);
        if (user == null) {
            throw new ForbiddenException("User not exist in the DB");
        }

        if (!authenticatorService.authenticate(user, password)) {
            throw new ForbiddenException("Invalid Password");
        }
        
        return user;
    }

    @Transactional
    public User register(User newUser) {
        User createdUser = addUser(newUser);
        return createdUser;
    }

    public AuthenticatorService getAuthenticatorService() {
        return authenticatorService;
    }

    public void setAuthenticatorService(AuthenticatorService authenticatorService) {
        this.authenticatorService = authenticatorService;
    }

}

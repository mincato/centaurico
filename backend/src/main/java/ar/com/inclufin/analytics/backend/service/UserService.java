package ar.com.inclufin.analytics.backend.service;

import java.text.MessageFormat;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.inclufin.analytics.backend.model.User;
import ar.com.inclufin.analytics.backend.service.exception.AuthenticationException;
import ar.com.inclufin.analytics.backend.service.exception.ForbiddenException;
import ar.com.inclufin.analytics.backend.service.security.AuthenticatorService;

@Service
public class UserService {

	private static final Logger LOGGER = Logger.getLogger(UserService.class );
	
    private AuthenticatorService authenticatorService;
    
	@Transactional
	public User addUser(User newUser) {
	    
		//newUser = userRepository.create(newUser);
        
        LOGGER.info("User added: " + newUser.getFirstName() + " " + newUser.getLastName());
        return newUser;
	}
	
	@Transactional
    public User updateUser(User user) {
		//user = userRepository.create(user);
        return user;
    }

    public User findByUsername(String username) {
        //User user = userRepository.findByUsername(username);
    	User user = new User();
    	user.setUsername(username);
        return user;
    }
    
    public User login(String username, String password) {

        LOGGER.info(MessageFormat.format("--- Login: {0} ---", username));

        if (!authenticatorService.authenticate(username, password)) {
            throw new AuthenticationException();
        }
        
        User user = findByUsername(username);
        if (user == null) {
            throw new ForbiddenException();
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

package net.volkno.archetype.epidata.argentarii.backend.service;

import java.text.MessageFormat;

import net.volkno.archetype.epidata.argentarii.backend.model.User;
import net.volkno.archetype.epidata.argentarii.backend.repository.UserRepository;
import net.volkno.archetype.epidata.argentarii.backend.service.exception.AuthenticationException;
import net.volkno.archetype.epidata.argentarii.backend.service.exception.ForbiddenException;
import net.volkno.archetype.epidata.argentarii.backend.service.security.AuthenticatorService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	private static final Logger LOGGER = Logger.getLogger(UserService.class );
	
	@Autowired
	private UserRepository userRepository;
    
    private AuthenticatorService authenticatorService;
    
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

    public User findByNick(String nick) {
        User user = userRepository.findByUsername(nick);
        return user;
    }
    
    public User findByUsername(String ntUser) {
        User user = userRepository.findByUsername(ntUser);
        return user;
    }
    
    public User login(String nick, String password) {

        LOGGER.info(MessageFormat.format("--- Login: {0} ---", nick));

        if (!authenticatorService.authenticate(nick, password)) {
            throw new AuthenticationException();
        }
        
        User user = findByUsername(nick);
        if (user == null) {
            //throw new AuthenticationException("User does not exists!");
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

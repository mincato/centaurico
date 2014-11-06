package net.volkno.archetype.epidata.argentarii.backend.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.volkno.archetype.epidata.argentarii.backend.model.User;
import net.volkno.archetype.epidata.argentarii.backend.repository.UserRepository;
import net.volkno.archetype.epidata.argentarii.backend.service.security.TokenHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionHandler {
	
    @Autowired
    private TokenHandler tokenHandler;
    
    @Autowired
    private UserRepository userRepository;

    public void saveUserInSession(HttpServletRequest request, String token, User user) {
    	HttpSession session = request.getSession(true);
    	session.setAttribute(token, user);
    }

    public User loadUserFromSession(HttpServletRequest request, String token, String userId) {
    	User user = null;    	
    	HttpSession session = request.getSession();
    	if (session != null) {
    		user = (User) session.getAttribute(token);
    	}
    	if (user == null) {
    		user = userRepository.findOne(userId);
    		saveUserInSession(request, token, user);
    	}
    	return user;
	}
	
}

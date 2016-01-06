package co.centauri.archetype.backend.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import co.centauri.archetype.backend.model.User;

@Service
public class SessionHandler {
	
/*    @Autowired
    private UserRepository userRepository;*/

    public void saveUserInSession(HttpServletRequest request, String token, User user) {
    	HttpSession session = request.getSession(true);
    	session.setAttribute(token, user);
    }

    public User loadUserFromSession(HttpServletRequest request, String token, Long userId) {
    	User user = null;    	
    	HttpSession session = request.getSession();
    	if (session != null) {
    		user = (User) session.getAttribute(token);
    	}
    	if (user == null) {
    		//user = userRepository.findOne(userId);
    		saveUserInSession(request, token, user);
    	}
    	return user;
	}
	
}

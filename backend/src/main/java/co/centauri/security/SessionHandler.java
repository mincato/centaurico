package co.centauri.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionHandler {

    @Autowired
    private UserRepository userRepository;

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
            user = userRepository.findOne(userId);
            saveUserInSession(request, token, user);
        }
        return user;
    }

    public void removeUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if (session != null) {
            session.invalidate();
        }
    }

}

package co.centauri.security;

import org.springframework.stereotype.Service;

@Service
public class AuthenticatorMockService implements AuthenticatorService {

    @Override
    public boolean authenticate(User user, String password) {
        return true;
    }

}

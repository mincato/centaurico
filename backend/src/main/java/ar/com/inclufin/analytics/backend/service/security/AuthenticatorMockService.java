package ar.com.inclufin.analytics.backend.service.security;

import org.springframework.stereotype.Service;

@Service
public class AuthenticatorMockService implements AuthenticatorService {

	@Override
	public boolean authenticate(String username, String password) {
		return true;
	}

}

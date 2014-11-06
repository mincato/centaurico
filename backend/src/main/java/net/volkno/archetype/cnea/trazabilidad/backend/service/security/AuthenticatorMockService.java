package net.volkno.archetype.cnea.trazabilidad.backend.service.security;

import org.springframework.stereotype.Service;

/**
 * 
 * @author Adrian Paredes
 *
 */
@Service
public class AuthenticatorMockService implements AuthenticatorService {

	@Override
	public boolean authenticate(String username, String password) {
		return true;
	}

}

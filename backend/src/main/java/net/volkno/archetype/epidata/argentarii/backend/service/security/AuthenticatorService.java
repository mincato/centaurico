package net.volkno.archetype.epidata.argentarii.backend.service.security;


public interface AuthenticatorService {

	boolean authenticate(String username, String password);
	
}

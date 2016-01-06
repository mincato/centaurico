package co.centauri.archetype.backend.service.security;


public interface AuthenticatorService {

	boolean authenticate(String username, String password);
	
}

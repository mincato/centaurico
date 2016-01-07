package co.centauri.security;


public interface AuthenticatorService {

	boolean authenticate(String username, String password);
	
}

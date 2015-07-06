package ar.com.inclufin.analytics.backend.service.security;


public interface AuthenticatorService {

	boolean authenticate(String username, String password);
	
}

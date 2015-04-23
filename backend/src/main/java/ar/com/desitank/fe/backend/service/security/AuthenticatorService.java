package ar.com.desitank.fe.backend.service.security;


public interface AuthenticatorService {

	boolean authenticate(String username, String password);
	
}

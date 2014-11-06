package net.volkno.archetype.cnea.trazabilidad.backend.service.security;


public interface AuthenticatorService {

	boolean authenticate(String username, String password);
	
}

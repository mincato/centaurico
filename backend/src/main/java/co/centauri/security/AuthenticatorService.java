package co.centauri.security;

public interface AuthenticatorService {

    boolean authenticate(User user, String password);

}

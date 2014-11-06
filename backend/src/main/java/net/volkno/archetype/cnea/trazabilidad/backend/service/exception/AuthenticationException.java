package net.volkno.archetype.cnea.trazabilidad.backend.service.exception;

public class AuthenticationException extends RuntimeException {

    private static final long serialVersionUID = -5672567588735793025L;
    
    public AuthenticationException() {
    }
    
    public AuthenticationException(String message) {
        super(message);
    }
    
    public AuthenticationException(Throwable t) {
        super(t);
    }
    
}

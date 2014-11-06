package net.volkno.archetype.cnea.trazabilidad.backend.service.exception;

public class RefreshTokenException extends RuntimeException {

    private static final long serialVersionUID = -877720068698717425L;

    public RefreshTokenException(Throwable t) {
        super(t);
    }
}

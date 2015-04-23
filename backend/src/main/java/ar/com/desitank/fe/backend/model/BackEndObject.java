package ar.com.desitank.fe.backend.model;

import java.io.Serializable;

import ar.com.desitank.fe.backend.util.JSONObjectConverter;

public abstract class BackEndObject implements Serializable {

    private static final long serialVersionUID = -3704224041413428471L;
    
    @Override
    public String toString() {
        return JSONObjectConverter.convertToJSON(this);
    }
    
}

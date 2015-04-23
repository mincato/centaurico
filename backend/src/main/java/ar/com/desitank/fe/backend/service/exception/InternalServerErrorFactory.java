package ar.com.desitank.fe.backend.service.exception;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class InternalServerErrorFactory {

    private Map<String, InternalServerError> exceptionMapping;
    
    private static final String UNKNOWN_ERROR_CODE = "Unknown";
    
    public InternalServerError buildInternalError(Exception e) {
        String className = e.getClass().getName();
        InternalServerError error = exceptionMapping.get(className);
        if (error == null) {
        	error = exceptionMapping.get(UNKNOWN_ERROR_CODE);
        }
        return error;
    }
    
    public Map<String, InternalServerError> getExceptionMapping() {
		return exceptionMapping;
	}
    
    public void setExceptionMapping(Map<String, InternalServerError> exceptionMapping) {
		this.exceptionMapping = exceptionMapping;
	}
    
}

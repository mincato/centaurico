package ar.com.inclufin.analytics.backend.service.aspect;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.inclufin.analytics.backend.dto.UserData;
import ar.com.inclufin.analytics.backend.service.exception.UnauthorizedException;
import ar.com.inclufin.analytics.backend.util.RequestHandler;

@Service
public class AuthorizationFilter implements ContainerRequestFilter {
    
    @Context
    private HttpServletRequest request;
    
    @Autowired
    private RequestHandler requestHandler;
    
    private static final Logger LOGGER = Logger.getLogger(AuthorizationFilter.class);
    
    private List<PublicRule> publicMethodsRules;
    
    public void filter(ContainerRequestContext context) {
        
        try {
            UserData userData = null;
            if (isPublicMethod(request.getMethod(), request.getRequestURI())) {
                userData = requestHandler.getUserFromRequestInfo(request);
            } else {
                userData = requestHandler.verifyToken(request);
                if (userData == null || StringUtils.isEmpty(userData.getUsername())) {
                    throw new UnauthorizedException("Token verified, but User is invalid!");
                }
            }
            if (userData != null) {
                requestHandler.saveUserInRequestInfo(request, userData);
            }
        } catch (UnauthorizedException e) {
            LOGGER.error("Exception in AuthorizationFilter", e);
            Response unauthorizedResponse = Response.status(Status.UNAUTHORIZED).build();
            context.abortWith(unauthorizedResponse);
        }
    }
    
    private boolean isPublicMethod(String httpMethod, String uri) {
        for (PublicRule publicRule : publicMethodsRules) {
            if (httpMethod.equals(publicRule.getHttpMethod())) {
                for (String endpoint : publicRule.getEndpoints()) {
                    if (uri.matches(endpoint)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public List<PublicRule> getPublicMethodsRules() {
        return publicMethodsRules;
    }
    
    public void setPublicMethodsRules(List<PublicRule> publicMethodsRules) {
        this.publicMethodsRules = publicMethodsRules;
    }
 
}

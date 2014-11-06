package net.volkno.archetype.epidata.argentarii.backend.service.aspect;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.volkno.archetype.epidata.argentarii.backend.dto.UserData;
import net.volkno.archetype.epidata.argentarii.backend.service.exception.UnauthorizedException;
import net.volkno.archetype.epidata.argentarii.backend.util.RequestHandler;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.message.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationFilter implements org.apache.cxf.jaxrs.ext.RequestHandler {
    
    @Context
    private HttpServletRequest request;
    
    @Autowired
    private RequestHandler requestHandler;
    
    private static final Logger LOGGER = Logger.getLogger(AuthorizationFilter.class);
    
    private List<PublicRule> publicMethodsRules;
    
    public Response handleRequest(Message message, ClassResourceInfo resourceClass) {
        
        try {
            UserData userData = null;
            if (isPublicMethod(request.getMethod(), request.getRequestURI())) {
                userData = requestHandler.getUserFromRequestInfo(request);
            } else {
                userData = requestHandler.verifyToken(request);
                if (userData == null || StringUtils.isEmpty(userData.getEmail())) {
                    throw new UnauthorizedException("Token verified, but User is invalid!");
                }
            }
            if (userData != null) {
                requestHandler.saveUserInRequestInfo(request, userData);
            }
            return null;
        } catch (UnauthorizedException e) {
            LOGGER.error("Exception in AuthorizationFilter", e);
            return Response.status(Status.UNAUTHORIZED).build();
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

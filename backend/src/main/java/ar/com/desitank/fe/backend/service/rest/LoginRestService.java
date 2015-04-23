package ar.com.desitank.fe.backend.service.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.desitank.fe.backend.dto.Credential;
import ar.com.desitank.fe.backend.dto.UserData;
import ar.com.desitank.fe.backend.model.User;
import ar.com.desitank.fe.backend.service.UserService;
import ar.com.desitank.fe.backend.service.security.TokenHandler;
import ar.com.desitank.fe.backend.util.RestResponseHandler;
import ar.com.desitank.fe.backend.util.SessionHandler;

@Service
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginRestService {

    @Autowired
    private UserService userService;
    
    @Autowired
    private RestResponseHandler responseHandler;
    
    @Autowired
    private SessionHandler sessionHandler;
    
    @Autowired
    private TokenHandler tokenHandler;
    
    @POST
    @Path("/login")
    public Response login(@Context HttpServletRequest request,
            Credential credential) {
        try {
            User user = userService.login(credential.getUsername(), credential.getPassword());
            configureUser(user, request);
            return responseHandler.buildSuccessResponse(user, Status.OK);

        } catch (Exception e) {
            return responseHandler.buildErrorResponse(e);
        }
    }
    
    @POST
    @Path("/register")
    public Response addUser(User newUser, @Context HttpServletRequest request) {
        try {
            User user = userService.register(newUser);
            configureUser(user, request);
            return responseHandler.buildSuccessResponse(user, Status.CREATED);
        } catch (Exception e) {
            return responseHandler.buildErrorResponse(e);
        }
    }
    
    private void configureUser(User user, HttpServletRequest request) {
        String token = tokenHandler.createToken(new UserData(user));
        sessionHandler.saveUserInSession(request, token, user);
        user.setToken(token);
    }

}

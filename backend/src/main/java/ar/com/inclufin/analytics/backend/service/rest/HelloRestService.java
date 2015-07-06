package ar.com.inclufin.analytics.backend.service.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.inclufin.analytics.backend.util.RestResponseHandler;

@Service
@Path("/hello")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HelloRestService {

    @Autowired
    private RestResponseHandler responseHandler;
    
    @GET
    public Response hello(@Context HttpServletRequest request) {
        try {
            return responseHandler.buildSuccessResponse("{ \"text\": \"Hello. I'm OK!\" }", Status.OK);
        } catch (Exception e) {
            return responseHandler.buildErrorResponse(e);
        }            
    }    
}

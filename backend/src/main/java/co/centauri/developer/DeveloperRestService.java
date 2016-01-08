package co.centauri.developer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.centauri.RestResponseHandler;

@Service
@Path("/developers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DeveloperRestService {

    @Autowired
    private RestResponseHandler responseHandler;

    @Autowired
    private DeveloperService developerService;

    @GET
    public Response find(@Context HttpServletRequest request) {
        try {
            List<Developer> developers = developerService.find();
            return responseHandler.buildSuccessResponse(developers, Status.OK);
        } catch (Exception e) {
            return responseHandler.buildErrorResponse(e);
        }
    }

    @GET
    @Path("{id}")
    public Response find(@Context HttpServletRequest request, @PathParam("id") Integer id) {
        try {
            Developer developer = developerService.find(id);
            return responseHandler.buildSuccessResponse(developer, Status.OK);
        } catch (Exception e) {
            return responseHandler.buildErrorResponse(e);
        }
    }

    @POST
    public Response save(@Context HttpServletRequest request, Developer newDeveloper) {
        try {
            Developer developerSaved = developerService.save(newDeveloper);
            return responseHandler.buildSuccessResponse(developerSaved, Status.OK);
        } catch (Exception e) {
            return responseHandler.buildErrorResponse(e);
        }
    }
    
    @PUT
    @Path("{id}")
    public Response update(@Context HttpServletRequest request, Developer developer) {
        try {
            Developer developerUpdated = developerService.update(developer);
            return responseHandler.buildSuccessResponse(developerUpdated, Status.OK);
        } catch (Exception e) {
            return responseHandler.buildErrorResponse(e);
        }
    }
    
    @DELETE
    @Path("{id}")
    public Response delete(@Context HttpServletRequest request, @PathParam("id") Integer id) {
        try {
            developerService.delete(id);
            return responseHandler.buildSuccessResponse(Status.OK);
        } catch (Exception e) {
            return responseHandler.buildErrorResponse(e);
        }        
    }
}

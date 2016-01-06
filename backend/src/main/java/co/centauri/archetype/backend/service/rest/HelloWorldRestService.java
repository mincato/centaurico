package co.centauri.archetype.backend.service.rest;

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

import co.centauri.archetype.backend.model.HelloWorld;
import co.centauri.archetype.backend.service.HelloWorldService;
import co.centauri.archetype.backend.util.RestResponseHandler;

@Service
@Path("/hello")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldRestService {

    @Autowired
    private RestResponseHandler responseHandler;

    @Autowired
    private HelloWorldService helloWorldService;

    @GET
    public Response sayHello(@Context HttpServletRequest request) {
        try {
            HelloWorld helloWorld = helloWorldService.sayHello();
            return responseHandler.buildSuccessResponse(helloWorld, Status.OK);
        } catch (Exception e) {
            return responseHandler.buildErrorResponse(e);
        }
    }
}

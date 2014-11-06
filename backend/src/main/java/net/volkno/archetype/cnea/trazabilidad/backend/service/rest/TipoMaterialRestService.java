package net.volkno.archetype.cnea.trazabilidad.backend.service.rest;

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

import net.volkno.archetype.cnea.trazabilidad.backend.model.tipo.TipoMaterial;
import net.volkno.archetype.cnea.trazabilidad.backend.service.TipoMaterialService;
import net.volkno.archetype.cnea.trazabilidad.backend.util.RestResponseHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Path("/tipo-materiales")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TipoMaterialRestService {

	@Autowired
	private TipoMaterialService tipoMaterialService;
	
    @Autowired
    private RestResponseHandler responseHandler;
	
	@POST
	public Response createTipoMaterial(@Context HttpServletRequest request, TipoMaterial tipoMaterial) {
        try {
        	tipoMaterial = tipoMaterialService.createTipoMaterial(tipoMaterial);
            return responseHandler.buildSuccessResponse(tipoMaterial, Status.CREATED);
        } catch (Exception e) {
            return responseHandler.buildErrorResponse(e);
        }
	}
	
    @PUT
    @Path("{id}")
    public Response updateTipoMaterial(@Context HttpServletRequest request, TipoMaterial tipoMaterial) {
        try {
        	tipoMaterial = tipoMaterialService.update(tipoMaterial);
            return responseHandler.buildSuccessResponse(tipoMaterial, Status.ACCEPTED);
        } catch (Exception e) {
            return responseHandler.buildErrorResponse(e);
        }            
    }
 
    @DELETE
    @Path("{id}")
    public Response deleteTipoMaterial(@Context HttpServletRequest request, @PathParam("id") String id) {
        try {
            TipoMaterial tipoMaterial = tipoMaterialService.find(id);
            tipoMaterialService.delete(tipoMaterial);
            return responseHandler.buildSuccessResponse(Status.ACCEPTED);
        } catch (Exception e) {
            return responseHandler.buildErrorResponse(e);
        }            
    }
    
    @GET
    @Path("{id}")
    public Response getMaterial(@Context HttpServletRequest request, @PathParam("id") String id) {
        try {
            TipoMaterial tipoMaterial = tipoMaterialService.find(id);
            return responseHandler.buildSuccessResponse(tipoMaterial, Status.OK);
        } catch (Exception e) {
            return responseHandler.buildErrorResponse(e);
        }    	
    }
	
	@GET
	public Response getAll(@Context HttpServletRequest request) {
        try {
        	List<TipoMaterial> tipoMateriales = tipoMaterialService.getAll();
            return responseHandler.buildSuccessResponse(tipoMateriales, Status.OK);
        } catch (Exception e) {
            return responseHandler.buildErrorResponse(e);
        }
	}
	
}

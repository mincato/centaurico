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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.volkno.archetype.cnea.trazabilidad.backend.model.Material;
import net.volkno.archetype.cnea.trazabilidad.backend.service.MaterialService;
import net.volkno.archetype.cnea.trazabilidad.backend.util.RequestHandler;
import net.volkno.archetype.cnea.trazabilidad.backend.util.RestResponseHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Path("/materiales")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MaterialRestService {
    
    @Autowired
    private MaterialService materialService;
    
    @Autowired
    private RestResponseHandler responseHandler;
    
    @Autowired
    private RequestHandler requestHandler;
    
    @POST
    public Response createMaterial(@Context HttpServletRequest request, Material material) {
        try {
            material = materialService.createMaterial(material);
            return responseHandler.buildSuccessResponse(material, Status.CREATED);
        } catch (Exception e) {
            return responseHandler.buildErrorResponse(e);
        }            
    }

    @PUT
    @Path("{id}")
    public Response updateMaterial(@Context HttpServletRequest request, Material material) {
        try {
            material = materialService.updateMaterial(material);
            return responseHandler.buildSuccessResponse(material, Status.ACCEPTED);
        } catch (Exception e) {
            return responseHandler.buildErrorResponse(e);
        }            
    }
 
    @DELETE
    @Path("{id}")
    public Response deleteMaterial(@Context HttpServletRequest request, @PathParam("id") String id) {
        try {
            Material material = materialService.getMaterial(id);
            materialService.deleteMaterial(material);
            return responseHandler.buildSuccessResponse(Status.ACCEPTED);
        } catch (Exception e) {
            return responseHandler.buildErrorResponse(e);
        }            
    }
    
    @GET
    @Path("{id}")
    public Response getMaterial(@Context HttpServletRequest request, @PathParam("id") String id) {
        try {
            Material material = materialService.getMaterial(id);
            return responseHandler.buildSuccessResponse(material, Status.OK);
        } catch (Exception e) {
            return responseHandler.buildErrorResponse(e);
        }    	
    }
    
    @GET
    public Response getAll(@Context HttpServletRequest request,
    		@QueryParam("nombre") String nombre,
    		@QueryParam("tipoMaterialId") String tipoMaterialId) {
        try {
            List<Material> materiales = materialService.find(nombre, tipoMaterialId);
            return responseHandler.buildSuccessResponse(materiales, Status.OK);
        } catch (Exception e) {
            return responseHandler.buildErrorResponse(e);
        }
    }

}

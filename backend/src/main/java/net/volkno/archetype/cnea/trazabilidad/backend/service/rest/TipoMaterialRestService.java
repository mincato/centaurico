package net.volkno.archetype.cnea.trazabilidad.backend.service.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
	public Response createMaterial(@Context HttpServletRequest request, TipoMaterial tipoMaterial) {
        try {
        	tipoMaterial = tipoMaterialService.createTipoMaterial(tipoMaterial);
            return responseHandler.buildSuccessResponse(tipoMaterial, Status.CREATED);
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

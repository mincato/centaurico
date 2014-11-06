package net.volkno.archetype.epidata.argentarii.backend.service.rest;

import java.io.InputStream;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.volkno.archetype.epidata.argentarii.backend.dto.UploadResponse;
import net.volkno.archetype.epidata.argentarii.backend.util.FileUtil;
import net.volkno.archetype.epidata.argentarii.backend.util.RestResponseHandler;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Path("/upload")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UploadDocRestService {

    @Autowired
    private RestResponseHandler responseHandler;
    
    @Value("${documents.dir}")
    private String directory;
    
    @Autowired
    private FileUtil fileUtil;
    
    @POST
    @Path("docs")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadImage(@Context HttpServletRequest request, Attachment attachment) {
        try {
        	String completeFileName = saveFile(attachment);
            return responseHandler.buildSuccessResponse(
            		Arrays.asList(new UploadResponse(
            				Status.OK.name(), completeFileName)), Status.OK);
        } catch (Exception e) {
            return responseHandler.buildErrorResponse(e);
        }
    }
    
    private String saveFile(Attachment attachment) throws Exception {
    	InputStream is = attachment.getDataHandler().getInputStream();
    	String fileName = attachment.getContentDisposition().getParameter("filename");
    	return fileUtil.saveToFile(directory, fileName, is);
    }    
     
}

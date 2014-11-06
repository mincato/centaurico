package net.volkno.archetype.cnea.trazabilidad.backend.service.aspect;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import net.volkno.archetype.cnea.trazabilidad.backend.util.RequestHandler;

import org.apache.cxf.jaxrs.model.OperationResourceInfo;
import org.apache.cxf.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseInfoPicker implements org.apache.cxf.jaxrs.ext.ResponseHandler {
    
    @Context
    private HttpServletRequest request;
    
    @Autowired
    private RequestHandler requestHandler;

    @Override
    public Response handleResponse(Message outputMessage,
            OperationResourceInfo invokedOperation, Response response) {
        
        RequestInfo requestInfo = requestHandler.getRequestInfoOrCreateNew(request);
        requestInfo.setResponseStatus(response.getStatus());
        requestInfo.setResponseEntity(response.getEntity());
        
        requestInfo.setEnd(new Date());
        requestInfo.setDuration(calculateDuration(requestInfo));
        
        requestHandler.saveRequestInfo(request, requestInfo);
        return null;
    }
    
    private Long calculateDuration(final RequestInfo requestInfo) {
        Date start = requestInfo.getStart();
        Date end = requestInfo.getEnd();
        if (start == null || end == null) {
            return null;
        }
        return end.getTime() - start.getTime();
    }

}

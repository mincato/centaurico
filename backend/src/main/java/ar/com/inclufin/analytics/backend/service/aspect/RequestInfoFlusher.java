package ar.com.inclufin.analytics.backend.service.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.model.OperationResourceInfo;
import org.apache.cxf.message.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.inclufin.analytics.backend.util.RequestHandler;

@Service
public class RequestInfoFlusher implements org.apache.cxf.jaxrs.ext.ResponseHandler {
    
    private static final Logger CONSOLE_LOGGER = Logger.getLogger("requestInfoConsoleLogger");
    private static final Logger FILE_LOGGER = Logger.getLogger("requestInfoFileLogger");
    
    @Context
    private HttpServletRequest request;
    
    @Autowired
    private RequestHandler requestHandler;

    @Override
    public Response handleResponse(Message message,
            OperationResourceInfo invokedOperation, Response response) {
        
        String requestInfoJSON = null;

        RequestInfo requestInfo = requestHandler
                .getRequestInfoOrCreateNew(request);

        try {
            requestInfoJSON = requestInfo.toJSON();

        } catch (Exception e) {
            requestInfoJSON = "{\"error\" : \"unable to serialize request\"}";
        }            
            
        CONSOLE_LOGGER.debug("requestInfo = " + requestInfoJSON);
        FILE_LOGGER.info(requestInfoJSON);
            

        
        return null;
    }

}

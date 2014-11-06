package net.volkno.archetype.epidata.argentarii.backend.service.aspect;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import net.volkno.archetype.epidata.argentarii.backend.util.RequestHandler;

import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestInfoPicker implements org.apache.cxf.jaxrs.ext.RequestHandler {

    @Context
    private HttpServletRequest request;
    
    @Autowired
    private RequestHandler requestHandler;
    
    @Override
    public Response handleRequest(Message inputMessage, ClassResourceInfo resourceClass) {

        RequestInfo requestInfo = requestHandler.getRequestInfoOrCreateNew(request);
        requestInfo.setStart(new Date());
        requestInfo.setHttpMethod(request.getMethod());
        requestInfo.setIpAddress(request.getRemoteAddr());
        requestInfo.setEndpoint(request.getRequestURI());
        requestInfo.setHeader(buildHeader(request));
        
        requestHandler.saveRequestInfo(request, requestInfo);
        
        return null;
    }
    
    private Map<String, String> buildHeader(HttpServletRequest request) {
        
        Map<String, String> header = new HashMap<String, String>();
        
        Enumeration<String> keys = request.getHeaderNames();
        if (keys != null) {
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                if (!key.equals("authorization")) {
                    header.put(key, request.getHeader(key));
                }
            }
        }
        
        return header;
    }
}

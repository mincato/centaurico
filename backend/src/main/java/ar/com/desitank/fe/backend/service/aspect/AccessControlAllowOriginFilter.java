package ar.com.desitank.fe.backend.service.aspect;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.model.OperationResourceInfo;
import org.apache.cxf.message.Message;
import org.springframework.stereotype.Service;

@Service
public class AccessControlAllowOriginFilter implements org.apache.cxf.jaxrs.ext.ResponseHandler {

	@Override
	public Response handleResponse(Message message, OperationResourceInfo opResInfo, Response response) {
		response.getHeaders().putSingle("Access-Control-Allow-Origin", "http://localhost:3000");
		response.getHeaders().putSingle("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		response.getHeaders().putSingle("Access-Control-Allow-Headers", "Content-Type, X-Requested-With");
		return response;
	}


}

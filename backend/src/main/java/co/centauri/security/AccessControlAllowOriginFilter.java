package co.centauri.security;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import org.springframework.stereotype.Service;

@Service
public class AccessControlAllowOriginFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext inContext, ContainerResponseContext outContext) {
        outContext.getHeaders().putSingle("Access-Control-Allow-Origin", "http://localhost:3000");
        outContext.getHeaders().putSingle("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        outContext.getHeaders().putSingle("Access-Control-Allow-Headers",
                "Content-Type, X-Requested-With, Authorization");
        outContext.getHeaders().putSingle("Access-Control-Allow-Credentials", "true");
    }
}

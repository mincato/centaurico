/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.volkno.archetype.cnea.trazabilidad.backend.model.audit;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.hibernate.envers.RevisionListener;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class CustomRevisionListener implements RevisionListener {
	
    private static final Logger LOGGER = Logger.getLogger(CustomRevisionListener.class);
    
    @Override
    public void newRevision(Object revisionEntity) {
        try {
            CustomRevisionEntity customRevisionEntity = (CustomRevisionEntity) revisionEntity;
            
            ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes());
            HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
            
            String ipaddress = httpServletRequest.getRemoteAddr();
            
            customRevisionEntity.setIpaddress(ipaddress);
        } catch (Throwable throwable) { //Si hay problemas, nos quedamos sin ip logueada, pero sigue funcionando
            LOGGER.error("Fallo al determinar IP desde el request para auditoria", throwable);
        }
    }
    
}

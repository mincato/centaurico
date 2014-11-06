/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.volkno.archetype.epidata.argentarii.backend.model.audit;

import javax.persistence.Entity;

import org.hibernate.envers.DefaultTrackingModifiedEntitiesRevisionEntity;
import org.hibernate.envers.RevisionEntity;

@Entity
@RevisionEntity(CustomRevisionListener.class)
public class CustomRevisionEntity extends DefaultTrackingModifiedEntitiesRevisionEntity {

	private static final long serialVersionUID = 2774443596153738630L;
	
	private String ipaddress;

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }
    
    
}

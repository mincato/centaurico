package net.volkno.archetype.cnea.trazabilidad.backend.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatorLDAPService implements AuthenticatorService {

	@Autowired
	private LdapTemplate ldapTemplate;
	
	private String filterAttribute;
	
	@Override
	public boolean authenticate(String username, String password) {
		Filter filter = new EqualsFilter(filterAttribute, username);
		return ldapTemplate.authenticate("", filter.encode(), password);
	}
	
	public String getFilterAttribute() {
		return filterAttribute;
	}
	
	public void setFilterAttribute(String filterAttribute) {
		this.filterAttribute = filterAttribute;
	}
}

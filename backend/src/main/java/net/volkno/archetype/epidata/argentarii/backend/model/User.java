package net.volkno.archetype.epidata.argentarii.backend.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.volkno.archetype.epidata.argentarii.backend.util.GenericPredicate;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "users")
@SQLDelete(sql="UPDATE User SET state = '0' WHERE id = ?")
@Where(clause="state = '2'")
@JsonIgnoreProperties(ignoreUnknown = true)
@Audited
public class User extends BackEndEntity {

    private static final long serialVersionUID = 1294418189803999949L;

	private String username;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    
    @Transient
    private String password;
    
    private String language;
    private String token;
    
    private String ntUser; //FIXME: debería ser transient quizas, para que no se serialice
    
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Rol> roles;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}

    /**
     * @return the ntUser
     */
    public String getNtUser() {
        return ntUser;
    }

    /**
     * @param ntUser the ntUser to set
     */
    public void setNtUser(String ntUser) {
        this.ntUser = ntUser;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }
    
    public boolean tieneAlgunPermiso(Collection<String> permisos) {
    	if (CollectionUtils.isNotEmpty(roles)) {
    		for (Rol rol : roles) {
    			for (String permiso : permisos) {
    				Predicate predicate = new GenericPredicate("nombre", permiso);
    				if (CollectionUtils.find(rol.getPermisos(), predicate) != null) {
    					return true;
    				}
    			}
    		}
    	}
    	return false;
    }

}

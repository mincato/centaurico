package co.centauri.security;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import co.centauri.BackEndEntity;
import co.centauri.predicate.EqualsPropertyPredicate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends BackEndEntity {

    private static final long serialVersionUID = 1294418189803999949L;

    private String username;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;

    private String password;

    private String language;
    private String token;

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
                    Predicate predicate = new EqualsPropertyPredicate("nombre", permiso);
                    if (CollectionUtils.find(rol.getPermisos(), predicate) != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}

package net.volkno.archetype.epidata.argentarii.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;

import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

@MappedSuperclass
@Audited
public abstract class BackEndEntity extends BackEndObject {

    private static final long serialVersionUID = -5354058460706112830L;

    @Id
    @Column(unique = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @JsonProperty("_id")
    private String id;

    private EntityState state = EntityState.ACTIVE;

    @Temporal(javax.persistence.TemporalType.DATE) //TemporalType.TIMESTAMP?
    private Date fechaCreacion;
    
    private String usuarioCreacionId;
    
    @Temporal(javax.persistence.TemporalType.DATE) //TemporalType.TIMESTAMP?
    private Date fechaActualizacion;
    
    private String usuarioActualizoId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EntityState getState() {
        return state;
    }

    public void setState(EntityState state) {
        this.state = state;
    }

    /**
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuarioCreacionId() {
		return usuarioCreacionId;
	}
    
    public void setUsuarioCreacionId(String usuarioCreacionId) {
		this.usuarioCreacionId = usuarioCreacionId;
	}

    /**
     * @return the fechaActualizacion
     */
    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    /**
     * @param fechaActualizacion the fechaActualizacion to set
     */
    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getUsuarioActualizoId() {
		return usuarioActualizoId;
	}
    
    public void setUsuarioActualizoId(String usuarioActualizoId) {
		this.usuarioActualizoId = usuarioActualizoId;
	}
    
    //Actualizamos la fecha de Creacion
    @PrePersist
    protected void onCreate() {
        //usuarioCreacion = FIXME;  //Seteado más arriba, lamentablemente
        fechaCreacion = new Date();
    }

    //Actualizamos la fecha de actualización
    @PreUpdate
    protected void onUpdate() {
        //usuarioActualizo = FIXME;  //Seteado más arriba, lamentablemente
        fechaActualizacion = new Date();
    }
    
}

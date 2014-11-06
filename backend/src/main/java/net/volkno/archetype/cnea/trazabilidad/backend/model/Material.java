package net.volkno.archetype.cnea.trazabilidad.backend.model;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.volkno.archetype.cnea.trazabilidad.backend.model.tipo.TipoMaterial;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "materiales")
@SQLDelete(sql = "UPDATE Material SET state = '0' WHERE id = ?")
@Where(clause = "state = '2'")
@JsonIgnoreProperties(ignoreUnknown = true)
@Audited
public class Material extends BackEndEntity {

    private static final long serialVersionUID = 1660233966320240190L;

    private String nombre;
    private String descripcion;
    private boolean requiereCertificado;
    private boolean contable; //FIXME: herencia
    private String magnitud; //FIXME: herencia

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_material_tipo_material"), name = "tipo_material_id")
    private TipoMaterial tipoMaterial;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoMaterial getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(TipoMaterial tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    /**
     * @return the requiereCertificado
     */
    public boolean isRequiereCertificado() {
        return requiereCertificado;
    }

    /**
     * @param requiereCertificado the requiereCertificado to set
     */
    public void setRequiereCertificado(boolean requiereCertificado) {
        this.requiereCertificado = requiereCertificado;
    }

    /**
     * @return the contable
     */
    public boolean isContable() {
        return contable;
    }

    /**
     * @param contable the contable to set
     */
    public void setContable(boolean contable) {
        this.contable = contable;
    }

    /**
     * @return the magnitud
     */
    public String getMagnitud() {
        return magnitud;
    }

    /**
     * @param magnitud the magnitud to set
     */
    public void setMagnitud(String magnitud) {
        this.magnitud = magnitud;
    }

}

package net.volkno.archetype.cnea.trazabilidad.backend.model.tipo;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "tipo_materiales")
@SQLDelete(sql = "UPDATE TipoMaterial SET state = '0' WHERE id = ?")
@Where(clause = "state <> '0'")
@PrimaryKeyJoinColumn(name = "tipo_id")
@JsonIgnoreProperties(ignoreUnknown = true)
@Audited
public class TipoMaterial extends Tipo {

    private static final long serialVersionUID = -5400247113612164203L;

    
}

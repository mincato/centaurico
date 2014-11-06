package net.volkno.archetype.cnea.trazabilidad.backend.model.tipo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import net.volkno.archetype.cnea.trazabilidad.backend.model.BackEndEntity;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

/**
 * 
 * @author Adrian Paredes
 *
 */
@Entity
@Table(name = "tipos")
@Where(clause="state <> '0'")
@SQLDelete(sql="UPDATE Tipo SET state = '0' WHERE id = ?")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties(ignoreUnknown = true)
@Audited
public abstract class Tipo extends BackEndEntity {

	private static final long serialVersionUID = 1033348952152982832L;
	
	@Column(unique = true)
	private String codigo;
	private String valor;
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}

}

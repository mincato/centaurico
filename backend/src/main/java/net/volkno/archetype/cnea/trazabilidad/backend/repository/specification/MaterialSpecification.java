package net.volkno.archetype.cnea.trazabilidad.backend.repository.specification;

import net.volkno.archetype.cnea.trazabilidad.backend.model.Material;

import org.springframework.data.jpa.domain.Specification;

public class MaterialSpecification {

	public static Specification<Material> andIgnoringBlanks(String nombre, String tipoMaterialId) {
		return IgnoreNullSpecifications
				.where(nombreEsIgualQue(nombre))
				.and(tipoMaterialIdEsIgualQue(tipoMaterialId))
				.getSpecification();
	}

	public static Specification<Material> nombreEsIgualQue(String nombre) {
		return CommonSpecification.attrEsIgualQue("nombre", nombre);
	}
	
	public static Specification<Material> tipoMaterialIdEsIgualQue(final String tipoMaterialId) {
		return CommonSpecification.tipoMaterialIdEsIgualQue(tipoMaterialId);
	}
	
}
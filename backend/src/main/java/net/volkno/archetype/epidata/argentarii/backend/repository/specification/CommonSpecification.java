package net.volkno.archetype.epidata.argentarii.backend.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class CommonSpecification {

	public static <T> Specification<T> attrEsIgualQue(final String attr, final String value) {
		
		if (StringUtils.isBlank(value)) {
			return null;
		}
		
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get(attr), value);
			}
		};
	}
	
	public static <T> Specification<T> tipoMaterialIdEsIgualQue(final String tipoMaterialId) {
		
		if (StringUtils.isBlank(tipoMaterialId)) {
			return null;
		}
		
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {				
				return cb.equal(root.get("tipoMaterial").get("id"), tipoMaterialId);
			}
		};
	}
	
}

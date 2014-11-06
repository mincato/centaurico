package net.volkno.archetype.cnea.trazabilidad.backend.repository;

import net.volkno.archetype.cnea.trazabilidad.backend.model.tipo.TipoMaterial;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoMaterialRepository extends JpaRepository<TipoMaterial, String> {

}

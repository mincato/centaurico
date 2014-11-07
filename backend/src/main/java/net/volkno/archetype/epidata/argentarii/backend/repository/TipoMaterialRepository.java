package net.volkno.archetype.epidata.argentarii.backend.repository;

import net.volkno.archetype.epidata.argentarii.backend.model.tipo.TipoMaterial;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TipoMaterialRepository extends MongoRepository<TipoMaterial, String> {

}

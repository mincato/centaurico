package net.volkno.archetype.cnea.trazabilidad.backend.service;

import java.util.List;

import net.volkno.archetype.cnea.trazabilidad.backend.model.tipo.TipoMaterial;
import net.volkno.archetype.cnea.trazabilidad.backend.repository.TipoMaterialRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TipoMaterialService {

	@Autowired
	private TipoMaterialRepository tipoMaterialRepository;

	@Transactional
	public TipoMaterial createTipoMaterial(TipoMaterial tipoMaterial) {
		return tipoMaterialRepository.save(tipoMaterial);
	}
	
	public List<TipoMaterial> getAll() {
		return tipoMaterialRepository.findAll();
	}

	@Transactional
	public TipoMaterial update(TipoMaterial tipoMaterial) {
		return tipoMaterialRepository.save(tipoMaterial); 
	}

	public TipoMaterial find(String id) {
		return tipoMaterialRepository.findOne(id);
	}

	public void delete(TipoMaterial tipoMaterial) {
		tipoMaterialRepository.delete(tipoMaterial);
	}
	
}

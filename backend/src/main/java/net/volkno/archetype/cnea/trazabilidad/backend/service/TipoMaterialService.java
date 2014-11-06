package net.volkno.archetype.cnea.trazabilidad.backend.service;

import java.util.List;

import net.volkno.archetype.cnea.trazabilidad.backend.model.tipo.TipoMaterial;
import net.volkno.archetype.cnea.trazabilidad.backend.repository.TipoMaterialRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Adrian Paredes
 *
 */
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
	
}

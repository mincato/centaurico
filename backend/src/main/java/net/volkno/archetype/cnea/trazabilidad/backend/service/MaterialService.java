/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.volkno.archetype.cnea.trazabilidad.backend.service;

import java.util.List;

import net.volkno.archetype.cnea.trazabilidad.backend.model.Material;
import net.volkno.archetype.cnea.trazabilidad.backend.repository.MaterialRepository;
import net.volkno.archetype.cnea.trazabilidad.backend.repository.specification.MaterialSpecification;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MaterialService {

    private static final Logger LOGGER = Logger.getLogger(MaterialService.class);

    @Autowired
    private MaterialRepository materialRepository;

    @Transactional
    public Material createMaterial(Material material) {
        material = materialRepository.save(material);

        LOGGER.info("Material added: " + material.toString());
        return material;
    }

    @Transactional
    public Material updateMaterial(Material material) {
        material = materialRepository.save(material);
        
        LOGGER.info("Material updated: " + material.toString());
        return material;
    }

    @Transactional
    public void deleteMaterial(Material material) {
        materialRepository.delete(material);
        LOGGER.info("Material deleted: " + material.toString());
    }
    
    public Material getMaterial(String id) {
        Material material = materialRepository.findOne(id);
        if (material==null){
            LOGGER.info("No Material found id " + id);
        } else {
            LOGGER.info("Material found id " + id + " : " + material.toString());
        }
        return material;
    }

    public List<Material> find(String nombre, String tipoMaterialId) {
    	
        List<Material> materiales = materialRepository.findAll(
        		MaterialSpecification.andIgnoringBlanks(nombre, tipoMaterialId));
        
		if (materiales == null || materiales.isEmpty()) {
			LOGGER.info("Materiales not found");
		} else {
			LOGGER.info("Materiales found count " + materiales.size());
		}
        return materiales;
    }


}

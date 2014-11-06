/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.volkno.archetype.epidata.argentarii.backend.repository;

import net.volkno.archetype.epidata.argentarii.backend.model.Material;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MaterialRepository  extends JpaRepository<Material, String>, JpaSpecificationExecutor<Material> {

}
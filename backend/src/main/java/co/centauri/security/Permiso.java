/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.centauri.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import co.centauri.BackEndEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Permiso extends BackEndEntity {

	private static final long serialVersionUID = -3553958359504695800L;

	private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

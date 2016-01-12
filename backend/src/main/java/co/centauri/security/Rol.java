/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.centauri.security;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import co.centauri.BackEndEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rol extends BackEndEntity {

    private static final long serialVersionUID = -655321606681663452L;

    private String nombre;

    private List<Permiso> permisos;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Permiso> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<Permiso> permisos) {
        this.permisos = permisos;
    }

}

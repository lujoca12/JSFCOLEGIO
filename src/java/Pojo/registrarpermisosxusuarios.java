/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojo;

import java.util.Set;

/**
 *
 * @author server
 */
public class registrarpermisosxusuarios implements java.io.Serializable {
    private int id;
     private Permiso permiso;
     private Usuario usuario;
     private Character estado;

    public registrarpermisosxusuarios() {
    }

    public registrarpermisosxusuarios(int id, Permiso permiso, Usuario usuario, Character estado) {
       this.id = id;
       this.permiso = permiso;
       this.usuario = usuario;
       this.estado = estado;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Permiso getPermiso() {
        return this.permiso;
    }
    
    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Character getEstado() {
        return this.estado;
    }
    
    public void setEstado(Character estado) {
        this.estado = estado;
    }
}

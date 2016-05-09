/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.Date;

/**
 *
 * @author SERVER
 */
public class ClsEstudiante {
     private int id,colegiatura;
     private String cedula;
     private String nombres;
     private String apellidos;
     private String email,celular,pais;
     private Date fechanaci;
     private boolean sexo, estadocivil;

    public ClsEstudiante(int id, String nombres, String apellidos) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }
     
     

    public ClsEstudiante(int id, int colegiatura, String cedula, String nombres, String apellidos, String email, String celular, String pais, Date fechanaci, boolean sexo, boolean estadocivil) {
        this.id = id;
        this.colegiatura = colegiatura;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.celular = celular;
        this.pais = pais;
        this.fechanaci = fechanaci;
        this.sexo = sexo;
        this.estadocivil = estadocivil;
    }

    public ClsEstudiante() {
    }

     
     
     
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getColegiatura() {
        return colegiatura;
    }

    public void setColegiatura(int colegiatura) {
        this.colegiatura = colegiatura;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Date getFechanaci() {
        return fechanaci;
    }

    public void setFechanaci(Date fechanaci) {
        this.fechanaci = fechanaci;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public boolean isEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(boolean estadocivil) {
        this.estadocivil = estadocivil;
    }
     
     
     
     
}

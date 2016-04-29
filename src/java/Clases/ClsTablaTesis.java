/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.Serializable;
import Pojo.Maestria;
import Pojo.Estudiante;
/**
 *
 * @author SERVER
 */
public class ClsTablaTesis implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private int idTesis;
    private String autor;
    private String titulo;
    private String fechaSus;
    private String fechaSubida;
    private String ruta;
    private String resumen;
    private Maestria idMaestria;
    private Estudiante idEstudiante;

    public ClsTablaTesis(int idTesis, String autor, String titulo, String ruta, String resumen, Maestria idMaestria, Estudiante idEstudiante) {
        this.idTesis = idTesis;
        this.autor = autor;
        this.titulo = titulo;
        this.ruta = ruta;
        this.resumen = resumen;
        this.idMaestria = idMaestria;
        this.idEstudiante = idEstudiante;
    }

    
    
    
    public ClsTablaTesis(int idTesis, String autor, String titulo, String fechaSus, String fechaSubida, String ruta, String resumen, Maestria idMaestria, Estudiante idEstudiante) {
        this.idTesis = idTesis;
        this.autor = autor;
        this.titulo = titulo;
        this.fechaSus = fechaSus;
        this.fechaSubida = fechaSubida;
        this.ruta = ruta;
        this.resumen = resumen;
        this.idMaestria = idMaestria;
        this.idEstudiante = idEstudiante;
    }

    public int getIdTesis() {
        return idTesis;
    }

    public void setIdTesis(int idTesis) {
        this.idTesis = idTesis;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechaSus() {
        return fechaSus;
    }

    public void setFechaSus(String fechaSus) {
        this.fechaSus = fechaSus;
    }

    public String getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(String fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public Maestria getIdMaestria() {
        return idMaestria;
    }

    public void setIdMaestria(Maestria idMaestria) {
        this.idMaestria = idMaestria;
    }

    public Estudiante getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Estudiante idEstudiante) {
        this.idEstudiante = idEstudiante;
    }
    
    
    
    
}

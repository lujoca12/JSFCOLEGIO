/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.Serializable;
import java.util.Date;
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
    private Date fechaSus;
    private Date fechaSubida;
    private String ruta;
    private String resumen;
    private String Maestria;
    private String Tutor;
    private Character Estado;
    private int idtitulacion;
    private int idusuario;

    public ClsTablaTesis(int idTesis, String autor, String titulo, Date fechaSus, Date fechaSubida, String ruta, String resumen, String Maestria, String Tutor, Character Estado, int idtitulacion, int idusuario) {
        this.idTesis = idTesis;
        this.autor = autor;
        this.titulo = titulo;
        this.fechaSus = fechaSus;
        this.fechaSubida = fechaSubida;
        this.ruta = ruta;
        this.resumen = resumen;
        this.Maestria = Maestria;
        this.Tutor = Tutor;
        this.Estado = Estado;
        this.idtitulacion = idtitulacion;
        this.idusuario = idusuario;
    }
    
    

    public ClsTablaTesis(int idTesis, String titulo) {
        this.idTesis = idTesis;
        this.titulo = titulo;
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

    public Date getFechaSus() {
        return fechaSus;
    }

    public void setFechaSus(Date fechaSus) {
        this.fechaSus = fechaSus;
    }

    public Date getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(Date fechaSubida) {
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

    public String getMaestria() {
        return Maestria;
    }

    public void setMaestria(String Maestria) {
        this.Maestria = Maestria;
    }

    public String getTutor() {
        return Tutor;
    }

    public void setTutor(String Tutor) {
        this.Tutor = Tutor;
    }

    public Character getEstado() {
        return Estado;
    }

    public void setEstado(Character Estado) {
        this.Estado = Estado;
    }

    public int getIdtitulacion() {
        return idtitulacion;
    }

    public void setIdtitulacion(int idtitulacion) {
        this.idtitulacion = idtitulacion;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }
    
    
    
    
}

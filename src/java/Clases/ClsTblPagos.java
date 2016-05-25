/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author chiti
 */
public class ClsTblPagos implements Serializable {
    private static final long serialVersionUID = 1L;
    private int idPago;
    private String valor;
    private Date fecha;
    private String idComprobante;
    private int idTipoPago;
    private int idMatricula;

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(String idComprobante) {
        this.idComprobante = idComprobante;
    }

    public int getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(int idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    public ClsTblPagos(int idPago, String valor, Date fecha, String idComprobante, int idTipoPago, int idMatricula) {
        this.idPago = idPago;
        this.valor = valor;
        this.fecha = fecha;
        this.idComprobante = idComprobante;
        this.idTipoPago = idTipoPago;
        this.idMatricula = idMatricula;
    }
    
    
    
    
    
}

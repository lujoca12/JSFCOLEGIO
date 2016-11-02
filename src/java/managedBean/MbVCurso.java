/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Dao.DaoTCurso;
import Dao.DaoTModalidad;
import Dao.DaoTSeccion;
import Pojo.Curso;
import Pojo.Modalidad;
import Pojo.Seccion;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author server
 */
@Named(value = "mbVCurso")
@ViewScoped
public class MbVCurso implements Serializable{

    /**
     * Creates a new instance of MbVCurso
     */
    private Curso tCurso;
    private Seccion tSeccion;
    private boolean msg = false;
    private boolean mostrarEliminados;
    private List<Curso> lstCurso;
    private List<SelectItem> cboSeccion;
    private BigDecimal precioMatricula;
    private BigDecimal precioColegiatura;
    private List<SelectItem> lstTodosParalelos;
    public MbVCurso() {
        tCurso = new Curso();
        tSeccion = new Seccion();
        cargarParalelos();
        cargarCboSeccion();
        cargarTablaSeccion();
    }
    public void cargarTablaSeccion(){
        
        try {
            lstCurso = new ArrayList<>();
            DaoTCurso daoCurso = new DaoTCurso();
            lstCurso = daoCurso.getTodosCursos();
        } catch (Exception ex) {
            Logger.getLogger(MbVCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void cargarCboSeccion() {
        try {
            
            cboSeccion = new ArrayList<>();
            DaoTSeccion daoSeccion = new DaoTSeccion();
            List<Seccion> seccion = daoSeccion.getSeccion();
            for (Seccion s : seccion) {
                SelectItem item = new SelectItem(s.getId(), s.getDescripcion());
                cboSeccion.add(item);
            }
        } catch (Exception ex) {
            
        }

    }
    public void cargarParalelos() {
        this.lstTodosParalelos = new ArrayList<SelectItem>();
        try {
            String[] modulos = {
                "A", "B", "C", "D", "E",
                "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O",
                "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y",
                "Z"
            };
            
            for (int i = 0; i < modulos.length; i++) {
                SelectItem usuarioItem = new SelectItem(modulos[i].toString(), modulos[i].toString());
                this.lstTodosParalelos.add(usuarioItem);
            }
        } catch (Exception ex) {

        }
    }
    
    private void mensajesOk(String msg){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    private void mensajesError(String msg){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public void registrar(){
        DaoTCurso daoCurso = new DaoTCurso();
        boolean repetida = false;
        boolean band = false;
        try {
            tCurso.setEstado('1');
            tCurso.setSeccion(tSeccion);
            repetida = daoCurso.existe(tCurso);
            if (!repetida) {
                msg = daoCurso.registrarCursoPrecio(tCurso, precioMatricula, precioColegiatura);
            }else{
                mensajesError("Registro repetido");
                cargarTablaSeccion();
                return;
            }
            
            if (msg) {
                mensajesOk("Datos procesados correctamente");
                vaciarCajas();
                cargarTablaSeccion();    
            } else {
                mensajesError("Error al procesar datos");
            }
        

        } catch (Exception e) {
            vaciarCajas();
        }
        
    }
    private void vaciarCajas(){
        tCurso = new Curso();
        tSeccion = new Seccion();
        cargarParalelos();
        precioMatricula = null;
        precioColegiatura = null;
        cargarCboSeccion();
    }
    public void onRowEdit(RowEditEvent event) {
        boolean repetida = false;
        DaoTCurso daoCurso = new DaoTCurso();
        Curso curso = new Curso();
        
        try {
            curso = (Curso) event.getObject();
            
            repetida = daoCurso.existe(curso);
            if (!repetida) {
                 msg = daoCurso.update(curso);
            }else{
                mensajesError("Registro repetido");
                //cargarTablaMaterias();
                return;
            }
            if (msg) {
                mensajesOk("Datos actualizados correctamente");
                   
            } else {
                mensajesError("Error al procesar datos");
            }
            cargarTablaSeccion(); 
        } catch (Exception ex) {
            cargarTablaSeccion(); 
            
        }
        
    }
    
    public void onRowCancel(RowEditEvent event) {
        
        
    }

    public Curso gettCurso() {
        return tCurso;
    }

    public void settCurso(Curso tCurso) {
        this.tCurso = tCurso;
    }

    public Seccion gettSeccion() {
        return tSeccion;
    }

    public void settSeccion(Seccion tSeccion) {
        this.tSeccion = tSeccion;
    }

    public boolean isMostrarEliminados() {
        return mostrarEliminados;
    }

    public void setMostrarEliminados(boolean mostrarEliminados) {
        this.mostrarEliminados = mostrarEliminados;
    }

    public List<Curso> getLstCurso() {
        return lstCurso;
    }

    public void setLstCurso(List<Curso> lstCurso) {
        this.lstCurso = lstCurso;
    }

    public List<SelectItem> getCboSeccion() {
        return cboSeccion;
    }

    public void setCboSeccion(List<SelectItem> cboSeccion) {
        this.cboSeccion = cboSeccion;
    }

    public BigDecimal getPrecioMatricula() {
        return precioMatricula;
    }

    public void setPrecioMatricula(BigDecimal precioMatricula) {
        this.precioMatricula = precioMatricula;
    }

    public BigDecimal getPrecioColegiatura() {
        return precioColegiatura;
    }

    public void setPrecioColegiatura(BigDecimal precioColegiatura) {
        this.precioColegiatura = precioColegiatura;
    }

    public List<SelectItem> getLstTodosParalelos() {
        return lstTodosParalelos;
    }

    public void setLstTodosParalelos(List<SelectItem> lstTodosParalelos) {
        this.lstTodosParalelos = lstTodosParalelos;
    }
    
    
}

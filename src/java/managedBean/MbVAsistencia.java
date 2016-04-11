/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Clases.ClsNotas;
import Clases.ClsTablaModulosRegistrados;
import Dao.DaoTMatricula;
import Dao.DaoTModulo;
import Pojo.Asistencia;
import Pojo.Matricula;
import Pojo.Modulo;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author server
 */
@Named(value = "mbVAsistencia")
@ViewScoped
public class MbVAsistencia implements Serializable{

    private Asistencia tAsistencia;
    
    private ClsTablaModulosRegistrados clsTblModulosReg;
    private List<ClsTablaModulosRegistrados> lstCboModulos;
    
    private ClsNotas clsNotas;
    private List<ClsNotas> lstTblNotas;
    
    
    private String cedula="";
    private String estudiante="";
    private int idProm = 0;
    private int idModulo=0;
    private boolean msg;
    
    public MbVAsistencia() {
        tAsistencia = new Asistencia();
        cargarCboModulos();
    }

    public ClsTablaModulosRegistrados getClsTblModulosReg() {
        return clsTblModulosReg;
    }

    public void setClsTblModulosReg(ClsTablaModulosRegistrados clsTblModulosReg) {
        this.clsTblModulosReg = clsTblModulosReg;
    }

    public List<ClsTablaModulosRegistrados> getLstCboModulos() {
        return lstCboModulos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public int getIdProm() {
        return idProm;
    }

    public void setIdProm(int idProm) {
        this.idProm = idProm;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public Asistencia gettAsistencia() {
        return tAsistencia;
    }

    public void settAsistencia(Asistencia tAsistencia) {
        this.tAsistencia = tAsistencia;
    }

    public ClsNotas getClsNotas() {
        return clsNotas;
    }

    public void setClsNotas(ClsNotas clsNotas) {
        this.clsNotas = clsNotas;
    }

    public List<ClsNotas> getLstTblNotas() {
        return lstTblNotas;
    }
    
    public void cargarCboModulos(){
        lstCboModulos = new ArrayList<>();
        try {
            lstCboModulos.clear();
            DaoTModulo daoTmodulo = new DaoTModulo();
            List<Modulo> lstModulo = daoTmodulo.getCboModulosNotas();
            
            if(lstModulo != null){
                if(lstModulo.size() > 0){
                    for(Modulo modulo : lstModulo){
                        lstCboModulos.add(new ClsTablaModulosRegistrados(modulo.getPromocion().getMaestria().getId(), 
                                modulo.getPromocion().getMaestria().getDescripcion()+" (Dir.(a)"+modulo.getPromocion().getUsuarios()+")", 
                                modulo.getPromocion().getId(), 
                                modulo.getDescripcion()+" ("+modulo.getPromocion().getMaestria().getDescripcion()+")", 
                                modulo.getUsuario().getId(), 
                                modulo.getUsuario().getApellidos()+" "+modulo.getUsuario().getNombres(), 
                                modulo.getCreditos(), 
                                modulo.getId()));
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cargarTablaRegAsistencia(){
        lstTblNotas = new ArrayList<>();
        try {
            lstTblNotas.clear();
            DaoTMatricula daoTmatricula = new DaoTMatricula();
            List<Matricula> lstMatricula = daoTmatricula.getMatriculaRegNotas(this.idModulo);
            Calendar calendar = Calendar.getInstance();
            int añoInicio = 0;
            int añoFin = 0;
            
                if(lstMatricula.size() > 0){
                    for(Matricula matricula : lstMatricula){
                        calendar.setTime(matricula.getPromocion().getFechaInicio());
                        añoInicio = calendar.get(Calendar.YEAR);
                        
                        calendar.setTime(matricula.getPromocion().getFechaFin());
                        añoFin = calendar.get(Calendar.YEAR);
                        estudiante = matricula.getSolicitudInscripcion().getEstudiante().getApellidos()+" "+matricula.getSolicitudInscripcion().getEstudiante().getNombres();
                        
                        lstTblNotas.add(new ClsNotas(matricula.getSolicitudInscripcion().getEstudiante().getId(), 
                                matricula.getSolicitudInscripcion().getEstudiante().getApellidos()+" "+matricula.getSolicitudInscripcion().getEstudiante().getNombres(), 
                                matricula.getId(), 
                                matricula.getNMatricula(), 
                                matricula.getFechaMatricula(), 
                                matricula.getPromocion().getId(), 
                                añoInicio+"-"+añoFin, 
                                matricula.getPromocion().getFechaResolucion(), 
                                matricula.getPromocion().getMaestria().getId(), 
                                matricula.getPromocion().getMaestria().getDescripcion()));
                    }
                }
            
            
        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

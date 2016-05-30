/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Clases.ClsHorarioModulo;
import Clases.ClsMaestria;
import Clases.ClsTablaModulosRegistrados;
import Dao.DaoTHorarioModulo;
import Dao.DaoTMaestrias;
import Dao.DaoTModulo;
import Pojo.HorarioModulo;
import Pojo.Maestria;
import Pojo.Modulo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author server
 */
@Named(value = "mbVModulosHorarios")
@ViewScoped
public class MbVModulosHorarios implements Serializable{

    private ClsMaestria themeMaestria; 
    private List<ClsMaestria> lstThemeMaestria;
    
    private ClsTablaModulosRegistrados clsTblModulosReg;
    private List<ClsTablaModulosRegistrados> lstCboModulos;
    
    private ClsHorarioModulo clsTblHorarioModulo;
    private List<ClsHorarioModulo> lstTblHorarioModulo;
    
    private boolean msg = false;
    private HorarioModulo tHorarioModulo;
    
    private boolean estado = false;
    private String horasModulo;
    private String horasAsignadas;
    private String horasxRegistrar;
    
    public MbVModulosHorarios() {
        tHorarioModulo = new HorarioModulo();
        llenarCboMaestria();
        cargarTablaHorarioModulos();
    }

    public ClsMaestria getThemeMaestria() {
        return themeMaestria;
    }

    public void setThemeMaestria(ClsMaestria themeMaestria) {
        this.themeMaestria = themeMaestria;
    }

    public List<ClsMaestria> getLstThemeMaestria() {
        return lstThemeMaestria;
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

    public HorarioModulo gettHorarioModulo() {
        return tHorarioModulo;
    }

    public void settHorarioModulo(HorarioModulo tHorarioModulo) {
        this.tHorarioModulo = tHorarioModulo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public ClsHorarioModulo getClsTblHorarioModulo() {
        return clsTblHorarioModulo;
    }

    public void setClsTblHorarioModulo(ClsHorarioModulo clsTblHorarioModulo) {
        this.clsTblHorarioModulo = clsTblHorarioModulo;
    }

    public List<ClsHorarioModulo> getLstTblHorarioModulo() {
        return lstTblHorarioModulo;
    }

    public String getHorasModulo() {
        return horasModulo;
    }

    public void setHorasModulo(String horasModulo) {
        this.horasModulo = horasModulo;
    }

    public String getHorasAsignadas() {
        return horasAsignadas;
    }

    public void setHorasAsignadas(String horasAsignadas) {
        this.horasAsignadas = horasAsignadas;
    }

    public String getHorasxRegistrar() {
        return horasxRegistrar;
    }

    public void setHorasxRegistrar(String horasxRegistrar) {
        this.horasxRegistrar = horasxRegistrar;
    }
    
    public void llenarCboMaestria(){
        this.lstThemeMaestria = new ArrayList<ClsMaestria>();
         try {
            DaoTMaestrias daoTmaestria = new DaoTMaestrias();
            
            List<Maestria> lstMaestria = daoTmaestria.getMaestrias();
            this.lstThemeMaestria.clear();
            this.lstThemeMaestria.add(new ClsMaestria(-1,"Ninguno","Ninguno",0,0,0, null, null));
            
            for(Maestria maestria: lstMaestria){
                this.lstThemeMaestria.add(new ClsMaestria(maestria.getId(),
                        maestria.getDescripcion(),
                        maestria.getDescripcion(),
                        maestria.getId(),
                        0,
                        0,
                        null,
                        null));
            }
        } catch (Exception ex) {
            
        }
    }
    
    public void onMaestriaChange() throws Exception {
        try {
            
            lstCboModulos = new ArrayList<>();
            lstCboModulos.clear();
            this.lstCboModulos.add(new ClsTablaModulosRegistrados(-1, "(Escoja un Módulo)", -1, "(Escoja un Módulo)", -1, "(Escoja un Módulo)","(Escoja un Módulo)",-1,"(Escoja un Módulo)",null,null,null,null,""));
            DaoTModulo daoTmodulo = new DaoTModulo();
            List<Modulo> lstModulo = null;
            
            if(themeMaestria != null)
                lstModulo = daoTmodulo.getTblModulosMaestria(themeMaestria.getId());
            else{
                lstCboModulos.clear();
                estado = false;
            }
                
            
            if (lstModulo != null) {
                if (lstModulo.size() > 0) {
                    for (Modulo modulo : lstModulo) {
                        lstCboModulos.add(new ClsTablaModulosRegistrados(modulo.getPromocion().getMaestria().getId(),
                                modulo.getDescripcion() + " (" + modulo.getPromocion().getMaestria().getDescripcion() + ")",
                                modulo.getPromocion().getId(),
                                modulo.getModulo()+": "+ modulo.getDescripcion() + " (Dir.(a)" + modulo.getPromocion().getUsuario() + ")",
                                modulo.getUsuario().getId(),
                                modulo.getUsuario().getApellidos() + " " + modulo.getUsuario().getNombres(),
                                modulo.getCreditos().toString(),
                                modulo.getId(),
                                modulo.getModulo(),
                                modulo.getFechaInicio() == null ? null : modulo.getFechaInicio(),
                                modulo.getFechaFin() == null ? null : modulo.getFechaFin(),
                                modulo.getFechaInicioExamen() == null ? null : modulo.getFechaInicioExamen(),
                                modulo.getFechaFinExamen() == null ? null : modulo.getFechaFinExamen(),
                                modulo.getTotalHorasModulo() == null ? null : modulo.getTotalHorasModulo().toString()
                        ));
                    }
                   
                }
            }
            
            
        } catch (Exception ex) {
            
        }

    }
    
    public void cargarTablaHorarioModulos() {
        lstTblHorarioModulo = new ArrayList<>();
        //Time horaInicio = null, horaFin = null;
        
        try {
            lstTblHorarioModulo.clear();
            DaoTHorarioModulo daoThorariomodulo = new DaoTHorarioModulo();
            List<HorarioModulo> lstHorario = daoThorariomodulo.getTblHorarios();
            if(lstHorario != null){
                if(lstHorario.size()>0){
                    for(HorarioModulo horario: lstHorario){
//                        horaInicio = new Time(horario.getHoraInicio().getTime());
//                        horaFin = new Time(horario.getHoraFin().getTime());
                        lstTblHorarioModulo.add(new ClsHorarioModulo(horario.getModulo().getPromocion().getMaestria().getId(), 
                                horario.getModulo().getPromocion().getMaestria().getDescripcion(), 
                                horario.getModulo().getId(), 
                                horario.getModulo().getModulo()+": "+horario.getModulo().getDescripcion()+ " (Dir.(a)" + horario.getModulo().getPromocion().getUsuario() + ")",
                                horario.getModulo().getCreditos().toString(), 
                                horario.getModulo().getFechaInicio() == null ? null : horario.getModulo().getFechaInicio(), 
                                horario.getModulo().getFechaFin() == null ? null : horario.getModulo().getFechaFin(), 
                                horario.getModulo().getModulo(), 
                                horario.getModulo().getTotalHorasModulo() == null ? null : horario.getModulo().getTotalHorasModulo().toString(),
                                horario.getId(), 
                                horario.getHoraInicio(),
                                horario.getHoraFin(),
                                horario.getFecha() == null ? null : horario.getFecha()));
                    }
                }
            }
            
        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void onModuloChange() throws Exception {
        if (clsTblModulosReg != null) {
            estado = true;
            movimientoHoras();
            
        } else {
            estado = false;
        }
    }
    
    private void movimientoHoras(){
        horasModulo = clsTblModulosReg.getTotalHorasModulo();
            if(!horasModulo.isEmpty()){
                DaoTHorarioModulo daoThorarioModulo = new DaoTHorarioModulo();
            try {
                horasAsignadas = daoThorarioModulo.getTotalHorasAsignadas(clsTblModulosReg.getIdModulo());
            } catch (Exception ex) {
                Logger.getLogger(MbVModulosHorarios.class.getName()).log(Level.SEVERE, null, ex);
            }
                if(!horasAsignadas.isEmpty()){
                    horasxRegistrar = String.valueOf(Double.parseDouble(horasModulo) -  Double.parseDouble(horasAsignadas));
                }else{
                    horasAsignadas = "0";
                    horasxRegistrar = horasModulo;
                }
                    
            }
    }
    
    private void edicMovimientoHoras(String totalHoras, int idModulo){
        horasModulo = totalHoras;
            if(!horasModulo.isEmpty()){
                DaoTHorarioModulo daoThorarioModulo = new DaoTHorarioModulo();
            try {
                horasAsignadas = daoThorarioModulo.getTotalHorasAsignadas(idModulo);
            } catch (Exception ex) {
                Logger.getLogger(MbVModulosHorarios.class.getName()).log(Level.SEVERE, null, ex);
            }
                if(!horasAsignadas.isEmpty()){
                    horasxRegistrar = String.valueOf(Double.parseDouble(horasModulo) -  Double.parseDouble(horasAsignadas));
                }else{
                    horasAsignadas = "0";
                    horasxRegistrar = horasModulo;
                }
                    
            }
    }
    
    public void registrar(){
        boolean repetida = false;
        try {
            BigDecimal bigdec;
            DaoTHorarioModulo daoThorariomodulo = new DaoTHorarioModulo();
            Modulo modulo = new Modulo();
            modulo.setId(clsTblModulosReg.getIdModulo());
            tHorarioModulo.setModulo(modulo);
            
            long tiempoInicial= tHorarioModulo.getHoraInicio().getTime();
            long tiempoFinal = tHorarioModulo.getHoraFin().getTime();
            long resta = tiempoFinal - tiempoInicial;
            
            
            
            //el metodo getTime te devuelve en mili segundos para saberlo en mins debes hacer
            resta = resta / (1000 * 60);
            
            
            if(resta >= 60){
                Long l = new Long(resta);
                double minutos = l.doubleValue();
                double hora = ((minutos-(minutos%60))/60)+((minutos%60)/100);
                
                bigdec = new BigDecimal(hora);
                tHorarioModulo.setHora(bigdec);
                repetida = daoThorariomodulo.existe(tHorarioModulo);
                if (!repetida) {
                    
                    if(hora > 0 && hora <= Double.parseDouble(horasxRegistrar)){
                        msg = daoThorariomodulo.registrar(tHorarioModulo);
                    }
                    else{
                        mensajesError("error! el total de horas no pueden ser mayor a "+horasxRegistrar+"");
                        return;
                    }
                }
                if (repetida) {
                    mensajesError("Registro repetido");
                } else {
                    if (msg) {
                        mensajesOk("Datos procesados correctamente");
                    } else {
                        mensajesError("Error al procesar datos");
                    }
                    vaciarCajas();
                    cargarTablaHorarioModulos();
                    movimientoHoras();
                }
                
            }else{
                mensajesError("Error en definición de hora inicio y fin");
            }
            
        } catch (Exception ex) {
            Logger.getLogger(MbVModulosHorarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void validarFecha(){
        if(tHorarioModulo.getFecha().before(this.clsTblModulosReg.getFechaInicio())){
            tHorarioModulo.setFecha(this.clsTblModulosReg.getFechaInicio());
            mensajesOk("Le fecha no puede ser menor a "+this.clsTblModulosReg.getFechaInicio()+"");
        }else if(tHorarioModulo.getFecha().after(this.clsTblModulosReg.getFechaInicio())){
            tHorarioModulo.setFecha(this.clsTblModulosReg.getFechaFin());
            mensajesOk("La fecha no puede ser mayor a "+this.clsTblModulosReg.getFechaFin()+"");
        }
    }
    
    public void onRowEdit(RowEditEvent event) {
        boolean repetida = false;
        BigDecimal bigdec;
        DaoTHorarioModulo daoThorariomodulo = new DaoTHorarioModulo();
        Modulo modulo = new Modulo();
        HorarioModulo horarioModulo = new HorarioModulo();
        modulo.setId(((ClsHorarioModulo) event.getObject()).getIdModulo());
        horarioModulo.setModulo(modulo);
        horarioModulo.setId(((ClsHorarioModulo) event.getObject()).getIdHorario());
        horarioModulo.setFecha(((ClsHorarioModulo) event.getObject()).getFechaHorario());
        horarioModulo.setHoraInicio(((ClsHorarioModulo) event.getObject()).getHoraInicio());
        horarioModulo.setHoraFin(((ClsHorarioModulo) event.getObject()).getHoraFin());
        
        long tiempoInicial = horarioModulo.getHoraInicio().getTime();
        long tiempoFinal = horarioModulo.getHoraFin().getTime();
        long resta = tiempoFinal - tiempoInicial;
            
            //el metodo getTime te devuelve en mili segundos para saberlo en mins debes hacer
            resta = resta / (1000 * 60);
            
            
            if(resta >= 60){
            try {
                Long l = new Long(resta);
                double minutos = l.doubleValue();
                double hora = ((minutos-(minutos%60))/60)+((minutos%60)/100);
                bigdec = new BigDecimal(hora);
                horarioModulo.setHora(bigdec);
                repetida = daoThorariomodulo.existe(horarioModulo);
                if (!repetida) {
                    edicMovimientoHoras(((ClsHorarioModulo) event.getObject()).getTotalHoras(), ((ClsHorarioModulo) event.getObject()).getIdModulo());
                    if(hora > 0 && hora <= Double.parseDouble(horasxRegistrar)){
                        if (tHorarioModulo.getFecha() != null) {
                            if (tHorarioModulo.getFecha().before(this.clsTblModulosReg.getFechaInicio())) {
                                mensajesOk("Le fecha no puede ser menor a " + this.clsTblModulosReg.getFechaInicio() + "");
                                return;
                            } else if (tHorarioModulo.getFecha().after(this.clsTblModulosReg.getFechaInicio())) {
                                mensajesOk("La fecha no puede ser mayor a " + this.clsTblModulosReg.getFechaFin() + "");
                                return;
                            } else {
                                msg = daoThorariomodulo.registrar(horarioModulo);
                            }
                        }else{
                                msg = daoThorariomodulo.registrar(horarioModulo);
                            }
                    }
                    else{
                        mensajesError("error! el total de horas no pueden ser mayor a "+horasxRegistrar+"");
                        return;
                    }
                }
                if (repetida) {
                    mensajesError("Registro repetido");
                } else {
                    if (msg) {
                        mensajesOk("Datos Actualizados correctamente");
                    } else {
                        mensajesError("Error al actualizar datos");
                    }
                    vaciarCajas();
                    edicMovimientoHoras(((ClsHorarioModulo) event.getObject()).getTotalHoras(), ((ClsHorarioModulo) event.getObject()).getIdModulo());
                }
            } catch (Exception ex) {
                Logger.getLogger(MbVModulosHorarios.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            }else{
                mensajesError("Error en definición de hora inicio y fin");
            }
        cargarTablaHorarioModulos();
    }
    
    public void onRowCancel(RowEditEvent event) {

    }
    
    public void onDelete(ClsHorarioModulo clsTblHorarios) {
        DaoTHorarioModulo daoThorariomodulo = new DaoTHorarioModulo();
        HorarioModulo horarioModulo = new HorarioModulo();
        horarioModulo.setId(clsTblHorarios.getIdHorario());
        try {
            msg = daoThorariomodulo.delete(horarioModulo);
        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        cargarTablaHorarioModulos();
        if (msg) {
            mensajesOk("Dato eliminado correctamente");
        } else {
            mensajesError("Error al eliminar dato");
        }
    }
    
    private void vaciarCajas() {
        
        tHorarioModulo.setHoraInicio(null);
        tHorarioModulo.setHoraFin(null);

    }
    
    private void mensajesOk(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private void mensajesError(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}

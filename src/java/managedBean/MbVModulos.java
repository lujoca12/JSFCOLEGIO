/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Clases.ClsMaestria;
import Clases.ClsProfesor;
import Clases.ClsTablaModulosRegistrados;
import Dao.DaoTMaestrias;
import Dao.DaoTModulo;
import Dao.DaoTPromocion;
import Dao.DaoTUsuario;
import Pojo.Maestria;
import Pojo.Modulo;
import Pojo.Promocion;
import Pojo.Usuario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
@Named(value = "mbVModulos")
@ViewScoped
public class MbVModulos implements Serializable {

    private List<SelectItem> lstMaestria;

    private ClsProfesor theme;
    private List<ClsProfesor> lstTheme;

    private ClsMaestria themeMaestria;
    private List<ClsMaestria> lstThemeMaestria;

    private List<SelectItem> lstTodosModulos;

    private ClsTablaModulosRegistrados clsTblModulosReg;
    private List<ClsTablaModulosRegistrados> lstTblModulosReg;
    private List<ClsTablaModulosRegistrados> filteredCars;

    private int idMaestria;
    private int idDocente;
    private String moduloDescripcion;
    private boolean msg;
    private Modulo tModulo;
    private List<Modulo> lstModulo;
    private String creditos;
    private String totalHorasModulo;
    
    private Date fechaInicio;
    private Date fechaFin;
    boolean repetida;

    public MbVModulos() {
        cargarTablaModulos();
        tModulo = new Modulo();
        llenarCboDocente();
        llenarCboMaestria();
        //getTblModuloRegistrados();
    }

    public List<ClsTablaModulosRegistrados> getFilteredCars() {
        return filteredCars;
    }

    public void setFilteredCars(List<ClsTablaModulosRegistrados> filteredCars) {
        this.filteredCars = filteredCars;
    }

    public int getIdMaestria() {
        return idMaestria;
    }

    public void setIdMaestria(int idMaestria) {
        this.idMaestria = idMaestria;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public Modulo gettModulo() {
        return tModulo;
    }

    public void settModulo(Modulo tModulo) {
        this.tModulo = tModulo;
    }

    public List<ClsProfesor> getLstTheme() {

        return lstTheme;
    }

    public ClsProfesor getTheme() {
        return theme;
    }

    public void setTheme(ClsProfesor theme) {
        this.theme = theme;
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

    public String getModuloDescripcion() {
        return moduloDescripcion;
    }

    public void setModuloDescripcion(String moduloDescripcion) {
        this.moduloDescripcion = moduloDescripcion;
    }

    public List<ClsTablaModulosRegistrados> getLstTblModulosReg() {
        return lstTblModulosReg;
    }

    public ClsTablaModulosRegistrados getClsTblModulosReg() {
        return clsTblModulosReg;
    }

    public void setClsTblModulosReg(ClsTablaModulosRegistrados clsTblModulosReg) {
        this.clsTblModulosReg = clsTblModulosReg;
    }

    public String getCreditos() {
        return creditos;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }

    public String getTotalHorasModulo() {
        return totalHorasModulo;
    }

    public void setTotalHorasModulo(String totalHorasModulo) {
        this.totalHorasModulo = totalHorasModulo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    public List<SelectItem> getLstTodosModulos() {
        this.lstTodosModulos = new ArrayList<SelectItem>();
        try {
            String[] modulos = {
                "Mòdulo I", "Mòdulo II", "Mòdulo III", "Mòdulo IV", "Mòdulo V",
                "Mòdulo VI", "Mòdulo VII", "Mòdulo VIII", "Mòdulo IX", "Mòdulo X",
                "Mòdulo XI", "Mòdulo XII", "Mòdulo XIII", "Mòdulo XIV", "Mòdulo XV",
                "Mòdulo XVI", "Mòdulo XVII", "Mòdulo XVIII", "Mòdulo XIX", "Mòdulo XX",
                "Mòdulo XXI", "Mòdulo XXII", "Mòdulo XXIII", "Mòdulo XXIV", "Mòdulo XXV",
                "Proyecto de Tesis"
            };
            lstTodosModulos.clear();
            for (int i = 0; i < modulos.length; i++) {
                SelectItem usuarioItem = new SelectItem(modulos[i].toString(), modulos[i].toString());
                this.lstTodosModulos.add(usuarioItem);
            }
        } catch (Exception ex) {

        }
        return lstTodosModulos;
    }

    public void cargarTablaModulos() {
        lstTblModulosReg = new ArrayList<>();
        try {
            lstTblModulosReg.clear();
            DaoTModulo daoTmodulo = new DaoTModulo();
            if(moduloDescripcion == null){
                lstModulo = daoTmodulo.getTblModulos("");
            }else{
                lstModulo = daoTmodulo.getTblModulos(moduloDescripcion);
            }
                

            if (lstModulo != null) {
                if (lstModulo.size() > 0) {
                    for (Modulo modulo : lstModulo) {
                        
                        lstTblModulosReg.add(new ClsTablaModulosRegistrados(modulo.getPromocion().getMaestria().getId(),
                                modulo.getPromocion().getMaestria().getDescripcion() + " (Dir.(a)" + modulo.getPromocion().getUsuario() + ")",
                                modulo.getPromocion().getId(),
                                modulo.getDescripcion(),
                                modulo.getUsuario().getId(),
                                modulo.getUsuario().getApellidos() + " " + modulo.getUsuario().getNombres(),
                                modulo.getCreditos().toString(),
                                modulo.getId(),
                                modulo.getModulo(),
                                modulo.getFechaInicio() == null ? null:modulo.getFechaInicio(),
                                modulo.getFechaFin() == null ? null:modulo.getFechaFin(),
                                modulo.getFechaInicioExamen() == null ? null:modulo.getFechaInicioExamen(),
                                modulo.getFechaFinExamen() == null ? null:modulo.getFechaFinExamen(),
                                modulo.getTotalHorasModulo() == null ? null:modulo.getTotalHorasModulo().toString(),
                                modulo.getPromocion().getFechaInicio(),
                                modulo.getPromocion().getFechaFin()
                                ));
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void llenarCboDocente() {
        this.lstTheme = new ArrayList<ClsProfesor>();
        try {
            DaoTUsuario daoTusuario = new DaoTUsuario();

            List<Usuario> lstUsuario = daoTusuario.getDocentes();
            this.lstTheme.clear();
            this.lstTheme.add(new ClsProfesor(-1, "Ninguno", "Ninguno"));
            for (Usuario user : lstUsuario) {
                this.lstTheme.add(new ClsProfesor(user.getId(), user.getApellidos() + " " + user.getNombres(), user.getApellidos() + " " + user.getNombres()));
            }
        } catch (Exception ex) {

        }
    }

    public void llenarCboMaestria() {
        this.lstThemeMaestria = new ArrayList<ClsMaestria>();
        try {
            DaoTMaestrias daoTmaestria = new DaoTMaestrias();
            DaoTPromocion daoTpromocion = new DaoTPromocion();

            List<Promocion> lstPromocion = daoTpromocion.getPromocionesMaestrias("");
            this.lstThemeMaestria.clear();
            this.lstThemeMaestria.add(new ClsMaestria(-1, "Ninguno", "Ninguno", 0, 0, 0, null, null));

            for (Promocion promocion : lstPromocion) {

                this.lstThemeMaestria.add(new ClsMaestria(promocion.getId(),
                        promocion.getMaestria().getDescripcion() + " (" + promocion.getUsuario() + ")",
                        promocion.getMaestria().getDescripcion(), promocion.getId(),
                        0, 0,
                        promocion.getFechaInicio(),
                        promocion.getFechaFin()));
            }
        } catch (Exception ex) {

        }
    }
    
    public void onMaestriaChange() throws Exception {
        if(themeMaestria != null){
            fechaInicio = themeMaestria.getFechaInicioMaestria();
            fechaFin = themeMaestria.getFechaFinMaestria();
        }else{
            fechaInicio = null;
            fechaFin = null;
        }
    }
    
    public List<SelectItem> getLstMaestria() {
        this.lstMaestria = new ArrayList<SelectItem>();
        try {
            DaoTMaestrias daoTmaestria = new DaoTMaestrias();

            List<Maestria> lstMaestria = daoTmaestria.getTodasMaestrias();
            lstMaestria.clear();
            for (Maestria maestria : lstMaestria) {
                SelectItem maestriaItem = new SelectItem(maestria.getId(), maestria.getDescripcion());
                this.lstMaestria.add(maestriaItem);
            }
        } catch (Exception ex) {

        }
        return lstMaestria;
    }

    public void registrar() {
        repetida = false;
        try {

            DaoTModulo daoTmodulo = new DaoTModulo();
            if(this.themeMaestria == null){
                mensajesError("Por favor escoja una maestria");
                return;
            } else {
                DaoTPromocion daoTpromocion = new DaoTPromocion();
                Promocion promocion = new Promocion();
                promocion.setId(this.themeMaestria.getIdPromocion());
                BigDecimal bigdec;
                bigdec = new BigDecimal(Double.parseDouble(creditos));
                tModulo.setCreditos(bigdec);
                bigdec = new BigDecimal(Double.parseDouble(totalHorasModulo));
                tModulo.setTotalHorasModulo(bigdec);
                tModulo.setPromocion(promocion);

                Usuario usuario = new Usuario();
                usuario.setId(theme.getId());
                tModulo.setUsuario(usuario);

                repetida = daoTmodulo.existe(tModulo);
                if (!repetida) {
                    List<Modulo> modulo = daoTmodulo.validacionModulos(tModulo);
                    if (modulo.size() <= 0) {
                        validacionFechas(daoTmodulo,0);
                    } else {
                       validacionFechas(daoTmodulo,0);
                    }

                }
                if (repetida) {
                    mensajesError("Registro repetido");
                }

               
            }
            

        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
    
    private void validacionFechas(DaoTModulo daoTmodulo, int estado) {
        if (fechaInicio.after(tModulo.getFechaInicio()) || fechaFin.before(tModulo.getFechaInicio())){
            mensajesError("La fecha de encuadre tiene que estar en este rango " + fechaInicio + " y " + fechaFin + "");
            tModulo.setFechaInicio(null);
            return;
        }else if(fechaInicio.after(tModulo.getFechaFin()) || fechaFin.before(tModulo.getFechaFin())){
            mensajesError("La 1ra asesoria tiene que estar en este rango " + fechaInicio + " y " + fechaFin + "");
            tModulo.setFechaFin(null);
            return;
        }else if(fechaInicio.after(tModulo.getFechaInicioExamen()) || fechaFin.before(tModulo.getFechaInicioExamen())){
            mensajesError("La 2da asesoria tiene que estar en este rango " + fechaInicio + " y " + fechaFin + "");
            tModulo.setFechaInicioExamen(null);
            return;
        }else if(fechaInicio.after(tModulo.getFechaFinExamen()) || fechaFin.before(tModulo.getFechaFinExamen())) {
            mensajesError("La fecha de evaluacón tiene que estar en este rango " + fechaInicio + " y " + fechaFin + "");
            tModulo.setFechaFinExamen(null);
            return;
        } else {
            if (tModulo.getFechaInicio().after(tModulo.getFechaFin()) || tModulo.getFechaInicio().equals(tModulo.getFechaFin()) ) {
                mensajesError("La 1ra Asesoria no puede ser menor o igual a la fecha encuadre");
                tModulo.setFechaFin(null);
                return;
            } else if (tModulo.getFechaInicio().after(tModulo.getFechaInicioExamen()) || tModulo.getFechaInicio().equals(tModulo.getFechaInicioExamen())) {
                mensajesError("La 2da Asesoria no puede ser menor o igual a la fecha encuadre");
                tModulo.setFechaInicioExamen(null);
                return;
            } else if (tModulo.getFechaInicio().after(tModulo.getFechaFinExamen()) || tModulo.getFechaInicio().equals(tModulo.getFechaFinExamen())) {
                mensajesError("La fecha evaluacion no puede ser menor o igual a la fecha encuadre");
                tModulo.setFechaFinExamen(null);
                return;
            } //segundo rango de condiciones
            else if (tModulo.getFechaFin().before(tModulo.getFechaInicio()) || tModulo.getFechaFin().equals(tModulo.getFechaInicio())) {
                mensajesError("La 1ra asesoria no puede ser menor a la fecha de encuadre");
                tModulo.setFechaInicio(null);
                return;
            } else if (tModulo.getFechaFin().after(tModulo.getFechaInicioExamen()) || tModulo.getFechaFin().equals(tModulo.getFechaInicioExamen())) {
                mensajesError("La 2da asesoria no puede ser menor o igual a la 1ra asesoria");
                tModulo.setFechaInicioExamen(null);
                return;
            } else if (tModulo.getFechaFin().after(tModulo.getFechaFinExamen()) || tModulo.getFechaFin().equals(tModulo.getFechaFinExamen())) {
                mensajesError("La fecha de evaluación no puede ser menor o igual a la 1ra asesoria");
                tModulo.setFechaFinExamen(null);
                return;
            } //Tercer rango de condiciones
            else if (tModulo.getFechaInicioExamen().before(tModulo.getFechaInicio()) || tModulo.getFechaInicioExamen().equals(tModulo.getFechaInicio())) {
                mensajesError("La 2da asesoria no puede ser menor a la fecha de encuadre");
                tModulo.setFechaInicio(null);
                return;
            } else if (tModulo.getFechaInicioExamen().before(tModulo.getFechaFin()) || tModulo.getFechaInicioExamen().equals(tModulo.getFechaFin())) {
                mensajesError("La 2da asesoria no puede ser menor a la 1ra asesoria");
                tModulo.setFechaFin(null);
                return;
            } else if (tModulo.getFechaInicioExamen().after(tModulo.getFechaFinExamen()) || tModulo.getFechaInicioExamen().equals(tModulo.getFechaFinExamen())) {
                mensajesError("La evaluacion no puede ser menor o igual a la evaluacion 2da asesoria");
                tModulo.setFechaFinExamen(null);
                return;
            } //cuarto rango de condiciones
            else if (tModulo.getFechaFinExamen().before(tModulo.getFechaInicio()) || tModulo.getFechaFinExamen().equals(tModulo.getFechaInicio())) {
                mensajesError("La fecha evaluación no puede ser menor a la fecha de encuadre");
                tModulo.setFechaInicio(null);
                return;
            } else if (tModulo.getFechaFinExamen().before(tModulo.getFechaFin()) || tModulo.getFechaFinExamen().equals(tModulo.getFechaFin())) {
                mensajesError("La fecha evaluación no puede ser menor a la 1ra asesoria");
                tModulo.setFechaFin(null);
                return;
            } else if (tModulo.getFechaFinExamen().before(tModulo.getFechaInicioExamen()) || tModulo.getFechaFinExamen().equals(tModulo.getFechaInicioExamen())) {
                mensajesError("La fecha evaluación no puede ser menor a la 2da asesoria");
                tModulo.setFechaInicioExamen(null);
                return;
            } else {
                try {
                    if(estado == 0){
                        msg = daoTmodulo.registrar(tModulo);
                        cargarTablaModulos();
                    }else if(estado == 1){
                        msg = daoTmodulo.update(tModulo);
                        cargarTablaModulos();
                    }
                    
                } catch (Exception ex) {
                    Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (estado == 0) {
                if (msg) {
                    mensajesOk("Datos procesados correctamente");
                } else {
                    mensajesError("Error al procesar datos");
                }

                vaciarCajas();

            } else if (estado == 1) {
                if (msg) {
                    mensajesOk("Datos actualizados correctamente");
                } else {
                    mensajesError("Error al actualizar datos");
                }
            }
        }
        //cargarTablaModulos();
    }

    private void vaciarCajas() {
        idDocente = 0;
        idMaestria = 0;
        tModulo = new Modulo();
        llenarCboDocente();
        creditos = "";
        totalHorasModulo = "";
        //llenarCboMaestria();

    }

    private void mensajesOk(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private void mensajesError(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onRowEdit(RowEditEvent event) {

        DaoTModulo daoTmodulo = new DaoTModulo();
        Usuario user = new Usuario();
        Promocion promocion = new Promocion();

        if (theme == null && themeMaestria == null) {
            user.setId(((ClsTablaModulosRegistrados) event.getObject()).getIdUsuario());
            promocion.setId(((ClsTablaModulosRegistrados) event.getObject()).getIdPromocion());
        } else if (theme == null && themeMaestria != null) {
            promocion.setId(themeMaestria.getIdPromocion());
            user.setId(((ClsTablaModulosRegistrados) event.getObject()).getIdUsuario());
        } else if (themeMaestria == null && theme != null) {
            user.setId(theme.getId());
            promocion.setId(((ClsTablaModulosRegistrados) event.getObject()).getIdPromocion());
        } else {
            promocion.setId(themeMaestria.getIdPromocion());
            user.setId(theme.getId());
        }

        try {
            tModulo = new Modulo();
            tModulo.setPromocion(promocion);
            tModulo.setUsuario(user);
            tModulo.setId(((ClsTablaModulosRegistrados) event.getObject()).getIdModulo());
            tModulo.setDescripcion(((ClsTablaModulosRegistrados) event.getObject()).getModulo());
            BigDecimal bigdec;
            String valorHoras = ((ClsTablaModulosRegistrados) event.getObject()).getTotalHorasModulo().toString();
            if(!valorHoras.isEmpty()){
                bigdec = new BigDecimal(Double.parseDouble(((ClsTablaModulosRegistrados) event.getObject()).getTotalHorasModulo()));
                tModulo.setTotalHorasModulo(bigdec);
            }
            bigdec = new BigDecimal(Double.parseDouble(((ClsTablaModulosRegistrados) event.getObject()).getCreditos()));
            tModulo.setCreditos(bigdec);
            
            tModulo.setFechaInicio(((ClsTablaModulosRegistrados) event.getObject()).getFechaInicio());
            tModulo.setFechaFin(((ClsTablaModulosRegistrados) event.getObject()).getFechaFin());
            tModulo.setFechaInicioExamen(((ClsTablaModulosRegistrados) event.getObject()).getFechaInicioExamen());
            tModulo.setFechaFinExamen(((ClsTablaModulosRegistrados) event.getObject()).getFechaFinExamen());
            tModulo.setModulo(((ClsTablaModulosRegistrados) event.getObject()).getN_modulo());
            this.fechaInicio = ((ClsTablaModulosRegistrados) event.getObject()).getFechaInicioMaestria();
            this.fechaFin = ((ClsTablaModulosRegistrados) event.getObject()).getFechaFinMaestria();
            
            validacionFechas(daoTmodulo,1);
//            msg = daoTmodulo.update(tModulo);
//            cargarTablaModulos();

        } catch (Exception ex) {
            Logger.getLogger(MbVTablaPermisos.class.getName()).log(Level.SEVERE, null, ex);
        }
        vaciarCajas();
        cargarTablaModulos();
//        if (msg) {
//            mensajesOk("Datos actualizados correctamente");
//        } else {
//            mensajesError("Error al actualizar datos");
//        }
    }
    
    public void onRowCancel(RowEditEvent event) {

    }

    public void onDelete(ClsTablaModulosRegistrados clsTblModulos) {
        DaoTModulo daoTmodulo = new DaoTModulo();
        Modulo modulo = new Modulo();
        modulo.setId(clsTblModulos.getIdModulo());
        try {
            msg = daoTmodulo.delete(modulo);
        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        cargarTablaModulos();
        if (msg) {
            mensajesOk("Dato eliminado correctamente");
        } else {
            mensajesError("Error al eliminar dato");
        }
    }

}

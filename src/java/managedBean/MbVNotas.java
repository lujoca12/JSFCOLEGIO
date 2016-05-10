/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Clases.ClsNotas;
import Clases.ClsTablaModulosRegistrados;
import Clases.ClsTblNotas;
import Dao.DaoTEstudiante;
import Dao.DaoTMatricula;
import Dao.DaoTModulo;
import Dao.DaoTNotas;
import Pojo.Estudiante;
import Pojo.Matricula;
import Pojo.Modulo;
import Pojo.Notas;
import Pojo.SolicitudInscripcion;
import Pojo.Usuario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author server
 */
@Named(value = "mbVNotas")
@ViewScoped
public class MbVNotas implements Serializable {

    private Notas tNotas;

    private ClsNotas clsNotas;
    private List<ClsNotas> lstTblNotas;

    private ClsTblNotas clsTblNotas;
    private List<ClsTblNotas> lstTblNotasReg;

    private ClsTablaModulosRegistrados clsTblModulosReg;
    private List<ClsTablaModulosRegistrados> lstCboModulos;

    private String cedula = "";
    private String estudiante = "";
    private String maestria;
    private int idProm = 0;
    private int idModulo = 0;
    private int tipo_user;
    private Character estado;
    private String docente;
    private boolean msg;

    public MbVNotas() {
        tNotas = new Notas();
        cargarCboModulos();
    }

    public Notas gettNotas() {
        return tNotas;
    }

    public void settNotas(Notas tNotas) {
        this.tNotas = tNotas;
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

    public ClsTblNotas getClsTblNotas() {
        return clsTblNotas;
    }

    public void setClsTblNotas(ClsTblNotas clsTblNotas) {
        this.clsTblNotas = clsTblNotas;
    }

    public List<ClsTblNotas> getLstTblNotasReg() {
        return lstTblNotasReg;
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

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public String getMaestria() {
        return maestria;
    }

    public void setMaestria(String maestria) {
        this.maestria = maestria;
    }

    public int getTipo_user() {
        return tipo_user;
    }

    public void setTipo_user(int tipo_user) {
        this.tipo_user = tipo_user;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }
    
    private void cargarTblMatriculaPromocion() {
        this.idProm = 0;
        lstTblNotas = new ArrayList<>();
        try {
            lstTblNotas.clear();
            DaoTMatricula daoTmatricula = new DaoTMatricula();
            List<Matricula> lstMatricula = daoTmatricula.getMatriculaMaestria(this.cedula);
            Calendar calendar = Calendar.getInstance();
            int añoInicio = 0;
            int añoFin = 0;
            int cont = 0;

            if (lstMatricula.size() > 0) {
                for (Matricula matricula : lstMatricula) {
                    calendar.setTime(matricula.getSolicitudInscripcion().getPromocion().getFechaInicio());
                    añoInicio = calendar.get(Calendar.YEAR);
                    cont++;
                    calendar.setTime(matricula.getSolicitudInscripcion().getPromocion().getFechaFin());
                    añoFin = calendar.get(Calendar.YEAR);
                    estudiante = matricula.getSolicitudInscripcion().getEstudiante().getApellidos() + " " + matricula.getSolicitudInscripcion().getEstudiante().getNombres();

                    lstTblNotas.add(new ClsNotas(matricula.getSolicitudInscripcion().getEstudiante().getId(),
                            matricula.getSolicitudInscripcion().getEstudiante().getApellidos() + " " + matricula.getSolicitudInscripcion().getEstudiante().getNombres(),
                            matricula.getId(),
                            matricula.getNMatricula(),
                            matricula.getFechaMatricula(),
                            matricula.getSolicitudInscripcion().getPromocion().getId(),
                            añoInicio + "-" + añoFin,
                            matricula.getSolicitudInscripcion().getPromocion().getFechaResolucion(),
                            matricula.getSolicitudInscripcion().getPromocion().getMaestria().getId(),
                            matricula.getSolicitudInscripcion().getPromocion().getMaestria().getDescripcion(),
                            cont,
                            "",
                            "",
                            null,
                            true, 0,"",""));
                }
            } else {
                this.estudiante = "";
            }

        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarTablaNotas(int idMatricula, int idPromocion) {
        lstTblNotasReg = new ArrayList<>();
        try {
            lstTblNotasReg.clear();
            DaoTNotas daoTnotas = new DaoTNotas();
            List<Notas> lstNotas = daoTnotas.getTodasNotas(idMatricula);

            DaoTModulo daoTmodulo = new DaoTModulo();
            List<Modulo> lstModulos = daoTmodulo.getTblModulosNotas(idPromocion);

            //Validacion para saber si hay notas registradas
//            if (lstNotas.size() > 0) {
//                for (Notas nota : lstNotas) {
//
//                    lstTblNotasReg.add(new ClsTblNotas(nota.getModulo().getId(),
//                            nota.getModulo().getDescripcion(),
//                            nota.getModulo().getCreditos(),
//                            nota.getId(),
//                            nota.getNota().doubleValue(),
//                            nota.getObservacion(),
//                            nota.getNotaTexto()));
//                }
//            } else {
            boolean bandera = true;
            if (lstModulos.size() > 0) {
                for (Modulo modulos : lstModulos) {

                    if (lstNotas.size() > 0) {
                        for (Notas nota : lstNotas) {
                            if (modulos.getId() == nota.getModulo().getId()) {
                                bandera = false;
                                lstTblNotasReg.add(new ClsTblNotas(modulos.getId(),
                                        modulos.getDescripcion(),
                                        modulos.getCreditos().toString(),
                                        nota.getId(),
                                        nota.getNota().doubleValue(),
                                        nota.getObservacion(),
                                        nota.getNotaTexto()));
                            }

                        }
                    }
                    if (bandera) {
                        lstTblNotasReg.add(new ClsTblNotas(modulos.getId(),
                                modulos.getDescripcion(),
                                modulos.getCreditos().toString(),
                                0,
                                0.0,
                                "",
                                ""));
                    }
                    bandera = true;
                }
            }

//            }
        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarCboModulos() {
        lstCboModulos = new ArrayList<>();
        //Recogiendo Datos de la sesion para saber que usuario ingreso la maestria promocion
        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        try {
            lstCboModulos.clear();
            
            this.lstCboModulos.add(new ClsTablaModulosRegistrados(-1, "(Escoja un Módulo)", -1, "(Escoja un Módulo)", -1, "(Escoja un Módulo)","(Escoja un Módulo)",-1,"(Escoja un Módulo)",null,null,null,null,""));
            if(usuario.getTipoUsuario().getDescripcion().equals("Profesor(a)") || usuario.getTipoUsuario().getDescripcion().equals("Docente") || usuario.getTipoUsuario().getDescripcion().equals("PROFESOR(A)") || usuario.getTipoUsuario().getDescripcion().equals("DOCENTE")){
                DaoTModulo daoTmodulo = new DaoTModulo();
                List<Modulo> lstModulo = daoTmodulo.getCboModulosNotas(usuario.getId());
                tipo_user = 1;
                if (lstModulo != null) {
                    if (lstModulo.size() > 0) {
                        for (Modulo modulo : lstModulo) {
                            lstCboModulos.add(new ClsTablaModulosRegistrados(modulo.getPromocion().getMaestria().getId(),
                                    modulo.getPromocion().getMaestria().getDescripcion() + " (Dir.(a)" + modulo.getPromocion().getUsuario() + ")",
                                    modulo.getPromocion().getId(),
                                    modulo.getDescripcion() + " (" + modulo.getPromocion().getMaestria().getDescripcion() + ")",
                                    modulo.getUsuario().getId(),
                                    modulo.getUsuario().getApellidos() + " " + modulo.getUsuario().getNombres(),
                                    modulo.getCreditos().toString(),
                                    modulo.getId(),
                                    modulo.getN_modulo(),
                                    modulo.getFechaInicio() == null ? null : modulo.getFechaInicio(),
                                    modulo.getFechaFin() == null ? null : modulo.getFechaFin(),
                                    modulo.getFechaInicioExamen() == null ? null : modulo.getFechaInicioExamen(),
                                    modulo.getFechaFinExamen() == null ? null : modulo.getFechaFinExamen(),
                                    modulo.getTotalHorasModulo() == null ? null : modulo.getTotalHorasModulo().toString()
                            ));
                        }
                    }
                }
            }else{
                DaoTModulo daoTmodulo = new DaoTModulo();
                List<Modulo> lstModulo = daoTmodulo.getCboModulosNotas(0);
                tipo_user = 0;
                if (lstModulo != null) {
                    if (lstModulo.size() > 0) {
                        for (Modulo modulo : lstModulo) {
                            lstCboModulos.add(new ClsTablaModulosRegistrados(modulo.getPromocion().getMaestria().getId(),
                                    modulo.getPromocion().getMaestria().getDescripcion() + " (Dir.(a)" + modulo.getPromocion().getUsuario() + ")",
                                    modulo.getPromocion().getId(),
                                    modulo.getDescripcion() + " (" + modulo.getUsuario().getApellidos() + " " + modulo.getUsuario().getNombres() + ")",
                                    modulo.getUsuario().getId(),
                                    modulo.getUsuario().getApellidos() + " " + modulo.getUsuario().getNombres(),
                                    modulo.getCreditos().toString(),
                                    modulo.getId(),
                                    modulo.getN_modulo(),
                                    modulo.getFechaInicio() == null ? null:modulo.getFechaInicio(),
                                    modulo.getFechaFin() == null ? null : modulo.getFechaFin(),
                                    modulo.getFechaInicioExamen() == null ? null : modulo.getFechaInicioExamen(),
                                    modulo.getFechaFinExamen() == null ? null : modulo.getFechaFinExamen(),
                                    modulo.getTotalHorasModulo() == null ? null : modulo.getTotalHorasModulo().toString()
                            ));
                        }
                    }
                }
                
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarTablaRegNotas() {
        lstTblNotas = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int añoInicio = 0;
        int añoFin = 0;
        int cont = 0;
        boolean bandera = true;
        maestria = "";
        if (this.clsTblModulosReg != null) {
            for (int i = 0; i < lstCboModulos.size(); i++) {
                if (lstCboModulos.get(i).getIdModulo() == this.clsTblModulosReg.getIdModulo()) {
                    maestria = lstCboModulos.get(i).getMaestria();
                    i = lstCboModulos.size();
                }
            }
        }
        
        try {
            lstTblNotas.clear();
            estado = ' ';
            tipo_user = 0;
            DaoTNotas daoTnotas = new DaoTNotas();
            List<Notas> lstNotas = null;
            if(this.clsTblModulosReg != null)
                lstNotas = daoTnotas.existe(this.clsTblModulosReg.getIdModulo(),"0");
            else
                lstNotas = daoTnotas.existe(0,"0");
            
            DaoTMatricula daoTmatricula = new DaoTMatricula();
            List<Matricula> lstMatricula = null;
            
            if(this.clsTblModulosReg != null)
                lstMatricula = daoTmatricula.getMatriculaRegNotas(this.clsTblModulosReg.getIdModulo());
            else
                lstMatricula = daoTmatricula.getMatriculaRegNotas(0);
            
            //Recogiendo Datos de la sesion para saber que usuario ingreso la maestria promocion
            Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            
            if(usuario.getTipoUsuario().getDescripcion().equals("Profesor(a)") || usuario.getTipoUsuario().getDescripcion().equals("Docente") || usuario.getTipoUsuario().getDescripcion().equals("PROFESOR(A)") || usuario.getTipoUsuario().getDescripcion().equals("DOCENTE")){
                tipo_user = 1;
            
            }else{
                tipo_user = 0;
            }
            
            if (lstMatricula.size() > 0) {
                for (Matricula matricula : lstMatricula) {

                    if (lstNotas.size() > 0) {
                        for (Notas notas : lstNotas) {
                            
                            if (matricula.getId() == notas.getMatricula().getId()) {
                                
                                estado = notas.getEstado();
                                docente = notas.getModulo().getUsuario().getApellidos()+" "+notas.getModulo().getUsuario().getNombres();
                                bandera = false;
                                calendar.setTime(notas.getMatricula().getSolicitudInscripcion().getPromocion().getFechaInicio());
                                añoInicio = calendar.get(Calendar.YEAR);

                                calendar.setTime(notas.getMatricula().getSolicitudInscripcion().getPromocion().getFechaFin());
                                añoFin = calendar.get(Calendar.YEAR);

                                estudiante = notas.getMatricula().getSolicitudInscripcion().getEstudiante().getApellidos() + " " + notas.getMatricula().getSolicitudInscripcion().getEstudiante().getNombres();
                                cont++;
                                lstTblNotas.add(new ClsNotas(notas.getMatricula().getSolicitudInscripcion().getEstudiante().getId(),
                                        estudiante,
                                        notas.getMatricula().getId(),
                                        notas.getMatricula().getNMatricula(),
                                        notas.getMatricula().getFechaMatricula(),
                                        notas.getMatricula().getSolicitudInscripcion().getPromocion().getId(),
                                        añoInicio + "-" + añoFin,
                                        notas.getMatricula().getSolicitudInscripcion().getPromocion().getFechaResolucion(),
                                        notas.getMatricula().getSolicitudInscripcion().getPromocion().getMaestria().getId(),
                                        notas.getMatricula().getSolicitudInscripcion().getPromocion().getMaestria().getDescripcion(),
                                        cont,
                                        notas.getNota().toString(),
                                        notas.getObservacion(),
                                        null,
                                        true,
                                        notas.getId(),
                                        notas.getUsuario(),
                                        notas.getResponsable()));
                            }
                        }
                    }

                    if (bandera) {
                        calendar.setTime(matricula.getSolicitudInscripcion().getPromocion().getFechaInicio());
                        añoInicio = calendar.get(Calendar.YEAR);

                        calendar.setTime(matricula.getSolicitudInscripcion().getPromocion().getFechaFin());
                        añoFin = calendar.get(Calendar.YEAR);
                        estudiante = matricula.getSolicitudInscripcion().getEstudiante().getApellidos() + " " + matricula.getSolicitudInscripcion().getEstudiante().getNombres();
                        cont++;
                        lstTblNotas.add(new ClsNotas(matricula.getSolicitudInscripcion().getEstudiante().getId(),
                                matricula.getSolicitudInscripcion().getEstudiante().getApellidos() + " " + matricula.getSolicitudInscripcion().getEstudiante().getNombres(),
                                matricula.getId(),
                                matricula.getNMatricula(),
                                matricula.getFechaMatricula(),
                                matricula.getSolicitudInscripcion().getPromocion().getId(),
                                añoInicio + "-" + añoFin,
                                matricula.getSolicitudInscripcion().getPromocion().getFechaResolucion(),
                                matricula.getSolicitudInscripcion().getPromocion().getMaestria().getId(),
                                matricula.getSolicitudInscripcion().getPromocion().getMaestria().getDescripcion(),
                                cont,
                                "0",
                                "",
                                null,
                                true, 0,"",""));

                    }

                    bandera = true;
                }
            }
            if(estado != null)
                if(estado.equals('A'))
                    mensajesOk("Notas ya Registradas");
            
            if(usuario.getTipoUsuario().getDescripcion().equals("Profesor(a)") || usuario.getTipoUsuario().getDescripcion().equals("Docente") || usuario.getTipoUsuario().getDescripcion().equals("PROFESOR(A)") || usuario.getTipoUsuario().getDescripcion().equals("DOCENTE")){
                tipo_user = 1;
                if(estado != null)
                    if (estado.equals('G')) {
                        mensajesOk("Notas ya Registradas");
                    }
            }
                

        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cargarTablaEdicionRegNotas() {
        lstTblNotas = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int añoInicio = 0;
        int añoFin = 0;
        int cont = 0;
        boolean bandera = true;
        maestria = "";
        if (this.clsTblModulosReg != null) {
            for (int i = 0; i < lstCboModulos.size(); i++) {
                if (lstCboModulos.get(i).getIdModulo() == this.clsTblModulosReg.getIdModulo()) {
                    maestria = lstCboModulos.get(i).getMaestria();
                    i = lstCboModulos.size();
                }
            }
        }

        try {
            lstTblNotas.clear();
            estado = ' ';
            tipo_user = 0;
            DaoTNotas daoTnotas = new DaoTNotas();
            List<Notas> lstNotas = null;
            if (this.clsTblModulosReg != null) {
                lstNotas = daoTnotas.existe(this.clsTblModulosReg.getIdModulo(),"1");
            } else {
                lstNotas = daoTnotas.existe(0,"1");
            }

            //Recogiendo Datos de la sesion para saber que usuario ingreso la maestria promocion
            Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

            if (usuario.getTipoUsuario().getDescripcion().equals("Profesor(a)") || usuario.getTipoUsuario().getDescripcion().equals("Docente") || usuario.getTipoUsuario().getDescripcion().equals("PROFESOR(A)") || usuario.getTipoUsuario().getDescripcion().equals("DOCENTE")) {
                tipo_user = 1;

            } else {
                tipo_user = 0;
            }
            if (lstNotas.size() > 0) {
                for (Notas notas : lstNotas) {

                    estado = notas.getEstado();
                    docente = notas.getModulo().getUsuario().getApellidos() + " " + notas.getModulo().getUsuario().getNombres();
                    bandera = false;
                    calendar.setTime(notas.getMatricula().getSolicitudInscripcion().getPromocion().getFechaInicio());
                    añoInicio = calendar.get(Calendar.YEAR);

                    calendar.setTime(notas.getMatricula().getSolicitudInscripcion().getPromocion().getFechaFin());
                    añoFin = calendar.get(Calendar.YEAR);

                    estudiante = notas.getMatricula().getSolicitudInscripcion().getEstudiante().getApellidos() + " " + notas.getMatricula().getSolicitudInscripcion().getEstudiante().getNombres();
                    cont++;
                    lstTblNotas.add(new ClsNotas(notas.getMatricula().getSolicitudInscripcion().getEstudiante().getId(),
                            estudiante,
                            notas.getMatricula().getId(),
                            notas.getMatricula().getNMatricula(),
                            notas.getMatricula().getFechaMatricula(),
                            notas.getMatricula().getSolicitudInscripcion().getPromocion().getId(),
                            añoInicio + "-" + añoFin,
                            notas.getMatricula().getSolicitudInscripcion().getPromocion().getFechaResolucion(),
                            notas.getMatricula().getSolicitudInscripcion().getPromocion().getMaestria().getId(),
                            notas.getMatricula().getSolicitudInscripcion().getPromocion().getMaestria().getDescripcion(),
                            cont,
                            notas.getNota().toString(),
                            notas.getObservacion(),
                            null,
                            true,
                            notas.getId(),
                            notas.getUsuario(),
                            notas.getResponsable()));
                }
            }

            

        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void registrar(Character accion) {
        DaoTNotas daoTnotas = new DaoTNotas();
        try {
            if(lstTblNotas.size() > 0)
                msg = daoTnotas.registrar(lstTblNotas, this.clsTblModulosReg.getIdModulo(), accion, docente);
                
        } catch (Exception ex) {
            Logger.getLogger(MbVNotas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (msg) {
            mensajesOk("Datos procesados correctamente");
        } else {
            mensajesError("Error al procesar datos");
        }
        vaciarCajas();
    }
//    B  = Borrador-> Docente
//    G  = Guardado-> Docente
//    A  = Archivado-> Secretario u otros
//    E  = Eliminado-> Calificaciones de baja
    public void registrar_borrador(){
        registrar('B');
    }
    
    public void registrar_notas_docente(){
        registrar('G');
    }
    
    public void confirmar_notas_Secretaria(){
        registrar('A');
    }

    public void consultarMaestrias() {
        if (this.cedula.length() < 10) {
            this.estudiante = "";
            this.idProm = 0;
        } else {
            this.cedula = this.cedula.replace("-", "");
            cargarTblMatriculaPromocion();
        }

    }
    public void eliminarNotas(){
        
        DaoTNotas daoTnotas = new DaoTNotas();
        try {
            msg = daoTnotas.update(lstTblNotas,this.clsTblModulosReg.getIdModulo());
        } catch (Exception ex) {
            Logger.getLogger(MbVNotas.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (msg) {
            mensajesOk("Dato eliminado correctamente");
        } else {
            mensajesError("Error al eliminar dato");
        }
        vaciarCajas();
    }

    private void vaciarCajas() {
        this.idModulo = 0;
        cargarCboModulos();
        this.clsTblModulosReg.setIdModulo(0);
        lstTblNotas = new ArrayList<>();
    }

    public void consultarNotas(ClsNotas clsNotas) {
        idProm = clsNotas.getIdPromocion();
        cargarTablaNotas(clsNotas.getIdMatricula(), clsNotas.getIdPromocion());
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

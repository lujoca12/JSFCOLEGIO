/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Clases.ClsMaestria;
import Clases.ClsTablaModulosRegistrados;
import Dao.DaoTMaestrias;
import Dao.DaoTModulo;
import Pojo.HorarioModulo;
import Pojo.Maestria;
import Pojo.Modulo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

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
    
    private boolean msg = false;
    private HorarioModulo tHorarioModulo;
    
    public MbVModulosHorarios() {
        tHorarioModulo = new HorarioModulo();
        llenarCboMaestria();
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
    
    public void llenarCboMaestria(){
        this.lstThemeMaestria = new ArrayList<ClsMaestria>();
         try {
            DaoTMaestrias daoTmaestria = new DaoTMaestrias();
            
            List<Maestria> lstMaestria = daoTmaestria.getMaestrias();
            this.lstThemeMaestria.clear();
            this.lstThemeMaestria.add(new ClsMaestria(-1,"Ninguno","Ninguno",0,0,0));
            
            for(Maestria maestria: lstMaestria){
                this.lstThemeMaestria.add(new ClsMaestria(maestria.getId(),
                        maestria.getDescripcion(),
                        maestria.getDescripcion(),
                        maestria.getId(),
                        0,
                        0));
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
            else
                lstCboModulos.clear();
            
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
    
}

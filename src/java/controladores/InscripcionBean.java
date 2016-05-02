/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Clases.ClsRequisito;
import Dao.LocalizacionDao;
import Dao.PromocionDao;
import Dao.RequisitosDao;
import Pojo.Archivos;
import Pojo.Canton;
import Pojo.Estudiante;
import Pojo.Pais;
import Pojo.Parroquia;
import Pojo.Promocion;
import Pojo.Provincia;
import Pojo.Requisitos;
import Pojo.RequisitosPromo;
import Pojo.SolicitudInscripcion;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.hibernate.Hibernate;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author chiti
 */
@Named(value = "inscripcionBean")
@ViewScoped
public class InscripcionBean implements Serializable {

    /**
     * Creates a new instance of InscripcionBean
     */
    private List<Requisitos> lstReq;
    private List<ClsRequisito> lstClsR;
    private List<SelectItem> lstPais;
    private List<SelectItem> lstProvincia;

    private String idPromo;
    private String idMaestria;
    private Map<Integer, String> lstReqProNombre;
    private Map<String, String> maestrias;
    private Map<String, String> promociones;
    private Map<String, String> requisitos;
    private Map<String, String> provincias;
    private Map<String, String> cantones;
    private Map<String, String> parroquias;
    private Map<String, String> provinciasDom;
    private Map<String, String> cantonesDom;
    private Map<String, String> parroquiasDom;
    private Map<String, String> provinciasTra;
    private Map<String, String> cantonesTra;
    private Map<String, String> parroquiasTra;

    private RequisitosBean reqB;
    private List<SelectItem> listaPromocion;
    private MaestriaBean mBean;
    private Map<String, Map<String, String>> data = new HashMap<>();
    private Map<String, Map<String, String>> data2 = new HashMap<>();
    private Map<String, Map<String, String>> dataProCan = new HashMap<>();
    private Map<String, Map<String, String>> dataCanPar = new HashMap<>();   

    private List<String> reqSelec;
    private List<SelectItem> listaRequisitos;
    private Estudiante estudiante;
    private SolicitudInscripcion sInscripcion;
    private Archivos archivos;
    private String idPaisOrigen;
    private String idProvinciaNac;
    private String idCantonNac;
    private String idParroquiaNac;
    private String idProvinciaDom;
    private String idCantonDom;
    private String idParroquiaDom;
    private String idProvinciaTra;
    private String idCantonTra;
    private String idParroquiaTra;
    private String idUniversidad;
    private String idEcuador;

    public String getIdEcuador() {
        return idEcuador;
    }

    public void setIdEcuador(String idEcuador) {
        this.idEcuador = idEcuador;
    }

    public Map<String, String> getProvinciasDom() {
        return provinciasDom;
    }

    public void setProvinciasDom(Map<String, String> provinciasDom) {
        this.provinciasDom = provinciasDom;
    }

    public Map<String, String> getCantonesDom() {
        return cantonesDom;
    }

    public void setCantonesDom(Map<String, String> cantonesDom) {
        this.cantonesDom = cantonesDom;
    }

    public Map<String, String> getParroquiasDom() {
        return parroquiasDom;
    }

    public void setParroquiasDom(Map<String, String> parroquiasDom) {
        this.parroquiasDom = parroquiasDom;
    }

    public Map<String, String> getProvinciasTra() {
        return provinciasTra;
    }

    public void setProvinciasTra(Map<String, String> provinciasTra) {
        this.provinciasTra = provinciasTra;
    }

    public Map<String, String> getCantonesTra() {
        return cantonesTra;
    }

    public void setCantonesTra(Map<String, String> cantonesTra) {
        this.cantonesTra = cantonesTra;
    }

    public Map<String, String> getParroquiasTra() {
        return parroquiasTra;
    }

    public void setParroquiasTra(Map<String, String> parroquiasTra) {
        this.parroquiasTra = parroquiasTra;
    }
    
    

    public List<SelectItem> getLstProvincia() {
        return lstProvincia;
    }

    public void setLstProvincia(List<SelectItem> lstProvincia) {
        this.lstProvincia = lstProvincia;
    }

    public Map<String, Map<String, String>> getDataProCan() {
        return dataProCan;
    }

    public void setDataProCan(Map<String, Map<String, String>> dataProCan) {
        this.dataProCan = dataProCan;
    }

    public Map<String, Map<String, String>> getDataCanPar() {
        return dataCanPar;
    }

    public void setDataCanPar(Map<String, Map<String, String>> dataCanPar) {
        this.dataCanPar = dataCanPar;
    }

    public List<SelectItem> getLstPais() {
        return lstPais;
    }

    public void setLstPais(List<SelectItem> lstPais) {
        this.lstPais = lstPais;
    }

    public Map<String, String> getProvincias() {
        return provincias;
    }

    public void setProvincias(Map<String, String> provincias) {
        this.provincias = provincias;
    }

    public Map<String, String> getCantones() {
        return cantones;
    }

    public void setCantones(Map<String, String> cantones) {
        this.cantones = cantones;
    }

    public Map<String, String> getParroquias() {
        return parroquias;
    }

    public void setParroquias(Map<String, String> parroquias) {
        this.parroquias = parroquias;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public SolicitudInscripcion getsInscripcion() {
        return sInscripcion;
    }

    public void setsInscripcion(SolicitudInscripcion sInscripcion) {
        this.sInscripcion = sInscripcion;
    }

    public Archivos getArchivos() {
        return archivos;
    }

    public void setArchivos(Archivos archivos) {
        this.archivos = archivos;
    }

    public String getIdPaisOrigen() {
        return idPaisOrigen;
    }

    public void setIdPaisOrigen(String idPaisOrigen) {
        this.idPaisOrigen = idPaisOrigen;
    }

    public String getIdProvinciaNac() {
        return idProvinciaNac;
    }

    public void setIdProvinciaNac(String idProvinciaNac) {
        this.idProvinciaNac = idProvinciaNac;
    }

    public String getIdCantonNac() {
        return idCantonNac;
    }

    public void setIdCantonNac(String idCantonNac) {
        this.idCantonNac = idCantonNac;
    }

    public String getIdParroquiaNac() {
        return idParroquiaNac;
    }

    public void setIdParroquiaNac(String idParroquiaNac) {
        this.idParroquiaNac = idParroquiaNac;
    }

    public String getIdProvinciaDom() {
        return idProvinciaDom;
    }

    public void setIdProvinciaDom(String idProvinciaDom) {
        this.idProvinciaDom = idProvinciaDom;
    }

    public String getIdCantonDom() {
        return idCantonDom;
    }

    public void setIdCantonDom(String idCantonDom) {
        this.idCantonDom = idCantonDom;
    }

    public String getIdParroquiaDom() {
        return idParroquiaDom;
    }

    public void setIdParroquiaDom(String idParroquiaDom) {
        this.idParroquiaDom = idParroquiaDom;
    }

    public String getIdProvinciaTra() {
        return idProvinciaTra;
    }

    public void setIdProvinciaTra(String idProvinciaTra) {
        this.idProvinciaTra = idProvinciaTra;
    }

    public String getIdCantonTra() {
        return idCantonTra;
    }

    public void setIdCantonTra(String idCantonTra) {
        this.idCantonTra = idCantonTra;
    }

    public String getIdParroquiaTra() {
        return idParroquiaTra;
    }

    public void setIdParroquiaTra(String idParroquiaTra) {
        this.idParroquiaTra = idParroquiaTra;
    }

    public String getIdUniversidad() {
        return idUniversidad;
    }

    public void setIdUniversidad(String idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    public List<ClsRequisito> getLstClsR() {
        return lstClsR;
    }

    public void setLstClsR(List<ClsRequisito> lstClsR) {
        this.lstClsR = lstClsR;
    }

    public Map<String, Map<String, String>> getData2() {
        return data2;
    }

    public void setData2(Map<String, Map<String, String>> data2) {
        this.data2 = data2;
    }

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, String>> data) {
        this.data = data;
    }

    public List<Requisitos> getLstReq() {
        return lstReq;
    }

    public void setLstReq(List<Requisitos> lstReq) {
        this.lstReq = lstReq;
    }

    public String getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(String idPromo) {
        this.idPromo = idPromo;
    }

    public String getIdMaestria() {
        return idMaestria;
    }

    public void setIdMaestria(String idMaestria) {
        this.idMaestria = idMaestria;
    }

    public Map<Integer, String> getLstReqProNombre() {
        return lstReqProNombre;
    }

    public void setLstReqProNombre(Map<Integer, String> lstReqProNombre) {
        this.lstReqProNombre = lstReqProNombre;
    }

    public Map<String, String> getMaestrias() {
        return maestrias;
    }

    public void setMaestrias(Map<String, String> maestrias) {
        this.maestrias = maestrias;
    }

    public Map<String, String> getPromociones() {
        return promociones;
    }

    public void setPromociones(Map<String, String> promociones) {
        this.promociones = promociones;
    }

    public Map<String, String> getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(Map<String, String> requisitos) {
        this.requisitos = requisitos;
    }

    public RequisitosBean getReqB() {
        return reqB;
    }

    public void setReqB(RequisitosBean reqB) {
        this.reqB = reqB;
    }

    public List<SelectItem> getListaPromocion() {
        return listaPromocion;
    }

    public void setListaPromocion(List<SelectItem> listaPromocion) {
        this.listaPromocion = listaPromocion;
    }

    public MaestriaBean getmBean() {
        return mBean;
    }

    public void setmBean(MaestriaBean mBean) {
        this.mBean = mBean;
    }

    public List<String> getReqSelec() {
        return reqSelec;
    }

    public void setReqSelec(List<String> reqSelec) {
        this.reqSelec = reqSelec;
    }

    public List<SelectItem> getListaRequisitos() {
        return listaRequisitos;
    }

    public void setListaRequisitos(List<SelectItem> listaRequisitos) {
        this.listaRequisitos = listaRequisitos;
    }

    public InscripcionBean() {
    }

    @PostConstruct
    public void init() {
        try {
            //datos de los combos de maestria y promociones ademas de los requisitos solicitados
            maestrias = new HashMap<>();
            listaPromocion = new ArrayList<>();
            mBean = new MaestriaBean();
            mBean.init();
            List<SelectItem> items = mBean.getListaMaestrias();
            PromocionDao daoTPromocion = new PromocionDao();
            RequisitosDao daoRequisitos = new RequisitosDao();

            for (SelectItem i : items) {
                maestrias.put(i.getLabel(), i.getValue().toString());
                List<Promocion> lstPromocion = daoTPromocion.getPromocionesMaestrias(Integer.valueOf(i.getValue().toString()));
                Map<String, String> map = new HashMap<>();
                for (Promocion p : lstPromocion) {
                    map.put(p.getDescripcion().toString(), String.valueOf(p.getId()));
                    List<RequisitosPromo> lstRequisitos = daoRequisitos.getRequisitosPromocion(String.valueOf(p.getId()), i.getValue().toString());
                    Map<String, String> map2 = new HashMap<>();
                    for (RequisitosPromo rp : lstRequisitos) {
                        map2.put(rp.getRequisitos().getDescripcion(), String.valueOf(rp.getId()));
                    }
                    data2.put(String.valueOf(p.getId()), map2);
                }
                data.put(i.getValue().toString(), map);
            }
            //datos para los paises combos de provincia canton parroquia
            lstPais = new ArrayList<>();
            LocalizacionDao locDao = new LocalizacionDao();
            List<Pais> paises = locDao.getPaises();
            for (Pais p : paises) {
                SelectItem item = new SelectItem(p.getId(), p.getDescripcion());
                lstPais.add(item);
                if(p.getDescripcion().equalsIgnoreCase("ecuador"))
                    idEcuador=String.valueOf(p.getId());
            }
            //prov canton parroq
            provincias = new HashMap<>();
            lstProvincia = new ArrayList<>();
            List<SelectItem> itemsProv = new ArrayList<>();
            List<Provincia> prov = locDao.getProvincias();
            for (Provincia p : prov) {
                SelectItem item = new SelectItem(p.getId(), p.getDescripcion());
                itemsProv.add(item);
            }
            for (SelectItem i : itemsProv) {
                provincias.put(i.getLabel(), i.getValue().toString());
                List<Canton> lstC = locDao.getCantonProvicia(Integer.valueOf(i.getValue().toString()));

                Map<String, String> map3 = new HashMap<>();
                for (Canton c : lstC) {
                    map3.put(c.getDescripcion(), String.valueOf(c.getId()));
                    List<Parroquia> lstPa = locDao.getParroquiaCanton(c.getId());
                    Map<String, String> map4 = new HashMap<>();
                    for (Parroquia pa : lstPa) {
                        map4.put(pa.getDescripcion(), String.valueOf(pa.getId()));
                    }
                    dataCanPar.put(String.valueOf(c.getId()), map4);
                }
                dataProCan.put(i.getValue().toString(), map3);
            }
           

        } catch (Exception ex) {
            Logger.getLogger(AsignarRequisitosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onMaestriaChange() throws Exception {
        try {
            promociones = new HashMap<>();
            if (idMaestria != null && !idMaestria.equals("")) {
                promociones = data.get(idMaestria);
            } else {
                promociones = new HashMap<>();
            }
        } catch (Exception ex) {
            Logger.getLogger(AsignarRequisitosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void onPromoChange() throws Exception {
        try {
            requisitos = new HashMap<>();
            lstClsR = new ArrayList<>();
            if (idPromo != null && !idPromo.equals("")) {
//                reqB = new RequisitosBean();
//                reqB.init();
//                listaRequisitos = reqB.getListaRequisitos();
//                reqSelec = reqB.getListaReqPro(idPromo, idMaestria);
                requisitos = data2.get(idPromo);
                Iterator it = requisitos.keySet().iterator();
                while (it.hasNext()) {
                    String key = it.next().toString();
                    lstClsR.add(new ClsRequisito(Integer.valueOf(requisitos.get(key)), key));

                }

            } else {
                requisitos = new HashMap<>();
            }
        } catch (Exception ex) {
            Logger.getLogger(AsignarRequisitosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void onProvinciaNacChange() {
        try {
            cantones = new HashMap<>();
            parroquias = new HashMap<>();
            if (idProvinciaNac != null && !idProvinciaNac.equals("")) {
                cantones = dataProCan.get(idProvinciaNac);
            } else {
                cantones = new HashMap<>();
            }
        } catch (Exception ex) {
            Logger.getLogger(AsignarRequisitosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void onCantonNacChange() {
        try {
            parroquias = new HashMap<>();
            if (idCantonNac != null && !idCantonNac.equals("")) {
                parroquias = dataCanPar.get(idCantonNac);
            } else {
                parroquias = new HashMap<>();
            }
        } catch (Exception ex) {
            Logger.getLogger(AsignarRequisitosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void onProvinciaDomChange() {
        try {
            cantonesDom = new HashMap<>();
            parroquiasDom = new HashMap<>();
            if (idProvinciaDom != null && !idProvinciaDom.equals("")) {
                cantonesDom = dataProCan.get(idProvinciaDom);
            } else {
                cantonesDom = new HashMap<>();
            }
        } catch (Exception ex) {
            Logger.getLogger(AsignarRequisitosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void onCantonDomChange() {
        try {
            parroquiasDom = new HashMap<>();
            if (idCantonDom != null && !idCantonDom.equals("")) {
                parroquiasDom = dataCanPar.get(idCantonDom);
            } else {
                parroquiasDom = new HashMap<>();
            }
        } catch (Exception ex) {
            Logger.getLogger(AsignarRequisitosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void onProvinciaTraChange() {
        try {
            cantonesTra = new HashMap<>();
            parroquiasTra = new HashMap<>();
            if (idProvinciaTra != null && !idProvinciaTra.equals("")) {
                cantonesTra = dataProCan.get(idProvinciaTra);
            } else {
                cantonesTra = new HashMap<>();
            }
        } catch (Exception ex) {
            Logger.getLogger(AsignarRequisitosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void onCantonTraChange() {
        try {
            parroquiasTra = new HashMap<>();
            if (idCantonTra != null && !idCantonTra.equals("")) {
                parroquiasTra = dataCanPar.get(idCantonTra);
            } else {
                parroquiasTra = new HashMap<>();
            }
        } catch (Exception ex) {
            Logger.getLogger(AsignarRequisitosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void obtenerRequisitos() throws Exception {
        if (reqB != null) {
            lstReqProNombre = new HashMap<>();
            lstReqProNombre = reqB.getListaReqProNombre(Integer.valueOf(idPromo), Integer.valueOf(idMaestria));
        }
    }
    

    public void handleFileUpload(FileUploadEvent event) {

        UploadedFile file = event.getFile();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateobj = new Date();
        String nombreFecha = (df.format(dateobj).replaceAll(":", "-"));       
         Path path = Paths.get("D:\\Postgrado\\inscripciones\\requisitos\\"+nombreFecha.trim());
        //if directory exists?
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                //fail to create directory
                e.printStackTrace();
            }
        }
        String filename = file.getFileName();
//         String extension = f.getContentType();
        Path ruta = Paths.get(path +"\\"+ filename);

        try (InputStream input = file.getInputstream()) {
            Files.copy(input, ruta, StandardCopyOption.REPLACE_EXISTING);
            FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (IOException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage message = new FacesMessage("Succesful", ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

}

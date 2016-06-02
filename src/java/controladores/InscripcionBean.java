/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Clases.ClsRequisito;
import Dao.ArchivosDao;
import Dao.InscripcionDao;
import Dao.LocalizacionDao;
import Dao.PagosDao;
import Dao.PromocionDao;
import Dao.RequisitosDao;
import Dao.UniversidadesDao;
import Pojo.Archivos;
import Pojo.Canton;
import Pojo.DatosDom;
import Pojo.DatosLab;
import Pojo.DatosNac;
import Pojo.Estudiante;
import Pojo.Pago;
import Pojo.Pais;
import Pojo.Parroquia;
import Pojo.Promocion;
import Pojo.Provincia;
import Pojo.Requisitos;
import Pojo.RequisitosPromo;
import Pojo.SolicitudInscripcion;
import Pojo.Titulo;
import Pojo.Universidad;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.apache.commons.io.FilenameUtils;
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
    private List<RequisitosPromo> reqPro;
    private List<ClsRequisito> lstClsR;
    private List<SelectItem> lstPais;
    private List<SelectItem> lstProvincia;
    private List<SelectItem> lstUniversidades;
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
    private MaestriaBean mBean;
    private Map<String, Map<String, String>> data = new HashMap<>();
    private Map<String, Map<String, String>> data2 = new HashMap<>();
    private Map<String, Map<String, String>> dataProCan = new HashMap<>();
    private Map<String, Map<String, String>> dataCanPar = new HashMap<>();
    private List<String> reqSelec;
    private List<SelectItem> listaRequisitos;
    private Estudiante estudiante = new Estudiante();
    private DatosNac datosNac = new DatosNac();
    private DatosDom datosDom = new DatosDom();
    private DatosLab datosLab = new DatosLab();
    private SolicitudInscripcion sInscripcion;
    private Archivos archivos = new Archivos();
    private Titulo titulo = new Titulo();
    private Universidad universidad = new Universidad();
    private String idPaisOrigen;
    private String idProvinciaNac = "";
    private String idCantonNac = "";
    private String idParroquiaNac = "";
    private String idProvinciaDom;
    private String idCantonDom;
    private String idParroquiaDom;
    private String idProvinciaTra;
    private String idCantonTra;
    private String idParroquiaTra;
    private String idUniversidad;
    private String idEcuador;
    private String universidadNueva;
    private String idPaisUniversidad;
    private String tituloDescr;
    private String numeroSenecyt;
    private int numeroReq;
    private UploadedFile file;
    private List<UploadedFile> files = new ArrayList<>();
    private boolean btnMostrar = true;
    private boolean btnGuardar = false;
    private String descripcionMaetria;
    private String descripcionPromo;
    private List<SelectItem> items;
    private List<Promocion> lstPromocion;
    private String filename = "";
    private String extension = "";
    private String cedOpas = "cedula";

    public String getCedOpas() {
        return cedOpas;
    }

    public void setCedOpas(String cedOpas) {
        this.cedOpas = cedOpas;
    }

    public boolean isBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(boolean btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    public String getDescripcionPromo() {
        return descripcionPromo;
    }

    public void setDescripcionPromo(String descripcionPromo) {
        this.descripcionPromo = descripcionPromo;
    }

    public boolean isBtnMostrar() {
        return btnMostrar;
    }

    public void setBtnMostrar(boolean btnMostrar) {
        this.btnMostrar = btnMostrar;
    }

    public List<RequisitosPromo> getReqPro() {
        return reqPro;
    }

    public void setReqPro(List<RequisitosPromo> reqPro) {
        this.reqPro = reqPro;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
        this.files.add(file);
    }

    public List<UploadedFile> getFiles() {
        return files;
    }

    public void setFiles(List<UploadedFile> files) {
        this.files = files;
    }

    public int getNumeroReq() {
        return numeroReq;
    }

    public void setNumeroReq(int numeroReq) {
        this.numeroReq = numeroReq;
    }

    public String getTituloDescr() {
        return tituloDescr;
    }

    public void setTituloDescr(String tituloDescr) {
        this.tituloDescr = tituloDescr;
    }

    public String getNumeroSenecyt() {
        return numeroSenecyt;
    }

    public void setNumeroSenecyt(String numeroSenecyt) {
        this.numeroSenecyt = numeroSenecyt;
    }

    public Universidad getUniversidad() {
        return universidad;
    }

    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }

    public String getIdPaisUniversidad() {
        return idPaisUniversidad;
    }

    public void setIdPaisUniversidad(String idPaisUniversidad) {
        this.idPaisUniversidad = idPaisUniversidad;
    }

    public String getUniversidadNueva() {
        return universidadNueva;
    }

    public void setUniversidadNueva(String universidadNueva) {
        this.universidadNueva = universidadNueva;
    }

    public List<SelectItem> getLstUniversidades() {
        return lstUniversidades;
    }

    public void setLstUniversidades(List<SelectItem> lstUniversidades) {
        this.lstUniversidades = lstUniversidades;
    }

    public Titulo getTitulo() {
        return titulo;
    }

    public void setTitulo(Titulo titulo) {
        this.titulo = titulo;
    }

    public DatosNac getDatosNac() {
        return datosNac;
    }

    public void setDatosNac(DatosNac datosNac) {
        this.datosNac = datosNac;
    }

    public DatosDom getDatosDom() {
        return datosDom;
    }

    public void setDatosDom(DatosDom datosDom) {
        this.datosDom = datosDom;
    }

    public DatosLab getDatosLab() {
        return datosLab;
    }

    public void setDatosLab(DatosLab datosLab) {
        this.datosLab = datosLab;
    }

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

    public boolean errorArchivos() {
        int x = 0;
        boolean error = false;
        for (UploadedFile f : files) {
            extension = FilenameUtils.getExtension(f.getFileName());
            if (!reqPro.get(x).getRequisitos().getTipoArchivo().contains(extension)) {
                error = true;
                files.clear();
                break;
            }
            x++;
        }
        return error;
    }

    public void guardarArchivos() {

        try {

            for (SelectItem i : items) {
                if (i.getValue().toString().equals(idMaestria)) {
                    descripcionMaetria = i.getLabel();
                }
            }
            PromocionDao pD = new PromocionDao();
            descripcionPromo = pD.getPromocion(idPromo).getDescripcion().toString();

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dateobj = new Date();
            String nombreCarpeta = (descripcionMaetria + "-" + descripcionPromo + "-" + estudiante.getApellidos() + " " + estudiante.getNombres() + "-" + df.format(dateobj).replaceAll(":", "-")).trim();
            File directorio = new File("d:/Postgrado/inscripciones/requisitos/" + nombreCarpeta + "/");
            if (!directorio.exists()) {
                directorio.mkdirs();
            }
            int cont = 0;
            ArchivosDao aDao = new ArchivosDao();
            for (UploadedFile f : files) {
                filename = reqPro.get(cont).getRequisitos().getFormato();
                extension = FilenameUtils.getExtension(f.getFileName());
                Path ruta = Paths.get(directorio + "/" + filename + "." + extension);
                InputStream input = f.getInputstream();
                Files.copy(input, ruta, StandardCopyOption.REPLACE_EXISTING);
                archivos = new Archivos();
                archivos.setRuta(ruta.toString());
                archivos.setRequisitosPromo(reqPro.get(cont));
                archivos.setSolicitudInscripcion(sInscripcion);
                aDao.insertar(archivos);                
                cont++;
            }

            // resultado= "/faces/index?faces-redirect=true";
        } catch (IOException ex) {
            Logger.getLogger(InscripcionBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage message = new FacesMessage("Error", ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, message);
            //resultado ="";
        } catch (Exception ex) {
            Logger.getLogger(InscripcionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        files.clear();

    }

    @PostConstruct
    public void init() {
        try {
            //datos de las universidades
            UniversidadesDao uDao = new UniversidadesDao();
            lstUniversidades = new ArrayList<>();
            SelectItemGroup g1 = new SelectItemGroup("Ecuador");
            SelectItem[] temp = uDao.getUniversidades(true);
            if (temp != null) {
                g1.setSelectItems(temp);
                lstUniversidades.add(g1);
            }
            SelectItemGroup g2 = new SelectItemGroup("Extranjero");
            SelectItem[] temp2 = uDao.getUniversidades(false);
            if (temp2 != null) {
                g2.setSelectItems(temp2);
                lstUniversidades.add(g2);
            }

            //datos de los combos de maestria y promociones ademas de los requisitos solicitados
            maestrias = new HashMap<>();
            items = new ArrayList<>();
            lstPromocion = new ArrayList<>();
            mBean = new MaestriaBean();
            mBean.init();
            items = mBean.getListaMaestrias();
            PromocionDao daoTPromocion = new PromocionDao();
            RequisitosDao daoRequisitos = new RequisitosDao();

            for (SelectItem i : items) {
                maestrias.put(i.getLabel(), i.getValue().toString());
                lstPromocion = daoTPromocion.getPromocionesMaestrias(Integer.valueOf(i.getValue().toString()));
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
                if (p.getDescripcion().equalsIgnoreCase("ecuador")) {
                    idEcuador = String.valueOf(p.getId());
                }
            }
            //prov canton parroq
            provincias = new LinkedHashMap<>();
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

                Map<String, String> map3 = new LinkedHashMap<>();
                for (Canton c : lstC) {
                    map3.put(c.getDescripcion(), String.valueOf(c.getId()));
                    List<Parroquia> lstPa = locDao.getParroquiaCanton(c.getId());
                    Map<String, String> map4 = new LinkedHashMap<>();
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
            btnMostrar = true;
        } catch (Exception ex) {
            Logger.getLogger(AsignarRequisitosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void onPromoChange() throws Exception {
        try {
            requisitos = new HashMap<>();
            lstClsR = new ArrayList<>();
            reqPro = new ArrayList<>();
            if (idPromo != null && !idPromo.equals("")) {

//                requisitos = data2.get(idPromo);
//                Iterator it = requisitos.keySet().iterator();
//                while (it.hasNext()) {
//                    String key = it.next().toString();
//                    lstClsR.add(new ClsRequisito(Integer.valueOf(requisitos.get(key)), key));
                RequisitosDao rpD = new RequisitosDao();
                reqPro = rpD.getRequisitosPromocion(idPromo, idMaestria);
//                }
//                numeroReq=lstClsR.size();
                btnMostrar = false;
            } else {
                requisitos = new HashMap<>();
                btnMostrar = true;
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

    public void guardar() {
        try {
            if (!errorArchivos()) {
                //estudiante ya se ha obtenido los datos solo hay q poner el paisOrigen
                LocalizacionDao lDao = new LocalizacionDao();
                Parroquia p;
                estudiante.setPaisOrigen(lDao.getNombrePais(idPaisOrigen));
                //datosNac
                if (!"".equals(idProvinciaNac) && !"".equals(idParroquiaNac) && !"".equals(idCantonNac)) {
                    datosNac.setFechaNac(estudiante.getFechaNac());
                    datosNac.setEstudiante(estudiante);
                    p = lDao.getParroquia(idParroquiaNac);
                    datosNac.setParroquia(p);
                } else {
                    datosNac = null;
                }
                //datosDom
                if (!"".equals(idProvinciaDom) && !"".equals(idParroquiaDom) && !"".equals(idCantonDom)) {
                    datosDom.setEstudiante(estudiante);
                    p = lDao.getParroquia(idParroquiaDom);
                    datosDom.setParroquia(p);
                } else {
                    datosDom = null;
                }
                //datosLab
                if (!"".equals(idProvinciaTra) && !"".equals(idParroquiaTra) && !"".equals(idCantonTra)) {
                    datosLab.setEstudiante(estudiante);
                    p = lDao.getParroquia(idParroquiaTra);
                    datosLab.setParroquia(p);
                } else {
                    datosLab = null;
                }
                //Academica
                if (idUniversidad.equals("Nueva")) {
                    universidad.setDescripcion(universidadNueva);
                    Pais paisP = lDao.getPais(idPaisUniversidad);
                    universidad.setPais(paisP);
                    titulo.setUniversidad(universidad);
                    titulo.setNSenecyt(numeroSenecyt);
                } else {
                    UniversidadesDao uDao = new UniversidadesDao();
                    titulo.setUniversidad(uDao.getUniversidad(idUniversidad));
                    titulo.setNSenecyt(numeroSenecyt);
                    titulo.setDescripcion(tituloDescr);
                }
                sInscripcion = new SolicitudInscripcion();
                sInscripcion.setEstado('E');
                Date dateobj = new Date();
                sInscripcion.setFechaRealizacion(dateobj);
                sInscripcion.setEstudiante(estudiante);
                PromocionDao pDao = new PromocionDao();
                sInscripcion.setPromocion(pDao.getPromocion(idPromo));
                InscripcionDao iDao = new InscripcionDao();
                if (iDao.insertar(estudiante, datosNac, datosDom, datosLab, sInscripcion, titulo, universidad)) {
                    guardarArchivos();
                    FacesMessage message = new FacesMessage("Succesful", "Datos guardados");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    btnGuardar = true;
                } else {
                    FacesMessage message = new FacesMessage("Error", "Ha habido un problema");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    btnGuardar = false;
                }
            } else {
                FacesMessage message = new FacesMessage("Error", "Revise los formatos de los archivos que intenta subir");
                FacesContext.getCurrentInstance().addMessage(null, message);
                btnGuardar = false;
            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public void validadorDeCedula() {
        int total = 0;
        int tamanoCedula = 10;
        int[] coeficientes = {2, 1, 2, 1, 2, 1, 2, 1, 2};
        int numeroProvincias = 24;
        int tercerDigito = 6;
        String cedula = cedOpas;
        if (cedula.matches("[0-9]*") && cedula.length() == tamanoCedula) {
            int provincia = Integer.parseInt(cedula.substring(0, 2));
            int digitoTres = Integer.parseInt(cedula.charAt(2) + "");
            if ((provincia > 0 && provincia <= numeroProvincias) && digitoTres < tercerDigito) {
                int digitoVerificadorRecibido = Integer.parseInt(cedula.charAt(9) + "");
                for (int i = 0; i < coeficientes.length; i++) {
                    int valor = coeficientes[i] * Integer.parseInt(cedula.charAt(i) + "");
                    total = valor >= 10 ? total + (valor - 9) : total + valor;
                }
                int digitoverificadorObtenido = total >= 10 ? (total % 10) != 0 ? 10 - (total % 10) : (total % 10) : total;
                if (digitoverificadorObtenido != digitoVerificadorRecibido) {
                    estudiante.setCedPasaporte("");
                    FacesMessage message = new FacesMessage("Error", "Número de Cédula inválido");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }
        }

    }

}

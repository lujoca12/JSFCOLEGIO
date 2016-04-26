/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Dao.DaoTMaestrias;
import Dao.PromocionDao;
import Pojo.Maestria;
import Pojo.Promocion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.xml.rpc.encoding.Serializer;

/**
 *
 * @author chiti
 */
@ManagedBean
@ViewScoped
public class MaestriaBean implements Serializable{

    private List<SelectItem> listaMaestrias;
    private Maestria maestria;

    public List<SelectItem> getListaMaestrias() {
        return listaMaestrias;
    }

    public void setListaMaestrias(List<SelectItem> listaMaestrias) {
        this.listaMaestrias = listaMaestrias;
    }

    public Maestria getMaestria() {
        return maestria;
    }

    public void setMaestria(Maestria maestria) {
        this.maestria = maestria;
    }
  

    /**
     * Creates a new instance of MaestriaBean
     */

    /**
     * Creates a new instance of MaestriaBean
     */
    @PostConstruct
    public void init() {
       try {
           String x="";
            listaMaestrias = new ArrayList<>();
            PromocionDao dao = new PromocionDao();
            List<Promocion> lstMaestria = dao.getMaestriasPromocion();
            for (Promocion p : lstMaestria) {
//                for(int i=0;i< p.getMaestria(). ;i++)
//                {
//                    x= m.
//                }
//                SelectItem item = new SelectItem(m.getId(), m.getDescripcion()+" Promoción "+x);
//                listaMaestrias.add(item);
            }
        } catch (Exception ex) {
            Logger.getLogger(MaestriaBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfaceModulos;
import Pojo.Modulo;
import Pojo.Usuario;
import java.util.List;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author server
 */
public class DaoTModulo implements InterfaceModulos{
    
    private Session sesion;
    private Transaction tx;
    
    private void iniciaOperacion()
    {
        try{
            sesion = HibernateUtil.getSessionFactory().openSession();
            tx = sesion.beginTransaction();
        }catch(HibernateException ex){
            
        }
        
    }
     
    private void manejaExcepcion(HibernateException he) throws HibernateException
    {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }

    @Override
    public boolean registrar(Modulo tModulo) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.save(tModulo);

            tx.commit();
            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }

    @Override
    public List<Modulo> getTodosModulo() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from Permiso as p where p.estado = '1' order by p.orden, p.descripcion asc";
        Query query = sesion.createQuery(hql);
        List<Modulo> lstPermiso=(List<Modulo>) query.list();
        sesion.close();
        return lstPermiso;
    }
    
    @Override
    public List<Modulo> getTblModulos() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        //Presento los modulos registrados x años 
        String hql="from Modulo mod inner join fetch mod.promocion p "
                + "inner join fetch mod.usuario user "
                + "inner join fetch p.maestria m where m.estado='1' and "
                + "(year(current_date) >= year(p.fechaInicio) "
                + "and year(current_date)<= year(p.fechaFin)) order by mod.id desc";
        Query query = sesion.createQuery(hql);
        List<Modulo> lstPermiso=(List<Modulo>) query.list();
        sesion.close();
        return lstPermiso;
    }
    
    @Override
    public List<Modulo> getTblModulosMaestria(int idMaestria) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        //Presento los modulos registrados x años 
        String hql = "from Modulo modul inner join fetch modul.usuario user \n"
                + "inner join fetch  user.tipoUsuario tuser \n"
                + "inner join fetch modul.promocion pr \n"
                + "inner join fetch pr.maestria maest \n"
                + "where maest.id = "+idMaestria+" and\n"
                + "(year(current_date) >= year(pr.fechaInicio) and year(current_date)<= year(pr.fechaFin))\n"
                + "and (tuser.descripcion like '%Prof%' or tuser.descripcion like '%prof%' or tuser.descripcion like '%Docen%' or tuser.descripcion like '%docent%') \n"
                + "order by modul.descripcion asc";
        Query query = sesion.createQuery(hql);
        List<Modulo> lstPermiso=(List<Modulo>) query.list();
        sesion.close();
        return lstPermiso;
    }
    
    @Override
    public List<Modulo> getCboModulosNotas(int usuario_id) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        
//        //Recogiendo Datos de la sesion para saber que usuario ingreso la maestria promocion
//        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");  
        String consulta = "";
        String fecha = "";
       
        if(usuario_id == 0){
            consulta = "";
            fecha = "";
        }
        else{
            consulta = "user.id="+usuario_id+" and";
            fecha="and (current_date >= modul.fechaInicioExamen and current_date <= modul.fechaFinExamen) ";
        }

        String hql="from Modulo modul inner join fetch modul.usuario user inner join fetch  user.tipoUsuario tuser inner join fetch modul.promocion pr inner join fetch pr.maestria maest \n" +
                    "where "+consulta+" \n" +
                    "(year(current_date) >= year(pr.fechaInicio) and year(current_date)<= year(pr.fechaFin))\n" +
                    ""+fecha+" "
                + "and (tuser.descripcion like '%Prof%' or tuser.descripcion like '%prof%' or tuser.descripcion like '%Docen%' or tuser.descripcion like '%docent%') order by modul.descripcion asc";
        Query query = sesion.createQuery(hql);
        List<Modulo> lstPermiso=(List<Modulo>) query.list();
        sesion.close();
        return lstPermiso;
    }
    
    @Override
    public List<Modulo> getCboModulosAsistencias(int usuario_id) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        
//        //Recogiendo Datos de la sesion para saber que usuario ingreso la maestria promocion
//        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");  
        String consulta = "";
        String fecha = " (current_date >= modul.fechaInicio and current_date <= modul.fechaInicioExamen) ";
       
//        if(usuario_id == 0){
//            consulta = "";
//            fecha = "";
//        }
//        else{
            consulta = "user.id="+usuario_id+"";
//            fecha="and (current_date >= modul.fechaInicio and current_date <= modul.fechaFin) ";
//        }

        String hql = "from Modulo modul inner join fetch modul.usuario user \n"
                + "inner join fetch  user.tipoUsuario tuser \n"
                + "inner join fetch modul.promocion pr \n"
                + "inner join fetch pr.maestria maest \n"
                + "where "+consulta+" and\n"
                + "(year(current_date) >= year(pr.fechaInicio) and year(current_date)<= year(pr.fechaFin)) and \n"
                + ""+fecha+"\n"
                + "and (tuser.descripcion like '%Prof%' or tuser.descripcion like '%prof%' or tuser.descripcion like '%Docen%' or tuser.descripcion like '%docent%') \n"
                + "order by modul.descripcion asc";
        Query query = sesion.createQuery(hql);
        List<Modulo> lstPermiso=(List<Modulo>) query.list();
        sesion.close();
        return lstPermiso;
    }
    
    @Override
    public List<Modulo> getTblModulosNotas(int idPromocion) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        //Presento los modulos registrados x años 
        String hql="from Modulo mod where mod.promocion = "+idPromocion+" order by mod.descripcion asc";
        Query query = sesion.createQuery(hql);
        List<Modulo> lstModulos=(List<Modulo>) query.list();
        sesion.close();
        return lstModulos;
    }

    @Override
    public Modulo getModulo(String idModulo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Modulo tModulo) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.update(tModulo);

            tx.commit();
            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }
    
    @Override
    public boolean delete(Modulo tModulo) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.delete(tModulo);

            tx.commit();
            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }
    
    @Override
    public boolean existe(Modulo tModulo) throws Exception {
        this.sesion = null;
        this.tx = null;
        boolean band = false;
        
        
        iniciaOperacion();
        String hql="from Modulo m where m.descripcion='"+tModulo.getDescripcion()+"' and "
                + "m.promocion="+tModulo.getPromocion().getId()+" and "
                + "m.usuario="+tModulo.getUsuario().getId()+"";
        Query query = sesion.createQuery(hql);
        List<Modulo> modulo=(List<Modulo>) query.list();
        if(modulo.size() > 0)
            band = true;
        else
            band = false;
        
        sesion.close();
        return band;
    }
    
    @Override
    public List<Modulo> validacionModulos(Modulo tModulo) throws Exception {
        this.sesion = null;
        this.tx = null;
        boolean band = false;
        
        
        iniciaOperacion();
        String hql="from Modulo m where "
                + "m.promocion="+tModulo.getPromocion().getId()+" and "
                + "m.usuario="+tModulo.getUsuario().getId()+" order by m.fechaInicio, m.fechaFin desc ";
        Query query = sesion.createQuery(hql);
        List<Modulo> modulo=(List<Modulo>) query.list();
        if(modulo.size() > 0)
            band = true;
        else
            band = false;
        
        sesion.close();
        return modulo;
    }
    
}

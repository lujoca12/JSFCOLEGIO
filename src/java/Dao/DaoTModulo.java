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
    public List<Modulo> getTodosModulo(int idModulo) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from Modulo m where m.id = "+idModulo+" and m.estado <> '0'";
        Query query = sesion.createQuery(hql);
        List<Modulo> lstPermiso=(List<Modulo>) query.list();
        sesion.close();
        return lstPermiso;
    }
    
    @Override
    public List<Modulo> getTblModulos(String moduloDescripcion, boolean mostrar) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String consulta = "";
        
        String estado = "";
        if(mostrar)
            estado = "mod.estado = '0'";
        else
            estado = "mod.estado <> '0'";
        
        //Presento los modulos registrados x años 
        if(moduloDescripcion.isEmpty())
            consulta = "";
        else
            consulta = "and m.descripcion like '%"+moduloDescripcion+"%'";
        
        String hql="from Modulo mod inner join fetch mod.promocion p "
                + "inner join fetch mod.usuario user "
                + "inner join fetch mod.materias mater "
                + "inner join fetch p.maestria m where m.estado='1' and "
                + "(year(current_date) >= year(p.fechaInicio) "
                + "and year(current_date)<= year(p.fechaFin)) "
                + ""+consulta+" and p.estado = '1' and "+estado+" and mater.estado = '1' order by mod.id desc";
        Query query = sesion.createQuery(hql);
        List<Modulo> lstPermiso=(List<Modulo>) query.list();
        sesion.close();
        return lstPermiso;
    }
    
    @Override
    public List<Modulo> getValidacionModulos(String moduloDescripcion, String nModulo) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String consulta = "";
        
        String estado = "0";
       
        
        //Presento los modulos registrados x años 
        if(moduloDescripcion.isEmpty())
            consulta = "";
        else
            consulta = "and m.descripcion = '"+moduloDescripcion+"'";
        
        String hql="from Modulo mod inner join fetch mod.promocion p "
                + "inner join fetch mod.usuario user "
                + "inner join fetch p.maestria m where m.estado='1' and "
                + "(year(current_date) >= year(p.fechaInicio) "
                + "and year(current_date)<= year(p.fechaFin)) "
                + ""+consulta+" and p.estado = '1' and mod.estado <> '"+estado+"' and mod.modulo = '"+nModulo+"' order by mod.id desc";
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
                + "where pr.id = "+idMaestria+" and\n"
                + "(year(current_date) >= year(pr.fechaInicio) and year(current_date)<= year(pr.fechaFin))\n"
                + "and (tuser.descripcion like '%Prof%' or tuser.descripcion like '%prof%' or tuser.descripcion like '%Docen%' or tuser.descripcion like '%docent%') \n"
                + " and pr.estado = '1' and modul.estado <> '0' order by modul.descripcion asc";
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
            fecha = " and modul.estado <> '0'";
        }
        else{
            consulta = "user.id="+usuario_id+" and";
            //fecha="and (modul.estado = '1' and (current_date >= modul.fechaFinExamen and current_date <= modul.fechaFinExamen+8)) or modul.estado = 'P' ";
            fecha="and (modul.estado = '1') or modul.estado = 'P' ";
        }

        String hql="from Modulo modul inner join fetch modul.usuario user inner join fetch  user.tipoUsuario tuser inner join fetch "
                + "modul.promocion pr inner join fetch pr.maestria maest inner join fetch modul.materias mater \n" +
                    "where "+consulta+" \n" +
                    "(year(current_date) >= year(pr.fechaInicio) and year(current_date)<= year(pr.fechaFin))\n" +
                    ""+fecha+" "
                + "and (tuser.descripcion like '%Prof%' or tuser.descripcion like '%prof%' or tuser.descripcion like '%Docen%' or tuser.descripcion like '%docent%') "
                + "and mater.estado <> '0' order by modul.descripcion asc";
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
        String fecha = "and (modul.estado = '1' and (current_date >= modul.fechaInicio and current_date <= modul.fechaFinExamen)) or modul.estado = 'P' ";
       
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
                + "(year(current_date) >= year(pr.fechaInicio) and year(current_date)<= year(pr.fechaFin)) \n"
                + ""+fecha+"\n"
                + "and (tuser.descripcion like '%Prof%' or tuser.descripcion like '%prof%' or tuser.descripcion like '%Docen%' or tuser.descripcion like '%docent%') \n"
                + " order by modul.descripcion asc";
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
        String hql="from Modulo mod where mod.promocion = "+idPromocion+" and mod.estado <> '0' order by mod.descripcion asc";
        Query query = sesion.createQuery(hql);
        List<Modulo> lstModulos=(List<Modulo>) query.list();
        sesion.close();
        return lstModulos;
    }
    
    @Override
    public List<Modulo> getModulosConfid(int idPromocion, int usuarioId) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        //Presento los modulos registrados x años 
        String hql="from Modulo mod inner join fetch mod.promocion pr "
                + "inner join fetch mod.usuario user inner join fetch pr.maestria maest "
                + "where pr.id = "+idPromocion+" and mod.estado <> '0' and "
                + "user.id="+usuarioId+" order by mod.modulo asc";
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
        //or  m.descripcion<>'"+tModulo.getDescripcion()+"'
        String hql = "from Modulo m inner join fetch m.promocion p where p.id="+tModulo.getPromocion().getId()+" and m.usuario="+tModulo.getUsuario().getId()+" and \n"
                + "m.estado <> '0' and (year(current_date) >= year(p.fechaInicio) and year(current_date)<= year(p.fechaFin)) and \n"
                + "m.curso = "+tModulo.getCurso().getId()+" and m.materias = "+tModulo.getMaterias().getId()+" and p.estado = '1'";
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
    
    @Override
    public List<Modulo> getNumeroModulo(int idPromocion) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from Modulo m inner join fetch m.promocion pr where pr.id = "+idPromocion+" order by m.fechaFinExamen desc";
        Query query = sesion.createQuery(hql);
        List<Modulo> lstPermiso=(List<Modulo>) query.list();
        sesion.close();
        return lstPermiso;
    }
    
    @Override
    public List<Modulo> getProyectoTesisRegistrado(int idPromocion, String moduloDescripcion) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from Modulo m inner join fetch m.promocion pr where pr.id = "+idPromocion+" and m.modulo like '%Proyecto%' order by m.fechaFinExamen desc";
        Query query = sesion.createQuery(hql);
        List<Modulo> lstPermiso=(List<Modulo>) query.list();
        sesion.close();
        return lstPermiso;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfaceMaestrias;
import Pojo.Maestria;
import Pojo.Promocion;
import Pojo.Usuario;
import java.util.List;
import javax.faces.context.FacesContext;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author server
 */
public class DaoTMaestrias implements InterfaceMaestrias{
    
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
    public boolean registrarMaestriaPromocion(Maestria tMaestria, Promocion tPromocion) throws Exception {
        
        boolean band = false;
        DaoTPromocion daoTpromocion = new DaoTPromocion();
        try {
            
            //invocando al metodo que obtienesi ya esta la maestria ingresada
            List<Maestria> lstMaestria = (List<Maestria>)getMaestriasxDescripcion(tMaestria.getDescripcion());
            iniciaOperacion();
            //Recogiendo Datos de la sesion para saber que usuario ingreso la maestria promocion
            //Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");  
            
            if(lstMaestria.size() > 0){
                tMaestria.setId(lstMaestria.get(0).getId());
            }
            else
                sesion.save(tMaestria);
            
            tPromocion.setMaestria(tMaestria);
            int i = daoTpromocion.getUltimoidPromocion(tMaestria.getDescripcion());
                tPromocion.setDescripcion(i+1);
               // tPromocion.setIdUsuario(usuario.getId());
                
            sesion.save(tPromocion);
            band = true;
            tx.commit();
            sesion.close();
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }

    @Override
    public List<Maestria> getTodasMaestrias() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        //Consulta para que me aparezcan solo las maestrias registradas en el anio actual
        String hql="from Maestria m inner join fetch m.promocions pr where m.estado='1' and (year(current_date) >= year(pr.fechaInicio) and year(current_date)<= year(pr.fechaFin)) order by m.descripcion asc";
        //Consulta hasta Usuarios
        //String hql="from Maestria m inner join fetch m.promocions pr inner join fetch pr.modulos mod inner join fetch mod.usuario user where m.estado='1' and year(pr.fechaInicio) = year(current_date) and pr.idUsuario=user.id order by m.descripcion asc";
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Maestria> lstMaestrias=(List<Maestria>) query.list();
        sesion.close();
        return lstMaestrias;
    }
    
    @Override
    public List<Maestria> getMaestriasD(String maestriaDescripcion, boolean mostrar) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        
        String consulta = "";
        String estado = "";
        if(mostrar)
            estado = "0";
        else
            estado = "1";
        
        if(maestriaDescripcion.isEmpty())
            consulta = "";
        else
            consulta = "and m.descripcion like '%"+maestriaDescripcion+"%'";
        
        //String hql="from Maestria m where m.estado = '1' "+consulta+" order by m.id desc";
        String hql="from Maestria m where m.estado = '"+estado+"' "+consulta+" order by m.id desc";
        Query query = sesion.createQuery(hql);

        List<Maestria> lstMaestrias=(List<Maestria>) query.list();
        sesion.close();
        return lstMaestrias;
    }
    
    @Override
    public List<Maestria> getMaestriaPromocion() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from Maestria m inner join fetch m.promocions pr where m.estado = '1' order by m.descripcion asc";
        Query query = sesion.createQuery(hql);

        List<Maestria> lstMaestrias=(List<Maestria>) query.list();
        sesion.close();
        return lstMaestrias;
    }

    @Override
    public Maestria getMaestrias(String idMaestria) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Maestria tMaestria) throws Exception {
        boolean band = false;
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        try {
            sesion.update(tMaestria);
            band = true;
            tx.commit();
            sesion.close();
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }
    
    @Override
    public boolean delete(Maestria tMaestria) throws Exception {
        boolean band = false;
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        try {
            sesion.delete(tMaestria);
            band = true;
            tx.commit();
            sesion.close();
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }

    @Override
    public List<Maestria> getMaestriasxDescripcion(String descripcion) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from Maestria m where m.estado='1' and m.descripcion like '%"+descripcion+"%' order by m.descripcion asc";
        Query query = sesion.createQuery(hql);
        List<Maestria> lstMaestrias=(List<Maestria>) query.list();
        sesion.close();
        return lstMaestrias;
    }

    @Override
    public boolean registrar(Maestria tMaestria) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.save(tMaestria);

            tx.commit();
            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }

    
    
}

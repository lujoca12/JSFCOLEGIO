/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfacePromocion;
import Pojo.Promocion;
import java.util.Calendar;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author server
 */
public class DaoTPromocion implements InterfacePromocion{
    
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
    public boolean registrar(Promocion tPromocion) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.save(tPromocion);

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
    public List<Promocion> getTodasPromociones() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from Permiso as p where p.estado = '1' order by p.orden, p.descripcion asc";
        Query query = sesion.createQuery(hql);
        List<Promocion> lstPermiso=(List<Promocion>) query.list();
        sesion.close();
        return lstPermiso;
    }
    
    @Override
    public List<Promocion> getPromocionesMaestrias() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from Promocion pr inner join fetch pr.maestria m where m.estado='1' and (year(current_date) >= year(pr.fechaInicio) and year(current_date)<= year(pr.fechaFin)) order by pr.id desc";
        Query query = sesion.createQuery(hql);
        List<Promocion> lstPermiso=(List<Promocion>) query.list();
        sesion.close();
        return lstPermiso;
    }

    @Override
    public Promocion getPromocion(int idMaestria) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from Permiso as p where p.estado = '1' order by p.orden, p.descripcion asc";
        Query query = sesion.createQuery(hql);
        Promocion promocion=(Promocion) query.uniqueResult();
        sesion.close();
        return promocion;
    }

    @Override
    public boolean update(Promocion tPromocion) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.update(tPromocion);

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
    public int getUltimoidPromocion(String descripcion) throws Exception {
        this.sesion = null;
        this.tx = null;
        int ultimoId = 0;
        iniciaOperacion();
        String hql="from Promocion as p inner join fetch p.maestria as m where m.descripcion like '%"+descripcion+"%' order by p.descripcion desc";
        Query query = sesion.createQuery(hql);
        List<Promocion> lstPromocion=(List<Promocion>) query.list();
        if(lstPromocion.size() > 0)
            ultimoId = lstPromocion.get(0).getDescripcion();
        else
            ultimoId = 0;
        
        sesion.close();
        return ultimoId;
    }

    @Override
    public boolean existe(Promocion tPromocion) throws Exception {
        this.sesion = null;
        this.tx = null;
        boolean band = false;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tPromocion.getFechaInicio());
        int anioInicio = calendar.get(Calendar.YEAR);
        
        calendar.setTime(tPromocion.getFechaFin());
        int anioFin = calendar.get(Calendar.YEAR);
        
        iniciaOperacion();
        String hql="from Promocion as pr where pr.maestria = "+tPromocion.getMaestria().getId()+" and "
                + "pr.idUsuario = "+tPromocion.getIdUsuario()+" and "
                + "year(pr.fechaInicio) = "+anioInicio+" and "
                + "year(pr.fechaFin) = "+anioFin+"";
        Query query = sesion.createQuery(hql);
        List<Promocion> promocion=(List<Promocion>) query.list();
        if(promocion.size() > 0)
            band = true;
        else
            band = false;
        
        sesion.close();
        return band;
    }
    
    @Override
    public boolean delete(Promocion tPromocion) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.delete(tPromocion);

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

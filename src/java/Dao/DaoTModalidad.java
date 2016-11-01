/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfaceModalidad;
import Pojo.Materias;
import Pojo.Modalidad;
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
public class DaoTModalidad implements InterfaceModalidad{
    
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
    public List<Modalidad> getTodasModalidades() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from Modalidad modal where modal.estado = '1' order by modal.id desc";
        Query query = sesion.createQuery(hql);
        List<Modalidad> lstModalidad=(List<Modalidad>) query.list();
        sesion.close();
        return lstModalidad;
    }

    @Override
    public boolean registrar(Modalidad tModalidad) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.saveOrUpdate(tModalidad);

            tx.commit();
            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }
    public boolean existe(Modalidad tModalidad) throws Exception {
        this.sesion = null;
        this.tx = null;
        boolean band = false;
        iniciaOperacion();
        String hql="from Modalidad modal where modal.descripcion='"+tModalidad.getDescripcion()+"' and modal.estado = '1'";
        Query query = sesion.createQuery(hql);
        List<Materias> materias=(List<Materias>) query.list();
        if(materias.size() > 0)
            band = true;
        else
            band = false;
        
        sesion.close();
        return band;
    }
    @Override
    public List<Modalidad> getModalidadxDescripcion(String descripcion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Modalidad tModalidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Modalidad tModalidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Modalidad getModalidad(String idModalidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Modalidad> getModalidad() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from Modalidad modal where modal.estado = '1' order by modal.descripcion asc";
        Query query = sesion.createQuery(hql);
        List<Modalidad> lstModalidad=(List<Modalidad>) query.list();
        sesion.close();
        return lstModalidad;
    }
    
}

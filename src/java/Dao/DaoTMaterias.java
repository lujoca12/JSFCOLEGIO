/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfaceMateria;
import Pojo.Maestria;
import Pojo.Materias;
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
public class DaoTMaterias implements InterfaceMateria{
    
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
    public List<Materias> getTodasMaterias() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from Materias mater where mater.estado = '1' order by mater.id desc";
        Query query = sesion.createQuery(hql);
        List<Materias> lstMaterias=(List<Materias>) query.list();
        sesion.close();
        return lstMaterias;
    }

    @Override
    public boolean registrar(Materias tMaterias) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.saveOrUpdate(tMaterias);

            tx.commit();
            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }
    public boolean existe(Materias tMaterias) throws Exception {
        this.sesion = null;
        this.tx = null;
        boolean band = false;
        iniciaOperacion();
        String hql="from Materias mater where mater.descripcion='"+tMaterias.getDescripcion()+"' and mater.estado = '1'";
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
    public List<Materias> getMateriasxDescripcion(String descripcion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Materias tMaterias) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Materias tMaterias) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Materias getMaterias(String idMaterias) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Materias> getMaterias() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

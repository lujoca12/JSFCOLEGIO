/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfaceTesis;
import Pojo.Maestria;
import Pojo.Matricula;
import Pojo.Proyecto;
import Pojo.PalabrasClave;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author SERVER
 */
public class DaoTesis implements InterfaceTesis{
    private Session sesion;
    private Transaction tx;
    private Session sesion1;
    private Transaction tx1;
    private void iniciaOperacion()
    {
        try{
            sesion = HibernateUtil.getSessionFactory().openSession();
            tx = sesion.beginTransaction();
            sesion1 = HibernateUtil.getSessionFactory().openSession();
            tx1 = sesion1.beginTransaction();
        }catch(HibernateException ex){
            
        }
        
        
    }

    @Override
    public List<Proyecto> getTodasProyecto() throws Exception {
        
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        //Consulta para que me aparezcan solo las maestrias registradas en el anio actual
        String hql="from tesis";
        //Consulta hasta Usuarios
        //String hql="from Maestria m inner join fetch m.promocions pr inner join fetch pr.modulos mod inner join fetch mod.usuario user where m.estado='1' and year(pr.fechaInicio) = year(current_date) and pr.idUsuario=user.id order by m.descripcion asc";
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Proyecto> lstMaestrias=(List<Proyecto>) query.list();
        sesion.close();
        return lstMaestrias;
    }

    @Override
    public List<Proyecto> getProyecto() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from Proyecto p\n" +
                "inner join fetch p.titulacion t\n" +
                "inner join fetch t.tipoTitulacion\n" +
                "";
        Query query = sesion.createQuery(hql);

        List<Proyecto> lstProyecto=(List<Proyecto>) query.list();
        sesion.close();
        return lstProyecto;
    }

    @Override
    public List<Proyecto> getProyectoPalabrasclaves(String palabra) throws Exception {
       this.sesion = null;
        this.tx = null;
        int id=0;
        Proyecto t1 = new Proyecto();
        iniciaOperacion();
       // String hql="from Usuario as u inner join fetch u.tipoUsuario as tu where tu.descripcion like '%Prof%' and u.estado='1' order by u.apellidos asc";
        String hql= "from PalabrasClave p where p.descripcion like '"+palabra+"'";
        Query query = sesion.createQuery(hql);
        List<PalabrasClave> lsttesi=(List<PalabrasClave>) query.list();
        sesion.close();        
        for (PalabrasClave pacla : lsttesi){
            t1 = pacla.getProyecto();
            id= t1.getId();
        }
        //sesion= null;
        String teid = "";
        teid = String.valueOf(id);
        String hql1= "from Proyecto t where t.id = '"+teid+"'";
        Query query1 = sesion1.createQuery(hql1);
        List<Proyecto> lst=(List<Proyecto>) query1.list();
        int ii=0;
        sesion1.close();
        return lst;

    }

    @Override
    public List<Proyecto> getProyectoxTitulo(String titulo) throws Exception {
       this.sesion= null;
       this.tx= null;
       iniciaOperacion();
        String hql="from Proyecto as t where t.titulo  like '%"+titulo+"%'";
        Query query = sesion.createQuery(hql);
        List<Proyecto> lsttesis=(List<Proyecto>) query.list();
        return lsttesis;
    }

    @Override
    public boolean registrarProyecto(Proyecto ttesis) throws Exception {
        boolean band = false;
        try{
            iniciaOperacion();
            sesion.save(ttesis);
            tx.commit();
            sesion.close();
            band=true;
        }catch(Exception e){
            tx.rollback();
            band=false;
        }
        
        
        return band;
    }

    @Override
    public List<Proyecto> getProyectoxAutor(String autor) throws Exception {
        this.sesion= null;
       this.tx= null;
       iniciaOperacion();
        String hql="from Proyecto as t where t.autor like '%"+autor+"%'";
        Query query = sesion.createQuery(hql);
        List<Proyecto> lsttesis=(List<Proyecto>) query.list();
        return lsttesis;
    }

    @Override
    public List<Proyecto> getProyectoxFechaSust(Date fecha) throws Exception {
        this.sesion= null;
       this.tx= null;
       iniciaOperacion();
        String hql="from Proyecto as t where t.fechaSustentacion = '"+fecha+"'";
        Query query = sesion.createQuery(hql);
        List<Proyecto> lsttesis=(List<Proyecto>) query.list();
        return lsttesis;
    }

    
    @Override
    public boolean registrarPalabrasClave(PalabrasClave tpalabrasclave) throws Exception {
        boolean band = false;
        try{
            iniciaOperacion();
            sesion.save(tpalabrasclave);
            tx.commit();
            sesion.close();
            band=true;
        }catch(Exception e){
            tx.rollback();
            band=false;
        }       
        return band;
    }

    @Override
    public List<Proyecto> getTodasProyectoxEstado(String estado) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from Proyecto pr\n" +
                "where pr.estado = '"+estado+"'";
        Query query = sesion.createQuery(hql);

        List<Proyecto> lstProyecto=(List<Proyecto>) query.list();
        sesion.close();
        return lstProyecto;
    }

    @Override
    public List<Proyecto> getProyectoxMaestria(String maestria) throws Exception {
        this.sesion= null;
        this.tx= null;
        iniciaOperacion();
        String hql="from Proyecto as t where t.maestria like '%"+maestria+"%'";
        Query query = sesion.createQuery(hql);
        List<Proyecto> lsttesis=(List<Proyecto>) query.list();
        return lsttesis;
    }

    @Override
    public boolean UpdateProyecto(Proyecto ttesis) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.saveOrUpdate(ttesis);

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
    public List<PalabrasClave> getTodasPC() throws Exception {
     this.sesion= null;
        this.tx= null;
        iniciaOperacion();
        String hql="from PalabrasClave pc\n" +
                "inner join pc.proyecto";
        Query query = sesion.createQuery(hql);
        List<PalabrasClave> lsttesis=(List<PalabrasClave>) query.list();
        return lsttesis;   
    }
}

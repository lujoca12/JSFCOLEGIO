/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfaceTesis;
import Pojo.Maestria;
import Pojo.Matricula;
import Pojo.Tesis;
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
    public List<Tesis> getTodasTesis() throws Exception {
        
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        //Consulta para que me aparezcan solo las maestrias registradas en el anio actual
        String hql="from tesis";
        //Consulta hasta Usuarios
        //String hql="from Maestria m inner join fetch m.promocions pr inner join fetch pr.modulos mod inner join fetch mod.usuario user where m.estado='1' and year(pr.fechaInicio) = year(current_date) and pr.idUsuario=user.id order by m.descripcion asc";
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Tesis> lstMaestrias=(List<Tesis>) query.list();
        sesion.close();
        return lstMaestrias;
    }

    @Override
    public List<Tesis> getTesis() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from Tesis";
        Query query = sesion.createQuery(hql);

        List<Tesis> lstTesis=(List<Tesis>) query.list();
        sesion.close();
        return lstTesis;
    }

    @Override
    public List<Tesis> getTesisPalabrasclaves(String palabra) throws Exception {
       this.sesion = null;
        this.tx = null;
        int id=0;
        Tesis t1 = new Tesis();
        iniciaOperacion();
       // String hql="from Usuario as u inner join fetch u.tipoUsuario as tu where tu.descripcion like '%Prof%' and u.estado='1' order by u.apellidos asc";
        String hql= "from PalabrasClave p where p.descripcion like '"+palabra+"'";
        Query query = sesion.createQuery(hql);
        List<PalabrasClave> lsttesi=(List<PalabrasClave>) query.list();
        sesion.close();        
        for (PalabrasClave pacla : lsttesi){
            t1 = pacla.getTesis();
            id= t1.getId();
        }
        //sesion= null;
        String teid = "";
        teid = String.valueOf(id);
        String hql1= "from Tesis t where t.id = '"+teid+"'";
        Query query1 = sesion1.createQuery(hql1);
        List<Tesis> lst=(List<Tesis>) query1.list();
        int ii=0;
        sesion1.close();
        return lst;

    }

    @Override
    public List<Tesis> getTesisxTitulo(String titulo) throws Exception {
       this.sesion= null;
       this.tx= null;
       iniciaOperacion();
        String hql="from Tesis as t where t.titulo  like '%"+titulo+"%'";
        Query query = sesion.createQuery(hql);
        List<Tesis> lsttesis=(List<Tesis>) query.list();
        return lsttesis;
    }

    @Override
    public boolean registrarTesis(Tesis ttesis) throws Exception {
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
    public List<Tesis> getTesisxAutor(String autor) throws Exception {
        this.sesion= null;
       this.tx= null;
       iniciaOperacion();
        String hql="from Tesis as t where t.autor like '%"+autor+"%'";
        Query query = sesion.createQuery(hql);
        List<Tesis> lsttesis=(List<Tesis>) query.list();
        return lsttesis;
    }

    @Override
    public List<Tesis> getTesisxFechaSust(Date fecha) throws Exception {
        this.sesion= null;
       this.tx= null;
       iniciaOperacion();
        String hql="from Tesis as t where t.fechaSustentacion = '"+fecha+"'";
        Query query = sesion.createQuery(hql);
        List<Tesis> lsttesis=(List<Tesis>) query.list();
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
}

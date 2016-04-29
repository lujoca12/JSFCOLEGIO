/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfaceTesis;
import Pojo.Maestria;
import Pojo.Tesis;
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
    private void iniciaOperacion()
    {
        try{
            sesion = HibernateUtil.getSessionFactory().openSession();
            tx = sesion.beginTransaction();
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
        String hql="from tesis";
        Query query = sesion.createQuery(hql);

        List<Tesis> lstTesis=(List<Tesis>) query.list();
        sesion.close();
        return lstTesis;
    }

    
    
    
}

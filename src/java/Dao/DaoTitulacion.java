/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import Interface.InterfaceTitulacion;
import Pojo.Maestria;
import Pojo.Matricula;
import Pojo.TipoTitulacion;
import Pojo.PalabrasClave;
import Pojo.TipoTitulacion;
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
public class DaoTitulacion implements InterfaceTitulacion{
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
    public List<TipoTitulacion> getTodastipoTitulacion() throws Exception {
        
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        //Consulta para que me aparezcan solo las tipos de titulacion registradas en el anio actual
        String hql="from TipoTitulacion";
        //Consulta hasta Usuarios
        //String hql="from Maestria m inner join fetch m.promocions pr inner join fetch pr.modulos mod inner join fetch mod.usuario user where m.estado='1' and year(pr.fechaInicio) = year(current_date) and pr.idUsuario=user.id order by m.descripcion asc";
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<TipoTitulacion> lsttipotitu=(List<TipoTitulacion>) query.list();
        sesion.close();
        return lsttipotitu;
    }
}

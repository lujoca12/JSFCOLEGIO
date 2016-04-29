/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Pojo.Promocion;
import Pojo.Requisitos;
import Pojo.RequisitosPromo;
import java.util.List;
import javax.faces.model.SelectItem;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author chiti
 */
public class PermisosPromoDao {

    private Session sesion;
    private Transaction tx;
    private List<RequisitosPromo> result;

    private void iniciaOperacion() {
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            tx = sesion.beginTransaction();
        } catch (HibernateException ex) {

        }

    }

    private void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }

    public String registrar(List<String> Requisitos, List<String> ReqSeleccionados, int IdPromo) throws Exception {
        boolean band = false;
        String msg = "";
        try {
            iniciaOperacion();    
            RequisitosPromo rp = new RequisitosPromo();
            Requisitos r = new Requisitos();
            Promocion p = new Promocion();
            for (int i = 0; i < Requisitos.size(); i++) {
                rp = new RequisitosPromo();
                r = new Requisitos();
                p = new Promocion();
                p.setId(IdPromo);
                r.setId(Integer.valueOf(Requisitos.get(i)));
                rp.setRequisitos(r);
                rp.setPromocion(p);
                if (ReqSeleccionados.contains(Requisitos.get(i))) {
                    rp.setEstado('1');
                } else {
                    rp.setEstado('0');
                }
                int x =getIdReqPromo(Integer.valueOf(Requisitos.get(i)), IdPromo);
                if(x>0)
                {
                    result.get(0).setEstado(rp.getEstado());
                    sesion.saveOrUpdate(result.get(0));
                }else                
                    sesion.saveOrUpdate(rp);

                
            }

            tx.commit();
            sesion.close();
            msg = "Datos procesados correctamente";
            band = true;
        } catch (Exception e) {
            tx.rollback();
            msg = e.getMessage();
            band = false;
        }

        return msg;
    }

    public int getIdReqPromo(int IdReq, int IdPromo) {
       
        int resp=0;
        boolean band = false;
        
        String hql = "from RequisitosPromo as rp where rp.promocion.id =:promo and rp.requisitos.id=:req";
        Query query = sesion.createQuery(hql);
        query.setInteger("promo", IdPromo);
        query.setInteger("req", IdReq);
        result = (List<RequisitosPromo>) query.list();
        
        if(result.isEmpty())
            resp=0;
        else   
            resp= result.get(0).getId();
        return resp;
    }

}

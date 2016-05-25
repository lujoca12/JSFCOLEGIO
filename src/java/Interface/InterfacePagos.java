/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.Pago;
import Pojo.TipoPago;
import java.util.List;

/**
 *
 * @author chiti
 */
public interface InterfacePagos {
    public List<Pago> getTodosPagos(int idMatricula) throws Exception ;
    public TipoPago getTipoPagoBanco() throws Exception ;
     public boolean registrar(Pago pago) throws Exception;
}

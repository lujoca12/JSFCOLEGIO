/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;
import Pojo.Acta;
import Pojo.TipoActa;
import java.util.List;
/**
 *
 * @author SERVER
 */
public interface InterfaceSustentacion {
    
     public List<Acta> getTodasActa() throws Exception;
     public List<TipoActa> getTipoActa() throws Exception;
    
}

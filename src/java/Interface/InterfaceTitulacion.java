/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.Titulacion;
import Pojo.Maestria;
import Pojo.TipoTitulacion;
import java.util.List;

/**
 *
 * @author SERVER
 */
public interface InterfaceTitulacion {
     public List<TipoTitulacion> getTodastipoTitulacion() throws Exception;
     public boolean registrarTitulacion(Titulacion t) throws Exception;
     public List<Titulacion> getTitulacionOK() throws Exception;
     public List<Titulacion> getTitulacionNO() throws Exception;
     public List<Maestria> obtenermaestria(int matricula) throws Exception;
}

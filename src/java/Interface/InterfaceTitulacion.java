/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.Titulacion;
import Pojo.Maestria;
import Pojo.Cargo;
import Pojo.Matricula;
import Pojo.TipoTitulacion;
import java.util.List;

/**
 *
 * @author SERVER
 */
public interface InterfaceTitulacion {
     public boolean update(Titulacion ttitulacion) throws Exception;
     public List<TipoTitulacion> getTodastipoTitulacion() throws Exception;
     public List<TipoTitulacion> gettipoTitulacionid(int titulacion) throws Exception;
     public List<Matricula> getMatriculaid(int titulacion) throws Exception;     
     public boolean registrarTitulacion(Titulacion t) throws Exception;
     public List<Titulacion> getTitulacionOK() throws Exception;
     public List<Titulacion> getTitulacionNO() throws Exception;
     public List<Titulacion> getTitulacionEstudianteMaestria(int estudiante,int maestria) throws Exception;
     public List<Maestria> obtenermaestria(int matricula) throws Exception;
     
     public List<Cargo> getCargosxDescripcion(String CargoDescripcion) throws Exception;
     public List<Cargo> getCargosD(String descripcion)throws Exception;
     public boolean registrarCargo(Cargo t) throws Exception;
}

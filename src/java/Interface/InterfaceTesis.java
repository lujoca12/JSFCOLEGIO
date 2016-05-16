/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.Proyecto;
import Pojo.PalabrasClave;
import java.util.List;
import java.util.Date;

/**
 *
 * @author SERVER
 */
public interface InterfaceTesis {
    
    public List<Proyecto> getTodasProyecto() throws Exception;
    public List<Proyecto> getProyecto() throws Exception;
    public List<Proyecto> getProyectoPalabrasclaves(String palabra) throws Exception;
    public List<Proyecto> getProyectoxTitulo(String titulo) throws Exception;   
    public boolean  registrarProyecto(Proyecto ttesis) throws Exception;
    public List<Proyecto> getProyectoxAutor(String autor) throws Exception;    
    public List<Proyecto> getProyectoxFechaSust(Date fecha) throws Exception;
    public boolean registrarPalabrasClave(PalabrasClave tpalabrasclave) throws Exception;
   // public List<Proyecto> getProyectoxTitulo(String titulo) throws Exception;    
}

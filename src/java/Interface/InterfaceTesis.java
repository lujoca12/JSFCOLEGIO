/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.Tesis;
import Pojo.PalabrasClave;
import java.util.List;
import java.util.Date;

/**
 *
 * @author SERVER
 */
public interface InterfaceTesis {
    
    public List<Tesis> getTodasTesis() throws Exception;
    public List<Tesis> getTesis() throws Exception;
    public List<Tesis> getTesisPalabrasclaves(String palabra) throws Exception;
    public List<Tesis> getTesisxTitulo(String titulo) throws Exception;   
    public boolean  registrarTesis(Tesis ttesis) throws Exception;
    public List<Tesis> getTesisxAutor(String autor) throws Exception;    
    public List<Tesis> getTesisxFechaSust(Date fecha) throws Exception;
    public boolean registrarPalabrasClave(PalabrasClave tpalabrasclave) throws Exception;
   // public List<Tesis> getTesisxTitulo(String titulo) throws Exception;    
}

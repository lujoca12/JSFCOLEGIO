/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.Tesis;
import java.util.List;

/**
 *
 * @author SERVER
 */
public interface InterfaceTesis {
    
    public List<Tesis> getTodasTesis() throws Exception;
    public List<Tesis> getTesis() throws Exception;
    public List<Tesis> getTesisPalabrasclaves(String palabra) throws Exception;
            


    
}

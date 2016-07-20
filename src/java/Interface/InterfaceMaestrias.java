/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.Maestria;
import Pojo.Promocion;
import java.util.List;

/**
 *
 * @author server
 */
public interface InterfaceMaestrias {
    
    public boolean registrarMaestriaPromocion(Maestria tMaestria,Promocion tPromocion) throws Exception;
    public boolean registrar(Maestria tMaestria) throws Exception;
    public List<Maestria> getTodasMaestrias() throws Exception;
    public List<Maestria> getMaestriaPromocion() throws Exception;
    public List<Maestria> getMaestriasxDescripcion(String descrpcion) throws Exception;
    public Maestria getMaestrias(String idMaestria) throws Exception;
    public List<Maestria> getMaestriasD(String maestriaDescripcion, boolean mostrar) throws Exception;
    public boolean update(Maestria tMaestria) throws Exception;
    public boolean delete(Maestria tMaestria) throws Exception;
    public List<Maestria> getMaestriaEstudiante(int idEstudiante) throws Exception;
}

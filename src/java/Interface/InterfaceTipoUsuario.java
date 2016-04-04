/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.TipoUsuario;
import java.util.List;

/**
 *
 * @author server
 */
public interface InterfaceTipoUsuario {
    public boolean registrar(TipoUsuario tTipoUsuario) throws Exception;
    public List<TipoUsuario> getTodosTipoUsuarios() throws Exception;
    public TipoUsuario getTipoUsuarios(String rol) throws Exception;
    public boolean update(TipoUsuario tTipoUsuario) throws Exception;
}

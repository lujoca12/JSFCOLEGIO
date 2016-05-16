/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.DetallePermiso;
import Pojo.Permiso;
import Pojo.Usuario;
import java.util.List;

/**
 *
 * @author server
 */
public interface InterfaceMenu {
    public boolean registrar(Permiso tPermiso) throws Exception;
    public List<Permiso> getPadres() throws Exception;
    public List<Permiso> getTodosPermisos() throws Exception;
    public List<Permiso> getMenuNavxUsuarios(Usuario usuario) throws Exception;
    public Permiso getPermiso(String idUsuario) throws Exception;
    public boolean update(Permiso tPermiso) throws Exception;
    public boolean updatePadres(Permiso tPermiso) throws Exception;
    public List<DetallePermiso> getUltimoIdDetallePermiso() throws Exception;
}

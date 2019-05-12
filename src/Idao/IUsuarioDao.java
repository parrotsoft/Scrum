/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Idao;

import java.util.List;
import Modelo.Usuario;

/**
 *
 * @author miguel
 */
public interface IUsuarioDao {
    
    public boolean guardar(Usuario usuario);
    public List<Usuario> listar();
    public boolean actualizar(Usuario usuario);
    public boolean eliminar(Usuario usuario);
    public boolean login(Usuario usuario); 
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Idao;

import Modelo.Proveedor;
import java.util.List;

/**
 *
 * @author miguel
 */
public interface IProveedorDao {
    
    public boolean guardar(Proveedor proveedor);
    public List<Proveedor> listar();
    public boolean actualizar(Proveedor proveedor);
    public boolean eliminar(Proveedor proveedor);
    public Proveedor getProveedor(int idProvedor);
    
}

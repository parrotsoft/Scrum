/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Idao;

import Modelo.Producto;
import java.util.List;

/**
 *
 * @author miguel
 */
public interface IProductoDao {
  
    public boolean guardar(Producto usuario);
    public List<Producto> listar();
    public boolean actualizar(Producto usuario);
    public boolean eliminar(Producto usuario);
    public Producto getProducto(int IdProducto);
    
}

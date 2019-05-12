/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Idao;

import Modelo.DetalleVenta;
import java.util.List;

/**
 *
 * @author miguel
 */
public interface IDetalleVentaDao {
    
    public boolean guardar(DetalleVenta detalleVenta);
    public List<DetalleVenta> listar();
    public boolean actualizar(DetalleVenta detalleVenta);
    public boolean eliminar(DetalleVenta detalleVenta);   
    
}

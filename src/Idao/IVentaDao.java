/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Idao;

import Modelo.DetalleVenta;
import Modelo.Venta;
import java.util.List;

/**
 *
 * @author miguel
 */
public interface IVentaDao {
    
    public boolean guardar(Venta venta, List<DetalleVenta> detalleVenta);
    public List<Venta> listar();
    public boolean actualizar(Venta venta);
    public boolean eliminar(Venta venta);
    
}

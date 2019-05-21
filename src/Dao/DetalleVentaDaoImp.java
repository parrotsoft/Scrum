/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Idao.IDetalleVentaDao;
import Modelo.DetalleVenta;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.*;
import java.sql.SQLException;
import Connection.Conexion;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.JOptionPane;

/**
 *
 * @author miguel
 */
public class DetalleVentaDaoImp implements IDetalleVentaDao {

    @Override
    public boolean guardar(DetalleVenta detalleVenta) {
        boolean guardar = false;
        
        Statement stm = null;
        Connection con = null;
        
        try {
            con = Conexion.conectart();
            String query = "{call guardar_detalle_venta(?,?,?,?,?,?,?,?)}";
            PreparedStatement stmt = con.prepareCall(query);
            stmt.setInt(1,0);
            stmt.setInt(2, detalleVenta.getVenta());
            stmt.setString(3, detalleVenta.getProducto());
            stmt.setDouble(4, detalleVenta.getPrecio());
            stmt.setInt(5, detalleVenta.getCantidad());
            stmt.setDouble(6, detalleVenta.getTotal());
            stmt.setInt(7, detalleVenta.getProductoid());
            stmt.setBoolean(8, detalleVenta.getActivo());
            stmt.execute();
            
            guardar = true;
            con.close();
            stmt.close();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.err.println("Error: Clase ClienteDaoImpl");
            e.printStackTrace();
        } catch(ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger.getLogger(ClienteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return guardar;
    }

    @Override
    public List<DetalleVenta> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean actualizar(DetalleVenta detalleVenta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(DetalleVenta detalleVenta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

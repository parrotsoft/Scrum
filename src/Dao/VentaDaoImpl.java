/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Idao.IVentaDao;
import Modelo.Venta;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.*;
import java.sql.SQLException;
import Connection.Conexion;
import Modelo.DetalleVenta;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author miguel
 */
public class VentaDaoImpl implements IVentaDao {

    @Override
    public boolean guardar(Venta venta, List<DetalleVenta> detalleVenta) {
        boolean guardar = false;
        
        Statement stm = null;
        Connection con = null;
        
        try {
            con = Conexion.conectart();
            String query = "{call guardar_venta(?,?,?,?,?,?)}";
            PreparedStatement stmt = con.prepareCall(query);
            stmt.setInt(1,0);
            stmt.setString(2, venta.getCliente());
            stmt.setString(3, venta.getRut());
            stmt.setDouble(4, venta.getTotal());
            stmt.setString(5, venta.getFecha());
            stmt.setBoolean(6, venta.getActivo());
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                int idVenta = Integer.parseInt(rs.getString("ID"));
                DetalleVentaDaoImp detalleVentaDao = new DetalleVentaDaoImp();
                for (int i = 0; i < detalleVenta.size(); i++) {
                    detalleVenta.get(i).setVenta(idVenta);
                    detalleVentaDao.guardar(detalleVenta.get(i));
                }
            }
            
            
            guardar = true;
            con.close();
            stmt.close();
        } catch(SQLException e) {
            System.err.println("Error: Clase VentaDaoImpl");
            e.printStackTrace();
        } catch(ClassNotFoundException ex) {
            Logger.getLogger(ClienteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return guardar;
    }

    @Override
    public List<Venta> listar() {
        Statement stm = null;
        Connection con = null;
        ResultSet rs = null;
        
        List<Venta> listaVentas = new ArrayList<Venta>();
        
        try {
            con = Conexion.conectart();
            stm = (Statement) con.createStatement();
            rs = stm.executeQuery("call listar_ventas");
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setId(rs.getInt(1));
                venta.setCliente(rs.getString(2));
                venta.setRut(rs.getString(3));
                venta.setTotal(rs.getDouble(4));
                venta.setFecha(rs.getString(5));
                venta.setActivo(rs.getBoolean(6));
                listaVentas.add(venta);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.err.println("Error : Clase VentaDaoImpl");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaVentas;
    }

    @Override
    public boolean actualizar(Venta venta) {
        boolean actualizar = false;
        
        Statement stm = null;
        Connection con = null;
        
        String sql = "";
        try {
            con = Conexion.conectart();
            stm = (Statement) con.createStatement();
            stm.execute(sql);
            actualizar = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.err.println("Error: Clase VentaDaoImpl");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return actualizar;
    }

    @Override
    public boolean eliminar(Venta venta) {
        boolean eliminar = false;
        
        Statement stm = null;
        Connection con = null;
        
        String sql = "";
        try {
            con = Conexion.conectart();
            stm = (Statement) con.createStatement();
            stm.execute(sql);
            eliminar = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.err.println("Error: Clase VentaDaoImpl");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eliminar;
    }
    
}

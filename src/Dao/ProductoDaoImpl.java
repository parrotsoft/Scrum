/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Idao.IProductoDao;
import Modelo.Producto;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.*;
import java.sql.SQLException;
import Connection.Conexion;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author miguel
 */
public class ProductoDaoImpl implements IProductoDao {

    @Override
    public boolean guardar(Producto producto) {
        boolean guardar = false;
        
        Statement stm = null;
        Connection con = null;
        
        try {
            con = Conexion.conectart();
            String query = "{call guardar_producto(?,?,?,?,?,?)}";
            PreparedStatement stmt = con.prepareCall(query);
            stmt.setInt(1,0);
            stmt.setString(2, producto.getNombre());
            stmt.setDouble(3, producto.getPrecio());
            stmt.setInt(4, producto.getStock());
            stmt.setInt(5, producto.getProveedor());
            stmt.setBoolean(6, producto.getActivo());
            stmt.execute();
            
            guardar = true;
            con.close();
            stmt.close();
        } catch(SQLException e) {
            System.err.println("Error: Clase ProductoDaoImpl");
            e.printStackTrace();
        } catch(ClassNotFoundException ex) {
            Logger.getLogger(ClienteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return guardar;
    }

    @Override
    public List<Producto> listar() {
        Statement stm = null;
        Connection con = null;
        ResultSet rs = null;
        
        List<Producto> listaClientes = new ArrayList<Producto>();
        
        try {
            con = Conexion.conectart();
            stm = (Statement) con.createStatement();
            rs = stm.executeQuery("call listar_productos");
            while (rs.next()) {
                Producto cliente = new Producto();
                cliente.setId(rs.getInt(1));
                cliente.setNombre(rs.getString(2));
                cliente.setPrecio(rs.getDouble(3));
                cliente.setStock(rs.getInt(4));
                cliente.setProveedor(rs.getInt(5));
                cliente.setActivo(rs.getBoolean(6));
                listaClientes.add(cliente);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.err.println("Error : Clase ProductoDaoImpl");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaClientes;
    }

    @Override
    public boolean actualizar(Producto producto) {
        boolean actualizar = false;
        
        Statement stm = null;
        Connection con = null;
        
        try {
            con = Conexion.conectart();
            String query = "{call actualizar_producto(?,?,?,?,?,?)}";
            PreparedStatement stmt = con.prepareCall(query);
            stmt.setInt(1,producto.getId());
            stmt.setString(2, producto.getNombre());
            stmt.setDouble(3, producto.getPrecio());
            stmt.setInt(4, producto.getStock());
            stmt.setInt(5, producto.getProveedor());
            stmt.setBoolean(6, producto.getActivo());
            stmt.execute();
            
            actualizar = true;
            con.close();
            stmt.close();
        } catch(SQLException e) {
            System.err.println("Error: Clase ProductoDaoImpl");
            e.printStackTrace();
        } catch(ClassNotFoundException ex) {
            Logger.getLogger(ClienteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return actualizar;
    }

    @Override
    public boolean eliminar(Producto producto) {
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
            System.err.println("Error: Clase ProductoDaoImpl");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eliminar;
    }

    @Override
    public Producto getProducto(int IdProducto) {
        Statement stm = null;
        Connection con = null;
        ResultSet rs = null;
        
        Producto producto = new Producto();
        
        try {
            con = Conexion.conectart();
            String query = "{call ver_producto(?)}";
            PreparedStatement stmt = con.prepareCall(query);
            stmt.setInt(1, IdProducto);
            rs = stmt.executeQuery();
            if(rs.next()) {
                producto.setId(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setPrecio(rs.getDouble(3));
                producto.setStock(rs.getInt(4));
                producto.setProveedor(rs.getInt(5));
                producto.setActivo(rs.getBoolean(6));
            }
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.err.println("Error : Clase ProductoDaoImpl");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return producto;
    }
    
}

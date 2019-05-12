/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.util.List;

import Idao.IProveedorDao;
import Modelo.Proveedor;

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
public class ProveedorDaoImpl implements IProveedorDao {

    @Override
    public boolean guardar(Proveedor proveedor) {
        boolean guardar = false;
        
        Statement stm = null;
        Connection con = null;
        
        try {
            con = Conexion.conectart();
            String query = "{call guardar_proveedor(?,?,?,?,?,?,?)}";
            PreparedStatement stmt = con.prepareCall(query);
            stmt.setInt(1,0);
            stmt.setString(2, proveedor.getRut());
            stmt.setString(3, proveedor.getNombre());
            stmt.setString(4, proveedor.getDireccion());
            stmt.setString(5, proveedor.getTelefono());
            stmt.setString(6, proveedor.getWeb());
            stmt.setBoolean(7, proveedor.getActivo());
            stmt.execute();
            
            guardar = true;
            con.close();
            stmt.close();
        } catch(SQLException e) {
            System.err.println("Error: Clase ProveedorDaoImpl");
            e.printStackTrace();
        } catch(ClassNotFoundException ex) {
            Logger.getLogger(ClienteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return guardar;
    }

    @Override
    public List<Proveedor> listar() {
        Statement stm = null;
        Connection con = null;
        ResultSet rs = null;
        
        List<Proveedor> listaClientes = new ArrayList<Proveedor>();
        
        try {
            con = Conexion.conectart();
            stm = (Statement) con.createStatement();
            rs = stm.executeQuery("call listar_proveedores");
            while (rs.next()) {
                Proveedor proveedor = new Proveedor();         
                proveedor.setId(rs.getInt(1));
                proveedor.setRut(rs.getString(2));
                proveedor.setNombre(rs.getString(3));
                proveedor.setDireccion(rs.getString(4));
                proveedor.setTelefono(rs.getString(5));
                proveedor.setWeb(rs.getString(6));
                proveedor.setActivo(rs.getBoolean(7));
                listaClientes.add(proveedor);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.err.println("Error : Clase ProveedorDaoImpl");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaClientes;
    }

    @Override
    public boolean actualizar(Proveedor usuario) {
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
            System.err.println("Error: Clase ProveedorDaoImpl");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return actualizar;
    }

    @Override
    public boolean eliminar(Proveedor usuario) {
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
            System.err.println("Error: Clase ProveedorDaoImpl");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eliminar;
    }

   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Idao.IClienteDao;
import Modelo.Cliente;
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
public class ClienteDaoImpl implements IClienteDao {

    @Override
    public boolean guardar(Cliente cliente) {
        boolean guardar = false;
        
        Statement stm = null;
        Connection con = null;
        
        try {
            con = Conexion.conectart();
            String query = "{call guardar_cliente(?,?,?,?,?,?)}";
            PreparedStatement stmt = con.prepareCall(query);
            stmt.setInt(1,0);
            stmt.setString(2, cliente.getRut());
            stmt.setString(3, cliente.getNombre());
            stmt.setString(4, cliente.getDireccion());
            stmt.setString(5, cliente.getTelefono());
            stmt.setBoolean(6, cliente.getActivo());
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
    public List<Cliente> listar() {
        Statement stm = null;
        Connection con = null;
        ResultSet rs = null;
        
        List<Cliente> listaClientes = new ArrayList<Cliente>();
        
        try {
            con = Conexion.conectart();
            stm = (Statement) con.createStatement();
            rs = stm.executeQuery("call listar_clientes");
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt(1));
                cliente.setRut(rs.getString(2));
                cliente.setNombre(rs.getString(3));
                cliente.setDireccion(rs.getString(4));
                cliente.setTelefono(rs.getString(5));
                cliente.setActivo(rs.getBoolean(6));
                listaClientes.add(cliente);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.err.println("Error : Clase ClienteDaoImpl");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger.getLogger(ClienteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaClientes;
    }

    @Override
    public boolean actualizar(Cliente cliente) {
        boolean actualizar = false;
        
        Statement stm = null;
        Connection con = null;
        
        try {
            con = Conexion.conectart();
            String query = "{call actualizar_cliente(?,?,?,?,?,?)}";
            PreparedStatement stmt = con.prepareCall(query);
            stmt.setInt(1, cliente.getId());
            stmt.setString(2, cliente.getRut());
            stmt.setString(3, cliente.getNombre());
            stmt.setString(4, cliente.getDireccion());
            stmt.setString(5, cliente.getTelefono());
            stmt.setBoolean(6, cliente.getActivo());
            stmt.execute();
            
            actualizar = true;
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
        return actualizar;
    }

    @Override
    public boolean eliminar(Cliente cliente) {
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
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.err.println("Error: Clase ClienteDaoImpl");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger.getLogger(ClienteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eliminar;
    }

    @Override
    public Cliente getCliente(int clienteId) {
        Statement stm = null;
        Connection con = null;
        ResultSet rs = null;
        
        Cliente cliente = new Cliente();
        
        try {
            con = Conexion.conectart();
            String query = "{call ver_cliente(?)}";
            PreparedStatement stmt = con.prepareCall(query);
            stmt.setInt(1,clienteId);
            rs = stmt.executeQuery();
            if(rs.next()) {
                cliente.setId(rs.getInt(1));
                cliente.setRut(rs.getString(2));
                cliente.setNombre(rs.getString(3));
                cliente.setDireccion(rs.getString(4));
                cliente.setTelefono(rs.getString(5));
                cliente.setActivo(rs.getBoolean(6));
            }
            rs.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.err.println("Error : Clase ClienteDaoImpl");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger.getLogger(ClienteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cliente;
    }
    
}

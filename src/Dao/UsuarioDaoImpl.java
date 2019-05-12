/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Idao.IUsuarioDao;
import Modelo.Usuario;
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
public class UsuarioDaoImpl implements IUsuarioDao {

    @Override
    public boolean guardar(Usuario usuario) {
        boolean guardar = false;
        
        Statement stm = null;
        Connection con = null;
        
        try {
            con = Conexion.conectart();
            String query = "{call guardar_usuario(?,?,?,?,?,?)}";
            PreparedStatement stmt = con.prepareCall(query);
            stmt.setInt(1,0);
            stmt.setString(2, usuario.getUsuario());
            stmt.setString(3, usuario.getClave());
            stmt.setString(4, usuario.getNombre());
            stmt.setInt(5, usuario.getRol());
            stmt.setBoolean(6, usuario.getActivo());
            stmt.execute();
            
            guardar = true;
            con.close();
            stmt.close();
        } catch(SQLException e) {
            System.err.println("Error: Clase UsuarioDaoImpl");
            e.printStackTrace();
        } catch(ClassNotFoundException ex) {
            Logger.getLogger(ClienteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return guardar;
    }

    @Override
    public List<Usuario> listar() {
        Statement stm = null;
        Connection con = null;
        ResultSet rs = null;
        
        List<Usuario> listaClientes = new ArrayList<Usuario>();
        
        try {
            con = Conexion.conectart();
            stm = (Statement) con.createStatement();
            rs = stm.executeQuery("call listar_usuarios");
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt(1));
                usuario.setUsuario(rs.getString(2));
                usuario.setClave(rs.getString(3));
                usuario.setNombre(rs.getString(4));
                usuario.setRol(rs.getInt(5));
                listaClientes.add(usuario);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.err.println("Error : Clase UsuarioDaoImpl");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaClientes;
    }

    @Override
    public boolean actualizar(Usuario usuario) {
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
            System.err.println("Error: Clase UsuarioDaoImpl");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return actualizar;
    }

    @Override
    public boolean eliminar(Usuario usuario) {
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
            System.err.println("Error: Clase UsuarioDaoImpl");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eliminar;
    }

    @Override
    public boolean login(Usuario usuario) {
        boolean loginOk = false;
        
        Statement stm = null;
        Connection con = null;
        ResultSet rs = null;
        
        try {
            con = Conexion.conectart();
            String sql = "SELECT COUNT(*) AS i FROM usuarios WHERE  usuario = '"+usuario.getUsuario()+"' AND clave = '"+usuario.getClave()+"';";
            stm = (Statement) con.createStatement();
            rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                if(rs.getString("i").equals("1")){
                    loginOk = true;
                }
            }
            con.close();
            stm.close();
        } catch (SQLException e) {
            System.err.println("Error: Clase UsuarioDaoImpl");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return loginOk;
    }
    
}

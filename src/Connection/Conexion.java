/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author miguel
 */
public class Conexion {
    
    public static Connection conectart() throws ClassNotFoundException {
        Connection con = null;
        
        String password = "root";
        String usuario = "root";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:8889/scrum","root","root");            
            if (con != null) {
                System.out.println("Conectado");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            System.err.println("No se pudo conectar a la base de datos");
            e.printStackTrace();
        }    
        return con;
    }
    
}

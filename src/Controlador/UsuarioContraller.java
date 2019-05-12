/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dao.UsuarioDaoImpl;
import Modelo.Cliente;
import Modelo.Usuario;
import Vistas.Usuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author miguel
 */
public class UsuarioContraller implements ActionListener {
    
    Usuarios viewUsuarios = new Usuarios();
    UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();

    public UsuarioContraller(Usuarios viewUsuarios) {
        this.viewUsuarios = viewUsuarios;
        this.viewUsuarios.btnGuardar.addActionListener(this);
        
        this.setTable();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewUsuarios.btnGuardar) {
            Usuario usuario = new Usuario();
            usuario.setUsuario(this.viewUsuarios.txtUsuario.getText());
            usuario.setClave(this.viewUsuarios.txtClave.getText());
            usuario.setNombre(this.viewUsuarios.txtNombre.getText());
            usuario.setRol(Integer.parseInt(this.viewUsuarios.txtRol.getText()));
            usuario.setActivo(true);
            
            if (this.usuarioDao.guardar(usuario)) {
                JOptionPane.showMessageDialog(null, "Registro almacenado de forma correcta");
                this.viewUsuarios.txtUsuario.setText("");
                this.viewUsuarios.txtClave.setText("");
                this.viewUsuarios.txtNombre.setText("");
                this.viewUsuarios.txtRol.setText("");
                this.setTable();
            } else {
                JOptionPane.showMessageDialog(null, "Error: No se pudo guardar el usuario");
            }
        }
    }
    
    private void setTable() {
        String[] cols = {"ID","Usuario","Nombre","Rol"};
            this.viewUsuarios.tblUsuarios.setModel(new DefaultTableModel(null, cols));
            List<Usuario> listaClientes = this.usuarioDao.listar();
            DefaultTableModel model = (DefaultTableModel) this.viewUsuarios.tblUsuarios.getModel();
            
            Object[] fila = new Object[4];
            for (int i = 0; i < listaClientes.size(); i++) {
                fila[0] = listaClientes.get(i).getId();
                fila[1] = listaClientes.get(i).getUsuario();
                fila[2] = listaClientes.get(i).getNombre();
                fila[3] = listaClientes.get(i).getRol();
                model.addRow(fila);
            }
    }
    
}

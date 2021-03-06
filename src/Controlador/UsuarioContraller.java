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
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author miguel
 */
public class UsuarioContraller implements ActionListener {
    
    Usuarios viewUsuarios = new Usuarios();
    UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();
    Usuario usuario = new Usuario();
    
    public UsuarioContraller(Usuarios viewUsuarios) {
        this.viewUsuarios = viewUsuarios;
        this.viewUsuarios.btnGuardar.addActionListener(this);
        this.viewUsuarios.btnCancelar.addActionListener(this);
        this.setTable();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewUsuarios.btnGuardar) {
            if (this.viewUsuarios.txtUsuario.getText().length() == 0
                    || this.viewUsuarios.txtClave.getText().length() == 0
                    || this.viewUsuarios.txtNombre.getText().length() == 0
                    || this.viewUsuarios.txtRol.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Todos los campos son requeridos...");
            }
            this.usuario.setUsuario(this.viewUsuarios.txtUsuario.getText());
            this.usuario.setClave(this.viewUsuarios.txtClave.getText());
            this.usuario.setNombre(this.viewUsuarios.txtNombre.getText());
            this.usuario.setRol(Integer.parseInt(this.viewUsuarios.txtRol.getText()));
            this.usuario.setActivo(this.viewUsuarios.txtActivo.isSelected());
            
            if(this.usuario.getId() != 0) {
                if (this.usuarioDao.actualizar(this.usuario)) {
                    JOptionPane.showMessageDialog(null, "Registro actualizado de forma correcta");
                    this.usuario = new Usuario();
                    this.limpiarCampos();
                    this.setTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Error: No se pudo actualizar el usuario");
                }
            } else {
                if (this.usuarioDao.guardar(this.usuario)) {
                    JOptionPane.showMessageDialog(null, "Registro almacenado de forma correcta");
                    this.usuario = new Usuario();
                    this.limpiarCampos();
                    this.setTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Error: No se pudo guardar el usuario");
                }
            }
            
        }
        
        if (e.getSource() == this.viewUsuarios.btnCancelar) {
            this.usuario = new Usuario();
            this.limpiarCampos();
            
        }
    }
    
    private void setTable() {
        String[] cols = {"ID","Usuario","Nombre","Rol","Activo"};
            this.viewUsuarios.tblUsuarios.setModel(new DefaultTableModel(null, cols));
            List<Usuario> listaClientes = this.usuarioDao.listar();
            DefaultTableModel model = (DefaultTableModel) this.viewUsuarios.tblUsuarios.getModel();
            
            Object[] fila = new Object[5];
            for (int i = 0; i < listaClientes.size(); i++) {
                fila[0] = listaClientes.get(i).getId();
                fila[1] = listaClientes.get(i).getUsuario();
                fila[2] = listaClientes.get(i).getNombre();
                fila[3] = listaClientes.get(i).getRol();
                fila[4] = listaClientes.get(i).getActivo()? "Activo": "Inactivo";
                model.addRow(fila);
            }
            
            this.viewUsuarios.tblUsuarios.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent me) {
                    JTable table = (JTable) me.getSource();
                    Point p = me.getPoint();
                    int row = table.rowAtPoint(p);
                    if (me.getClickCount() == 2) {
                        int linea = table.getSelectedRow();
                        
                        int idUsuario = Integer.parseInt(table.getValueAt(linea, 0).toString());
                        usuario = usuarioDao.getUsuario(idUsuario);
                        viewUsuarios.txtUsuario.setText(usuario.getUsuario());
                        viewUsuarios.txtClave.setText(usuario.getClave());
                        viewUsuarios.txtNombre.setText(usuario.getNombre());
                        viewUsuarios.txtRol.setText(""+usuario.getRol());
                        viewUsuarios.txtActivo.setSelected(usuario.getActivo());
                                                
                    }
                }
            });
           
    }
    
    private void limpiarCampos() {
        this.viewUsuarios.txtUsuario.setText("");
        this.viewUsuarios.txtClave.setText("");
        this.viewUsuarios.txtNombre.setText("");
        this.viewUsuarios.txtRol.setText("");
        this.viewUsuarios.txtActivo.setSelected(false);
    }
    
}

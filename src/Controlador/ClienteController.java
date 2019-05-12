/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dao.ClienteDaoImpl;
import Modelo.Cliente;
import Vistas.Clientes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author miguel
 */
public class ClienteController implements ActionListener {
    
    Clientes viewCliente = new Clientes();
    ClienteDaoImpl clienteDao = new ClienteDaoImpl();

    public ClienteController(Clientes viewCliente) {
        this.viewCliente = viewCliente;
        this.viewCliente.btnGuardar.addActionListener(this);
        this.setTable();
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewCliente.btnGuardar) {
            Cliente cliente = new Cliente();
            cliente.setRut(this.viewCliente.txtRut.getText());
            cliente.setNombre(this.viewCliente.txtNombre.getText());
            cliente.setDireccion(this.viewCliente.txtDireccion.getText());
            cliente.setTelefono(this.viewCliente.txtTelefono.getText());
            cliente.setActivo(true);
            
            if (this.clienteDao.guardar(cliente)) {
                JOptionPane.showMessageDialog(null, "Registro almacenado de forma correcta");
                this.viewCliente.txtRut.setText("");
                this.viewCliente.txtNombre.setText("");
                this.viewCliente.txtDireccion.setText("");
                this.viewCliente.txtTelefono.setText("");
                this.setTable();
            } else {
                JOptionPane.showMessageDialog(null, "Error: No se pudo guardar el cliente");
            }
        }
        
    }
    
    private void setTable() {
        String[] cols = {"ID","Nombre","Direccion","Telefono"};
            this.viewCliente.tblClientes.setModel(new DefaultTableModel(null, cols));
            List<Cliente> listaClientes = this.clienteDao.listar();
            DefaultTableModel model = (DefaultTableModel) this.viewCliente.tblClientes.getModel();
            
            Object[] fila = new Object[4];
            for (int i = 0; i < listaClientes.size(); i++) {
                fila[0] = listaClientes.get(i).getId();
                fila[1] = listaClientes.get(i).getNombre();
                fila[2] = listaClientes.get(i).getDireccion();
                fila[3] = listaClientes.get(i).getTelefono();
                model.addRow(fila);
            }
    }
    
}

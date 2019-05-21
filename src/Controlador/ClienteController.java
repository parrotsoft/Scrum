/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dao.ClienteDaoImpl;
import Modelo.Cliente;
import Vistas.Clientes;
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
public class ClienteController implements ActionListener {
    
    Clientes viewCliente = new Clientes();
    ClienteDaoImpl clienteDao = new ClienteDaoImpl();
    Cliente cliente = new Cliente();

    public ClienteController(Clientes viewCliente) {
        this.viewCliente = viewCliente;
        this.viewCliente.btnGuardar.addActionListener(this);
        this.viewCliente.btnCancelar.addActionListener(this);
        this.setTable();
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewCliente.btnGuardar) {
            
            if (this.viewCliente.txtRut.getText().length() == 0 || this.viewCliente.txtNombre.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Los campos Rut y Nombre son requeridos...");
            } else {
                this.cliente.setRut(this.viewCliente.txtRut.getText());
                this.cliente.setNombre(this.viewCliente.txtNombre.getText());
                this.cliente.setDireccion(this.viewCliente.txtDireccion.getText());
                this.cliente.setTelefono(this.viewCliente.txtTelefono.getText());
                this.cliente.setActivo(this.viewCliente.txtActivo.isSelected());

                if (this.cliente.getId() != 0) {
                    if (this.clienteDao.actualizar(this.cliente)) {
                        JOptionPane.showMessageDialog(null, "Registro actualizado de forma correcta");
                        this.limpiarCampos();
                        this.cliente = new Cliente();
                        this.setTable();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: No se pudo actualizado el cliente");
                    }
                } else {
                    if (this.clienteDao.guardar(this.cliente)) {
                        JOptionPane.showMessageDialog(null, "Registro almacenado de forma correcta");
                        this.limpiarCampos();
                        this.cliente = new Cliente();
                        this.setTable();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: No se pudo guardar el cliente");
                    }
                }
            }
        }
        
        if (e.getSource() == this.viewCliente.btnCancelar) {
            this.cliente = new Cliente();
            this.limpiarCampos();
        }
        
    }
    
    private void setTable() {
        String[] cols = {"ID","Nombre","Direccion","Telefono","Activo"};
            this.viewCliente.tblClientes.setModel(new DefaultTableModel(null, cols));
            List<Cliente> listaClientes = this.clienteDao.listar();
            DefaultTableModel model = (DefaultTableModel) this.viewCliente.tblClientes.getModel();
            
            Object[] fila = new Object[5];
            for (int i = 0; i < listaClientes.size(); i++) {
                fila[0] = listaClientes.get(i).getId();
                fila[1] = listaClientes.get(i).getNombre();
                fila[2] = listaClientes.get(i).getDireccion();
                fila[3] = listaClientes.get(i).getTelefono();
                fila[4] = listaClientes.get(i).getActivo()? "Activo": "Inactivo";
                model.addRow(fila);
            }
            
            this.viewCliente.tblClientes.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent me) {
                    JTable table = (JTable) me.getSource();
                    Point p = me.getPoint();
                    int row = table.rowAtPoint(p);
                    if (me.getClickCount() == 2) {
                        int linea = table.getSelectedRow();
                        
                        int idCliente = Integer.parseInt(table.getValueAt(linea, 0).toString());
                        cliente = clienteDao.getCliente(idCliente);
                        viewCliente.txtRut.setText(cliente.getRut());
                        viewCliente.txtNombre.setText(cliente.getNombre());
                        viewCliente.txtDireccion.setText(cliente.getDireccion());
                        viewCliente.txtTelefono.setText(cliente.getTelefono());
                        viewCliente.txtActivo.setSelected(cliente.getActivo());
                                                
                    }
                }
            });
    }
    
    private void limpiarCampos() {
        this.viewCliente.txtRut.setText("");
        this.viewCliente.txtNombre.setText("");
        this.viewCliente.txtDireccion.setText("");
        this.viewCliente.txtTelefono.setText("");
        this.viewCliente.txtActivo.setSelected(false);
    }
    
}

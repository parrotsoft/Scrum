/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dao.ProveedorDaoImpl;
import Modelo.Proveedor;
import Vistas.Proveedores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author miguel
 */
public class ProveedorController implements ActionListener {

    Proveedores viewProveedor = new Proveedores();
    ProveedorDaoImpl proveedorDao = new ProveedorDaoImpl();
            
    public ProveedorController(Proveedores viewProveedor) {
        this.viewProveedor = viewProveedor;
        this.viewProveedor.btnGuardar.addActionListener(this);
        this.setTable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewProveedor.btnGuardar) {
            Proveedor proveedor = new Proveedor();
            proveedor.setRut(this.viewProveedor.txtRut.getText());
            proveedor.setNombre(this.viewProveedor.txtNombre.getText());
            proveedor.setDireccion(this.viewProveedor.txtDireccion.getText());
            proveedor.setTelefono(this.viewProveedor.txtTelefono.getText());
            proveedor.setWeb(this.viewProveedor.txtWeb.getText());
            proveedor.setActivo(true);
            
            if (this.proveedorDao.guardar(proveedor)) {
                JOptionPane.showMessageDialog(null, "Registro almacenado de forma correcta");
                this.viewProveedor.txtRut.setText("");
                this.viewProveedor.txtNombre.setText("");
                this.viewProveedor.txtDireccion.setText("");
                this.viewProveedor.txtTelefono.setText("");
                this.viewProveedor.txtWeb.setText("");
                
                this.setTable();
            } else {
                JOptionPane.showMessageDialog(null, "Error: No se pudo guardar el cliente");
            }
        }
    }
    
    private void setTable() {
        String[] cols = {"ID","Rut","Nombre","Direccion","Telefono","Web"};
            this.viewProveedor.tblProveedores.setModel(new DefaultTableModel(null, cols));
            List<Proveedor> listaClientes = this.proveedorDao.listar();
            DefaultTableModel model = (DefaultTableModel) this.viewProveedor.tblProveedores.getModel();
            
            Object[] fila = new Object[6];
            for (int i = 0; i < listaClientes.size(); i++) {
                fila[0] = listaClientes.get(i).getId();
                fila[1] = listaClientes.get(i).getRut();
                fila[2] = listaClientes.get(i).getNombre();
                fila[3] = listaClientes.get(i).getDireccion();
                fila[4] = listaClientes.get(i).getTelefono();
                fila[5] = listaClientes.get(i).getWeb();
                model.addRow(fila);
            }
    }
    
}

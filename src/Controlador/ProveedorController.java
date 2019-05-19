/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dao.ProveedorDaoImpl;
import Modelo.Proveedor;
import Vistas.Proveedores;
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
public class ProveedorController implements ActionListener {

    Proveedores viewProveedor = new Proveedores();
    ProveedorDaoImpl proveedorDao = new ProveedorDaoImpl();
    Proveedor proveedor = new Proveedor();
            
    public ProveedorController(Proveedores viewProveedor) {
        this.viewProveedor = viewProveedor;
        this.viewProveedor.btnGuardar.addActionListener(this);
        this.viewProveedor.btnCancelar.addActionListener(this);
        this.setTable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewProveedor.btnGuardar) {
            
            this.proveedor.setRut(this.viewProveedor.txtRut.getText());
            this.proveedor.setNombre(this.viewProveedor.txtNombre.getText());
            this.proveedor.setDireccion(this.viewProveedor.txtDireccion.getText());
            this.proveedor.setTelefono(this.viewProveedor.txtTelefono.getText());
            this.proveedor.setWeb(this.viewProveedor.txtWeb.getText());
            this.proveedor.setActivo(this.viewProveedor.txtActivo.isSelected());
            
            if(this.proveedor.getId() != 0) {
             if (this.proveedorDao.actualizar(proveedor)) {
                JOptionPane.showMessageDialog(null, "Registro actualizado de forma correcta");
                this.limpiarCampos();
                this.proveedor = new Proveedor();
                this.setTable();
            } else {
                JOptionPane.showMessageDialog(null, "Error: No se pudo actualizar el cliente");
            }   
            } else {
                if (this.proveedorDao.guardar(proveedor)) {
                JOptionPane.showMessageDialog(null, "Registro almacenado de forma correcta");
                this.limpiarCampos();
                this.proveedor = new Proveedor();
                this.setTable();
            } else {
                JOptionPane.showMessageDialog(null, "Error: No se pudo guardar el cliente");
            }
            }
            
        }
        
        if (e.getSource() == this.viewProveedor.btnCancelar) {
            this.limpiarCampos();
            this.proveedor = new Proveedor();
        }
        
    }
    
    private void setTable() {
        String[] cols = {"ID","Rut","Nombre","Direccion","Telefono","Web","Activo"};
            this.viewProveedor.tblProveedores.setModel(new DefaultTableModel(null, cols));
            List<Proveedor> listaClientes = this.proveedorDao.listar();
            DefaultTableModel model = (DefaultTableModel) this.viewProveedor.tblProveedores.getModel();
            
            Object[] fila = new Object[7];
            for (int i = 0; i < listaClientes.size(); i++) {
                fila[0] = listaClientes.get(i).getId();
                fila[1] = listaClientes.get(i).getRut();
                fila[2] = listaClientes.get(i).getNombre();
                fila[3] = listaClientes.get(i).getDireccion();
                fila[4] = listaClientes.get(i).getTelefono();
                fila[5] = listaClientes.get(i).getWeb();
                fila[6] = listaClientes.get(i).getActivo()? "Activo": "Inactivo";
                model.addRow(fila);
            }
            
            this.viewProveedor.tblProveedores.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent me) {
                    JTable table = (JTable) me.getSource();
                    Point p = me.getPoint();
                    int row = table.rowAtPoint(p);
                    if (me.getClickCount() == 2) {
                        int linea = table.getSelectedRow();
                        
                        int idProveedor = Integer.parseInt(table.getValueAt(linea, 0).toString());
                        proveedor = proveedorDao.getProveedor(idProveedor);
                        viewProveedor.txtRut.setText(proveedor.getRut());
                        viewProveedor.txtNombre.setText(proveedor.getNombre());
                        viewProveedor.txtDireccion.setText(proveedor.getDireccion());
                        viewProveedor.txtTelefono.setText(proveedor.getTelefono());
                        viewProveedor.txtWeb.setText(proveedor.getWeb());
                        viewProveedor.txtActivo.setSelected(proveedor.getActivo());
                                                
                    }
                }
            });
    }
    
    private void limpiarCampos() {
        this.viewProveedor.txtRut.setText("");
        this.viewProveedor.txtNombre.setText("");
        this.viewProveedor.txtDireccion.setText("");
        this.viewProveedor.txtTelefono.setText("");
        this.viewProveedor.txtWeb.setText("");
        this.viewProveedor.txtActivo.setSelected(false);
    }
    
}

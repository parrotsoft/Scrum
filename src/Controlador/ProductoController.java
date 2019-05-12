/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dao.ProductoDaoImpl;
import Dao.ProveedorDaoImpl;
import Vistas.Productos;
import Modelo.Producto;
import Modelo.Proveedor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author miguel
 */
public class ProductoController implements ActionListener {

    Productos viewProducto = new Productos();
    ProductoDaoImpl productoDao = new ProductoDaoImpl();
    ProveedorDaoImpl proveedoresDao = new ProveedorDaoImpl();
    
    public ProductoController(Productos viewProducto) {
        this.viewProducto = viewProducto;
        this.viewProducto.btnGuardar.addActionListener(this);
        this.setTable();
        this.setComboBoxProveedores();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewProducto.btnGuardar) {
            Producto producto = new Producto();
            producto.setNombre(this.viewProducto.txtNombre.getText());
            producto.setPrecio(Double.parseDouble(this.viewProducto.txtPrecio.getText()));
            producto.setStock(Integer.parseInt(this.viewProducto.txtStock.getText()));
            String idProveedor = this.viewProducto.txtProveedor.getSelectedItem().toString().split("|")[0];
            producto.setProveedor(Integer.parseInt(idProveedor));
            producto.setActivo(true);
            
            if (this.productoDao.guardar(producto)) {
                JOptionPane.showMessageDialog(null, "Registro almacenado de forma correcta");
                this.viewProducto.txtNombre.setText("");
                this.viewProducto.txtPrecio.setText("");
                this.viewProducto.txtStock.setText("");
                //this.viewProducto.txtProveedor.setText("");
                this.setTable();
            } else {
                JOptionPane.showMessageDialog(null, "Error: No se pudo guardar el Producto");
            }
        }
    }
    
        private void setTable() {
        String[] cols = {"ID","Nombre","Precio","Stock","Proveedor"};
            this.viewProducto.tblProductos.setModel(new DefaultTableModel(null, cols));
            List<Producto> listaClientes = this.productoDao.listar();
            DefaultTableModel model = (DefaultTableModel) this.viewProducto.tblProductos.getModel();
            
            Object[] fila = new Object[5];
            for (int i = 0; i < listaClientes.size(); i++) {
                fila[0] = listaClientes.get(i).getId();
                fila[1] = listaClientes.get(i).getNombre();
                fila[2] = listaClientes.get(i).getPrecio();
                fila[3] = listaClientes.get(i).getStock();
                fila[4] = listaClientes.get(i).getProveedor();
                model.addRow(fila);
            }
    }
        
        private void setComboBoxProveedores() {
            List<Proveedor> proveedores = proveedoresDao.listar();
            for (int i = 0; i < proveedores.size(); i++) {
                this.viewProducto.txtProveedor.addItem(proveedores.get(i).getId() + "|" + proveedores.get(i).getNombre());
            }
        }
    
}

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
public class ProductoController implements ActionListener {

    Productos viewProducto = new Productos();
    ProductoDaoImpl productoDao = new ProductoDaoImpl();
    ProveedorDaoImpl proveedoresDao = new ProveedorDaoImpl();
    Producto producto = new Producto();
    
    public ProductoController(Productos viewProducto) {
        this.viewProducto = viewProducto;
        this.viewProducto.btnGuardar.addActionListener(this);
        this.viewProducto.btnCancelar.addActionListener(this);
        this.setTable();
        this.setComboBoxProveedores();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewProducto.btnGuardar) {
            
            if (this.viewProducto.txtNombre.getText().length() == 0 || 
                    this.viewProducto.txtPrecio.getText().length() == 0 || 
                    this.viewProducto.txtStock.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Todos los campos son requeridos.");
            } else {
                
                if (!isNumeric(this.viewProducto.txtPrecio.getText())) {
                    JOptionPane.showMessageDialog(null, "El precio debe ser un numero!");
                } else if(!isNumeric(this.viewProducto.txtStock.getText())) {
                    JOptionPane.showMessageDialog(null, "El Stock debe ser un numero!");
                } else {
                    this.producto.setNombre(this.viewProducto.txtNombre.getText());
                    this.producto.setPrecio(Double.parseDouble(this.viewProducto.txtPrecio.getText()));
                    this.producto.setStock(Integer.parseInt(this.viewProducto.txtStock.getText()));
                    String idProveedor = this.viewProducto.txtProveedor.getSelectedItem().toString().split("|")[0];
                    this.producto.setProveedor(Integer.parseInt(idProveedor));
                    this.producto.setActivo(this.viewProducto.txtActivo.isSelected());

                    if(this.producto.getId() != 0) {
                        if (this.productoDao.actualizar(producto)) {
                            JOptionPane.showMessageDialog(null, "Registro actualizado de forma correcta");
                            this.limpiarCampos();
                            this.setTable();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error: No se pudo actualizado el Producto");
                        }
                    } else {
                        if (this.productoDao.guardar(producto)) {
                            JOptionPane.showMessageDialog(null, "Registro almacenado de forma correcta");
                            this.limpiarCampos();
                            this.setTable();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error: No se pudo guardar el Producto");
                        }
                    }
                }
            }
        }
        
        if (e.getSource() == this.viewProducto.btnCancelar) {
            
        }
    }
    
        private void setTable() {
        String[] cols = {"ID","Nombre","Precio","Stock","Proveedor","Activo"};
            this.viewProducto.tblProductos.setModel(new DefaultTableModel(null, cols));
            List<Producto> listaClientes = this.productoDao.listar();
            DefaultTableModel model = (DefaultTableModel) this.viewProducto.tblProductos.getModel();
            
            Object[] fila = new Object[6];
            for (int i = 0; i < listaClientes.size(); i++) {
                fila[0] = listaClientes.get(i).getId();
                fila[1] = listaClientes.get(i).getNombre();
                fila[2] = listaClientes.get(i).getPrecio();
                fila[3] = listaClientes.get(i).getStock();
                fila[4] = listaClientes.get(i).getProveedor();
                fila[5] = listaClientes.get(i).getActivo()? "Activo": "Inactivo";
                model.addRow(fila);
            }
            
            this.viewProducto.tblProductos.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent me) {
                    JTable table = (JTable) me.getSource();
                    Point p = me.getPoint();
                    int row = table.rowAtPoint(p);
                    if (me.getClickCount() == 2) {
                        int linea = table.getSelectedRow();
                        
                        int idProducto = Integer.parseInt(table.getValueAt(linea, 0).toString());
                        producto = productoDao.getProducto(idProducto);
                        viewProducto.txtNombre.setText(producto.getNombre());
                        viewProducto.txtPrecio.setText(""+producto.getPrecio());
                        viewProducto.txtStock.setText(""+producto.getStock());
                        viewProducto.txtActivo.setSelected(producto.getActivo());                        
                    }
                }
            });
    }
        
        private void setComboBoxProveedores() {
            List<Proveedor> proveedores = proveedoresDao.listar();
            for (int i = 0; i < proveedores.size(); i++) {
                this.viewProducto.txtProveedor.addItem(proveedores.get(i).getId() + "|" + proveedores.get(i).getNombre());
            }
        }
    
        private void limpiarCampos() {
            this.viewProducto.txtNombre.setText("");
            this.viewProducto.txtPrecio.setText("");
            this.viewProducto.txtStock.setText("");
            this.viewProducto.txtActivo.setSelected(false);
        }
        
        private static boolean isNumeric(String cadena){
            try {
                    Integer.parseInt(cadena);
                    return true;
            } catch (NumberFormatException nfe){
                    return false;
            }
        }
}

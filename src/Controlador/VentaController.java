/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dao.ClienteDaoImpl;
import Dao.ProductoDaoImpl;
import Dao.VentaDaoImpl;
import Modelo.Cliente;
import Modelo.DetalleVenta;
import Modelo.Producto;
import Modelo.Venta;
import Vistas.Ventas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author miguel
 */
public class VentaController implements ActionListener {

    Ventas viewVentas = new Ventas();
    ClienteDaoImpl clienteDao = new ClienteDaoImpl();
    ProductoDaoImpl productoDao = new ProductoDaoImpl();
    VentaDaoImpl ventaDao = new VentaDaoImpl();
    List<DetalleVenta> carrito;
    double totalVenta;
    double descuento;

    public VentaController(Ventas viewVentas) {
        this.carrito = new ArrayList<DetalleVenta>();
        this.totalVenta = 0;
        this.descuento = 0;
        this.viewVentas = viewVentas;
        this.setComboBoxClientes();
        this.setComboBoxProductos();
        this.viewVentas.btnAgregar.addActionListener(this);
        this.viewVentas.btnRegistrar.addActionListener(this);
        this.viewVentas.txtTotal.setText("0");
        this.viewVentas.txtDescuento.setText("0");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewVentas.btnAgregar) {
            if (this.viewVentas.txtCanditad.getText().length() == 0){
                JOptionPane.showMessageDialog(null, "Digite la cantidad de producto...");
            }else if(!isNumeric(this.viewVentas.txtCanditad.getText())) {
                JOptionPane.showMessageDialog(null, "La cantidad debe ser un numero.");
            } else {
                DetalleVenta producto = new DetalleVenta();

                String[] parts = this.viewVentas.txtProducto.getSelectedItem().toString().split("-");
                producto.setId(Integer.parseInt(parts[0]));
                producto.setProducto(parts[1]);
                producto.setProductoid(Integer.parseInt(parts[0]));
                producto.setPrecio(Double.parseDouble(parts[3]));
                producto.setCantidad(Integer.parseInt(this.viewVentas.txtCanditad.getText())); 
                producto.setTotal(producto.getPrecio()*producto.getCantidad());
                producto.setActivo(true);
                this.totalVenta += producto.getTotal();
                this.descuento = Double.parseDouble(this.viewVentas.txtDescuento.getText());
                this.viewVentas.txtTotalFinal.setText(""+(this.totalVenta - this.descuento));
                this.carrito.add(producto);
                this.addProductoTable();
                this.viewVentas.txtCanditad.setText("");
                this.viewVentas.txtTotal.setText(""+this.totalVenta);
            }
        }
        
        if (e.getSource() == this.viewVentas.btnRegistrar) {
            if (!isNumeric(this.viewVentas.txtDescuento.getText())) {
                JOptionPane.showMessageDialog(null, "El descuento debe ser un numero...");
            } else {
                Venta venta = new Venta();
                this.descuento = Double.parseDouble(this.viewVentas.txtDescuento.getText());
                this.viewVentas.txtTotalFinal.setText(""+(this.totalVenta - this.descuento));

                String[] resp = this.viewVentas.txtCliente.getSelectedItem().toString().split("-");
                venta.setCliente(resp[1]);
                venta.setRut(resp[3]);
                venta.setTotal(this.totalVenta);
                venta.setFecha(new Date().toString());
                venta.setDescuento(this.descuento);
                venta.setActivo(true);

                if (this.ventaDao.guardar(venta, this.carrito)) {
                    JOptionPane.showMessageDialog(null, "Registro almacenado de forma correcta");
                    this.viewVentas.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Error: No se pudo guardar la venta");
                }
            }
        }
    }

    private void setComboBoxClientes() {
        List<Cliente> clientes = this.clienteDao.listar();
        for (int i = 0; i < clientes.size(); i++) {
            String activo = clientes.get(i).getActivo()? "Activo": "Inactivo";
            this.viewVentas.txtCliente.addItem(clientes.get(i).getId() + "-" + clientes.get(i).getNombre() + "-" + activo + "-" + clientes.get(i).getRut());
        }
    }

    private void setComboBoxProductos() {
        List<Producto> productos = this.productoDao.listar();
        for (int i = 0; i < productos.size(); i++) {
            String activo = productos.get(i).getActivo()? "Activo": "Inactivo";
            this.viewVentas.txtProducto.addItem(productos.get(i).getId() + "-" + productos.get(i).getNombre() + "-" + activo + "-" + productos.get(i).getPrecio());
        }
    }

    private void addProductoTable() {
        String[] cols = {"ID", "Nombre", "Precio", "Cantidad","Total"};
        this.viewVentas.tblProductos.setModel(new DefaultTableModel(null, cols));

        DefaultTableModel model = (DefaultTableModel) this.viewVentas.tblProductos.getModel();

        Object[] fila = new Object[5];
        System.err.println(this.carrito.size());
        for (int i = 0; i < this.carrito.size(); i++) {
            fila[0] = this.carrito.get(i).getId();
            fila[1] = this.carrito.get(i).getProducto();
            fila[2] = this.carrito.get(i).getPrecio();
            fila[3] = this.carrito.get(i).getCantidad();
            fila[4] = this.carrito.get(i).getTotal();
            model.addRow(fila);
        }
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

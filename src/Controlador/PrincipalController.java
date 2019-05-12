/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vistas.Clientes;
import Vistas.Principal;
import Vistas.Productos;
import Vistas.Proveedores;
import Vistas.Usuarios;
import Vistas.Ventas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author miguel
 */
public class PrincipalController implements ActionListener {

    Principal viewPrincipal = new Principal();
    
    public PrincipalController(Principal viewPrincipal) {
        this.viewPrincipal = viewPrincipal;
        this.viewPrincipal.itemClientes.addActionListener(this);
        this.viewPrincipal.itemProveedores.addActionListener(this);
        this.viewPrincipal.itemProductos.addActionListener(this);
        this.viewPrincipal.itemVentas.addActionListener(this);
        this.viewPrincipal.itemSalir.addActionListener(this);
        this.viewPrincipal.itemUsuarios.addActionListener(this);
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewPrincipal.itemClientes) {
            Clientes viewCliente = new Clientes();
            ClienteController clienteContoller = new ClienteController(viewCliente);
            this.viewPrincipal.Desktop.add(viewCliente);
            viewCliente.toFront();
            viewCliente.setVisible(true);
        }
        
        if (e.getSource() == this.viewPrincipal.itemProveedores) {
            Proveedores viewProveedores = new Proveedores();
            ProveedorController proveedorController = new ProveedorController(viewProveedores);
            this.viewPrincipal.Desktop.add(viewProveedores);
            viewProveedores.toFront();
            viewProveedores.setVisible(true);   
        }
        
        if (e.getSource() == this.viewPrincipal.itemProductos) {
            Productos viewProducto = new Productos();
            ProductoController productoController = new ProductoController(viewProducto);
            this.viewPrincipal.Desktop.add(viewProducto);
            viewProducto.toFront();
            viewProducto.setVisible(true);   
        }
        
        if (e.getSource() == this.viewPrincipal.itemVentas) {
            Ventas viewVentas = new Ventas();
            VentaController ventaController = new VentaController(viewVentas);
            this.viewPrincipal.Desktop.add(viewVentas);
            viewVentas.toFront();
            viewVentas.setVisible(true);
        }
        
        if (e.getSource() == this.viewPrincipal.itemUsuarios) {
            Usuarios viewUsuarios = new Usuarios();
            UsuarioContraller usuarioController = new UsuarioContraller(viewUsuarios);
            this.viewPrincipal.Desktop.add(viewUsuarios);
            viewUsuarios.toFront();
            viewUsuarios.setVisible(true);
        }
        
        if (e.getSource() == this.viewPrincipal.itemSalir) {
            System.exit(0);
        }
    }
    
}

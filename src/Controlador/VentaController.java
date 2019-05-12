/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vistas.Ventas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author miguel
 */
public class VentaController implements ActionListener {

    Ventas viewVentas = new Ventas();
    
    public VentaController(Ventas viewVentas) {
        this.viewVentas = viewVentas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

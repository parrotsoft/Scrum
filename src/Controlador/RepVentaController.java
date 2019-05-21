/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dao.VentaDaoImpl;
import Modelo.Venta;
import Vistas.RepVentas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author miguel
 */
public class RepVentaController implements ActionListener {
    
    RepVentas viewRepVentas = new RepVentas();
    VentaDaoImpl ventaDao = new VentaDaoImpl();
    
    public RepVentaController(RepVentas viewRepVentas) {
        this.viewRepVentas = viewRepVentas;
        this.setTable();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
    private void setTable() {
        String[] cols = {"ID","Cliente","Rut","Total","Descuento","Gran Total","Fecha"};
            this.viewRepVentas.tblVentas.setModel(new DefaultTableModel(null, cols));
            List<Venta> listaVentas = this.ventaDao.listar();
            DefaultTableModel model = (DefaultTableModel) this.viewRepVentas.tblVentas.getModel();
            
            Object[] fila = new Object[7];
            for (int i = 0; i < listaVentas.size(); i++) {
                fila[0] = listaVentas.get(i).getId();
                fila[1] = listaVentas.get(i).getCliente();
                fila[2] = listaVentas.get(i).getRut();
                fila[3] = listaVentas.get(i).getTotal();
                fila[4] = listaVentas.get(i).getDescuento();
                fila[5] = listaVentas.get(i).getTotal() - listaVentas.get(i).getDescuento();
                fila[6] = listaVentas.get(i).getFecha();
                model.addRow(fila);
            }
    }
}

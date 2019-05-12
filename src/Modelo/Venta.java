/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author miguel
 */
public class Venta {
    private int id;
    private String cliente;
    private String rut;
    private double total;
    private String fecha;
    private boolean activo;

    public Venta() {
    }
    
    public Venta(int id, String cliente, double total, String fecha, boolean activo, String rut) {
        this.id = id;
        this.cliente = cliente;
        this.total = total;
        this.fecha = fecha;
        this.activo = activo;
        this.rut = rut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }
    
    public boolean getActivo() {
        return this.activo;
    }
    
}

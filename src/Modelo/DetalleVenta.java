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
public class DetalleVenta {
    
    private int id;
    private int venta;
    private String producto;
    private double precio;
    private int cantidad;
    private double total;
    private int productoid;
    private boolean activo;

    public DetalleVenta() {
    }

    public DetalleVenta(int id, int venta, String producto, double precio, int cantidad, double total, int productoid, boolean activo) {
        this.id = id;
        this.venta = venta;
        this.producto = producto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.total = total;
        this.productoid = productoid;
        this.activo = activo;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVenta() {
        return venta;
    }

    public void setVenta(int venta) {
        this.venta = venta;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getProductoid() {
        return productoid;
    }

    public void setProductoid(int productoid) {
        this.productoid = productoid;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public boolean getActivo() {
        return this.activo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
}

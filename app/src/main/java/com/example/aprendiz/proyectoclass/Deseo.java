package com.example.aprendiz.proyectoclass;

/**
 * Created by Andrea y Oscar EDAM.
 */

public class Deseo {


   private int idProducto;
    private String Nombre;
    private String Precio;
    private int PrecioTotal;
    private int cantidad1;
    private String fecha;

    public Deseo(int idProducto, String nombre, String precio, int precioTotal, int cantidad1, String fecha) {
        this.idProducto = idProducto;
        Nombre = nombre;
        Precio = precio;
        PrecioTotal = precioTotal;
        this.cantidad1 = cantidad1;
        this.fecha = fecha;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    public int getPrecioTotal() {
        return PrecioTotal;
    }

    public void setPrecioTotal(int precioTotal) {
        PrecioTotal = precioTotal;
    }

    public int getCantidad1() {
        return cantidad1;
    }

    public void setCantidad1(int cantidad1) {
        this.cantidad1 = cantidad1;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}


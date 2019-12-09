package com.example.aprendiz.proyectoclass;

import android.content.Intent;

import java.util.Date;
import java.util.List;

/**
 * Created by Andrea y Oscar EDAM.
 */

public class Factura {

    private List<Carrito> listaCarrito;
    private Integer idPersona;
    private Integer idUsuario;
    private String fecha;
    private Integer totalFactura;
    private Integer idCiudad;



    public Factura(List<Carrito> listaCarrito, Integer idPersona, Integer idUsuario, String fecha, Integer totalFactura, Integer idCiudad) {
        this.listaCarrito = listaCarrito;
        this.idPersona = idPersona;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.totalFactura=totalFactura;
        this.idCiudad = idCiudad;

    }

    public List<Carrito> getListaCarrito() {
        return listaCarrito;
    }

    public void setListaCarrito(List<Carrito> listaCarrito) {
        this.listaCarrito = listaCarrito;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(Integer totalFactura) {
        this.totalFactura = totalFactura;
    }

    public Integer getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Integer idCiudad) {
        this.idCiudad = idCiudad;
    }
}

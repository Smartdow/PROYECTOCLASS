package com.example.aprendiz.proyectoclass;

/**
 * Created by Andrea y Oscar EDAM.
 */

public class MisFacturas {


    private Integer idFactura;
    private Integer ClienteIdUsuario;
    private Integer usuarioIdUsuario;
    private String fechaFactura;
    private Integer totalFactura;
    private Integer idCiudad;

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Integer getClienteIdUsuario() {
        return ClienteIdUsuario;
    }

    public void setClienteIdUsuario(Integer clienteIdUsuario) {
        ClienteIdUsuario = clienteIdUsuario;
    }

    public Integer getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(Integer usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    public String getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
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

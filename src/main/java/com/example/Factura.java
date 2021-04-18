package com.example;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.PrimaryKey;

public class Factura {

    @PrimaryKey
    private long id;
    @ForeignKey
    private String empleado_dni;
    @ForeignKey
    private String cliente_dni;
    @ForeignKey
    private List<Long> id_producto;
    private float precio;
    private Date fcha_factura;

    public Factura(long id, String empleado_dni, String cliente_dni, List<Long> id_producto, float precio,
            Date fcha_factura) {
        this.id = id;
        this.empleado_dni = empleado_dni;
        this.cliente_dni = cliente_dni;
        this.id_producto = id_producto;
        this.precio = precio;
        this.fcha_factura = fcha_factura;
    }

    public Factura() {

    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmpleado_dni() {
        return this.empleado_dni;
    }

    public void setEmpleado_dni(String empleado_dni) {
        this.empleado_dni = empleado_dni;
    }

    public String getCliente_dni() {
        return this.cliente_dni;
    }

    public void setCliente_dni(String cliente_dni) {
        this.cliente_dni = cliente_dni;
    }

    public List<Long> getId_producto() {
        return this.id_producto;
    }

    public void setId_producto(List<Long> id_producto) {
        this.id_producto = id_producto;
    }

    public float getPrecio() {
        return this.precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Date getFcha_factura() {
        return this.fcha_factura;
    }

    public void setFcha_factura(Date fcha_factura) {
        this.fcha_factura = fcha_factura;
    }

}
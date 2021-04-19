package com.example.data;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Factura {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT, primaryKey = "true")
    private long id;
    @ForeignKey
    private Empleado empleado;
    @ForeignKey
    private Cliente cliente;
    @ForeignKey
    private List<Producto> productos;
    private Double precio;
    private Date fcha_factura;

    public Factura(long id, Empleado empleado, Cliente cliente, List<Producto> productos, Double precio,
            Date fcha_factura) {
        this.id = id;
        this.empleado = empleado;
        this.cliente = cliente;
        this.productos = productos;
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

    public Empleado getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getProductos() {
        return this.productos;
    }

    public void setProducto(List<Producto> productos) {
        this.productos = productos;
    }

    public Double getPrecio() {
        return this.precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Date getFcha_factura() {
        return this.fcha_factura;
    }

    public void setFcha_factura(Date fcha_factura) {
        this.fcha_factura = fcha_factura;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", empleado='" + getEmpleado() + "'" + ", cliente='" + getCliente() + "'"
                + ", productos='" + getProductos() + "'" + ", precio='" + getPrecio() + "'" + ", fcha_factura='"
                + getFcha_factura() + "'" + "}";
    }

}
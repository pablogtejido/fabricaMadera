package com.atenea.data;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.ForeignKeyAction;
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
    @ForeignKey(deleteAction = ForeignKeyAction.NONE)
    private Empleado empleado;
    @ForeignKey(deleteAction = ForeignKeyAction.NONE)
    private Cliente cliente;
    @ForeignKey(deleteAction = ForeignKeyAction.NONE)
    private List<Producto> productos;
    private Double precio;
    private Date fcha_factura;

    public Factura(Empleado empleado, Cliente cliente, List<Producto> productos, Date fcha_factura) {
        this.empleado = empleado;
        this.cliente = cliente;
        this.productos = productos;
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

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    // Calcular el precio del listado de productos en vez de meterlo a mano
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
        return "Factura {" + " id= " + id + ", empleado= " + empleado + ", cliente= " + cliente + ", productos= "
                + productos + ", precio= " + precio + ", fcha_factura= " + fcha_factura + "}";
    }

    public String toStringProductos() {
        String nombreprod = "";

        for (Producto producto : productos) {
            nombreprod = nombreprod + ", " + producto.getNombre();

        }
        return nombreprod;
    }

    public Double calcularPrecio() {
        Double precio_calculado = 0.0;
        for (Producto producto : this.productos) {
            precio_calculado += producto.getPrecio();
        }

        return precio_calculado;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Factura)) {
            return false;
        }
        Factura factura = (Factura) o;
        return id == factura.id && Objects.equals(empleado, factura.empleado)
                && Objects.equals(cliente, factura.cliente) && Objects.equals(productos, factura.productos)
                && Objects.equals(precio, factura.precio) && Objects.equals(fcha_factura, factura.fcha_factura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, empleado, cliente, productos, precio, fcha_factura);
    }

}
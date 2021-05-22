package com.atenea.data;

import java.util.Objects;
import java.util.Set;

import javax.jdo.annotations.IdGeneratorStrategy;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Cada producto tendrá asociado un id, un nombre, un precio
 * un peso, una medida, un grosor, una opcion de tener añadidos y una lista de facturas 
 * 
 */

@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Producto {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT, primaryKey = "true")
	private Long producto_id;
	private String nombre;
	private double precio;
	private double peso;
	private double medida;
	private double grosor;
	private boolean anyadidos;
	@Persistent(mappedBy = "productos")
	Set<Factura> facturas;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getMedida() {
		return medida;
	}

	public void setMedida(double medida) {
		this.medida = medida;
	}

	public double getGrosor() {
		return grosor;
	}

	public void setGrosor(double grosor) {
		this.grosor = grosor;
	}

	public boolean isAnyadidos() {
		return anyadidos;
	}

	public void setAnyadidos(boolean anyadidos) {
		this.anyadidos = anyadidos;
	}

	public Long getId() {
		return this.producto_id;
	}

	public void setId(Long producto_id) {
		this.producto_id = producto_id;
	}

	public Producto() {
		super();
	}

	@Override
	public String toString() {
		return "Producto [producto_id=" + producto_id + ", nombre=" + nombre + ", precio=" + precio + ", peso=" + peso
				+ ", medida=" + medida + ", grosor=" + grosor + ", anyadidos=" + anyadidos + "]";
	}

	public Producto(String nombre, double precio, double peso, double medida, double grosor, boolean anyadidos) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.peso = peso;
		this.medida = medida;
		this.grosor = grosor;
		this.anyadidos = anyadidos;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Producto)) {
			return false;
		}
		Producto producto = (Producto) o;
		return Objects.equals(this.producto_id, producto.producto_id) && Objects.equals(nombre, producto.nombre)
				&& precio == producto.precio && peso == producto.peso && medida == producto.medida
				&& grosor == producto.grosor && anyadidos == producto.anyadidos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(producto_id, nombre, precio, peso, medida, grosor, anyadidos);
	}

}

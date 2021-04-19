package com.example.data;

import javax.jdo.annotations.IdGeneratorStrategy;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Producto {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT, primaryKey = "true")
	private Long id;
	private double precio;
	private double peso;
	private int cantidad;
	private double medida;
	private double grosor;
	private boolean anyadidos;

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

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Producto() {
		super();
	}

	public Producto(Long id, double precio, double peso, int cantidad, double medida, double grosor,
			boolean anyadidos) {
		super();
		this.id = id;
		this.precio = precio;
		this.peso = peso;
		this.cantidad = cantidad;
		this.medida = medida;
		this.grosor = grosor;
		this.anyadidos = anyadidos;
	}

	@Override
	public String toString() {
		return "{" + " id='" + getId() + "'" + ", precio='" + getPrecio() + "'" + ", peso='" + getPeso() + "'"
				+ ", cantidad='" + getCantidad() + "'" + ", medida='" + getMedida() + "'" + ", grosor='" + getGrosor()
				+ "'" + ", anyadidos='" + isAnyadidos() + "'" + "}";
	}

}

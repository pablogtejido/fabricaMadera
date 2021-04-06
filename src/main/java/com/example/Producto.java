package com.example;

import java.util.Date;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Producto {

	@PrimaryKey
	private int id;
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

	public int getId() {
		return this.id;
	}
	
	
	public Producto() {
		super();
	}

	public Producto(int id, double precio, double peso, int cantidad, double medida, double grosor, boolean anyadidos) {
		super();
		this.id = id;
		this.precio = precio;
		this.peso = peso;
		this.cantidad = cantidad;
		this.medida = medida;
		this.grosor = grosor;
		this.anyadidos = anyadidos;
	}
	
	

}

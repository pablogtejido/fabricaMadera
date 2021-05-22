package com.atenea.data;

import java.util.Objects;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Cliente {
	@PrimaryKey
	private String dni;
	private String nombre;
	private String apellidos;
	private String contrasena;

	public Cliente() {
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", contrasena=" + contrasena
				+ "]";
	}

	public Cliente(String dni, String nombre, String apellidos, String contrasena) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.contrasena = contrasena;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Cliente)) {
			return false;
		}
		Cliente cliente = (Cliente) o;
		return Objects.equals(dni, cliente.dni) && Objects.equals(nombre, cliente.nombre)
				&& Objects.equals(apellidos, cliente.apellidos) && Objects.equals(contrasena, cliente.contrasena);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni, nombre, apellidos, contrasena);
	}

}

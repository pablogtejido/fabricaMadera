package com.atenea.data;

import java.util.Objects;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Administrador {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT, primaryKey = "true")
	private int id;
	private String contrasena;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Administrador() {
		super();
	}

	public Administrador(String contrasena, String nombre, String apellido, String email, String telefono) {
		super();
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Administrador [id=" + id + ", contrasena=" + contrasena + ", nombre=" + nombre + ", apellido="
				+ apellido + ", email=" + email + ", telefono=" + telefono + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Administrador)) {
			return false;
		}
		Administrador administrador = (Administrador) o;
		return id == administrador.id && Objects.equals(contrasena, administrador.contrasena)
				&& Objects.equals(nombre, administrador.nombre) && Objects.equals(apellido, administrador.apellido)
				&& Objects.equals(email, administrador.email) && Objects.equals(telefono, administrador.telefono);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, contrasena, nombre, apellido, email, telefono);
	}

}

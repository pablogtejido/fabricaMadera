/**
 * @package objects
 */
package com.atenea.data;

import java.util.Objects;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

/**
 * El administrador es quien se va a encargar de hacer las modificaciones
 * necesarias del empleado; es decir, podrá modificar sus datos
 */

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Administrador {

	@PrimaryKey
	private String dni;
	private String contrasena;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;

	/**
	 * Devuelve el string dni
	 * @return String con el dni del cliente
	 */
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Devuelve el string contrasena
	 * @return String con el contrasena del cliente
	 */
	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * Devuelve el string nombre
	 * @return String con el nombre del cliente
	 */
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve el string apellido
	 * @return String con el apellido del cliente
	 */
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Devuelve el string email
	 * @return String con el email del cliente
	 */
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Devuelve el string telefono
	 * @return String con el telefono del cliente
	 */
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Constructor vacio de la clase Cliente
	 */
	public Administrador() {
		super();
	}

	
	/**
	 * Constructor de la clase Administrador
	 * @param dni STRING para el dni del administrador
	 * @param contrasena STRING para la contrasena del administrador
	 * @param nombre STRING para el nombre del administrador
	 * @param apellido STRING para el apellido del administrador
	 * @param email STRING para el email del administrador
	 * @param telefono STRING para el telefono del administrador
	 */
	public Administrador(String dni, String contrasena, String nombre, String apellido, String email, String telefono) {
		super();
		this.dni = dni;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
	}
	

	@Override
	public String toString() {
		return "Administrador [dni=" + dni + ", contrasena=" + contrasena + ", nombre=" + nombre + ", apellido="
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
		return Objects.equals(dni, administrador.dni) && Objects.equals(contrasena, administrador.contrasena)
				&& Objects.equals(nombre, administrador.nombre) && Objects.equals(apellido, administrador.apellido)
				&& Objects.equals(email, administrador.email) && Objects.equals(telefono, administrador.telefono);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni, contrasena, nombre, apellido, email, telefono);
	}

}

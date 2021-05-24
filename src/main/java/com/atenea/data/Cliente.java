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
 * Cada cliente será identificado por su dni. Además, se almacenan su nombre,
 * apellidos y contraseña
 * 
 */

@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Cliente {
	@PrimaryKey
	private String dni;
	private String nombre;
	private String apellidos;
	private String contrasena;

	/**
	 * Constructor vacio de la clase Cliente
	 */
	public Cliente() {
	}

	/**
	 * Devuelve el string dni
	 * @return String con el dni del cliente
	 */
	public String getDni() {
		return dni;
	}
	
	/**
	 * Permite cambiar el dni del cliente
	 * @param dni String con el nuevo dni de cliente
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Devuelve el string nombre
	 * @return String con el nombre del cliente
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Permite cambiar el nombre del cliente
	 * @param nombre String con el nuevo nombre de cliente
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve el string apellidos
	 * @return String con los apellidos del cliente
	 */
	public String getApellidos() {
		return apellidos;
	}
	
	/**
	 * Permite cambiar los apellidos del cliente
	 * @param apellidos String con los nuevos apellidos de cliente
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Devuelve el string contraseña
	 * @return String con la contraseña del cliente
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * Permite cambiar la contrasena del cliente
	 * @param contrasena String con la nueva contrasena de cliente
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", contrasena=" + contrasena
				+ "]";
	}

	
	/**
	 * Constructor de la clase Cliente
	 * @param dni STRING para el dni del cliente
	 * @param nombre STRING para el nombre del cliente
	 * @param apellido STRING para el apellido del cliente
	 * @param contrasena STRING para el contrasena del cliente
	 */
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

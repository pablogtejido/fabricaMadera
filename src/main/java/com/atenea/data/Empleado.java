package com.atenea.data;

import java.util.Date;
import java.util.Objects;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

/**
 * Cada empleado podrá visualizar y añadir clientes, productos y facturas. Sin
 * embargo, no podrá registrar empleados (eso solo podrá hacerlo el
 * administrador)
 */

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Empleado {

	@PrimaryKey
	private String dni;
	private String nombre;
	private String direccion;
	private String email;
	private String telefono;
	private EnumPuestoEmpleados puesto;
	private Date fcha_nacimiento;
	private Date fcha_empleado;
	private double sueldo;
	private String contrasena;

	public Empleado() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param dni String para el dni del empleado
	 * @param nombre String para el nombre del empleado
	 * @param direccion String para el direccion del empleado
	 * @param email String para el email del empleado
	 * @param telefono String para el telefono del empleado
	 * @param puesto EnumPuestoEmpleados para el puesto del empleado
	 * @param fcha_nacimiento Date para el fcha_nacimiento del empleado
	 * @param fcha_empleado Date para el fcha_empleado del empleado
	 * @param sueldo Double para el sueldo del empleado
	 * @param contrasena String para el contrasena del empleado
	 */
	public Empleado(String dni, String nombre, String direccion, String email, String telefono,
			EnumPuestoEmpleados puesto, Date fcha_nacimiento, Date fcha_empleado, double sueldo, String contrasena) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.direccion = direccion;
		this.email = email;
		this.telefono = telefono;
		this.puesto = puesto;
		this.fcha_nacimiento = fcha_nacimiento;
		this.fcha_empleado = fcha_empleado;
		this.sueldo = sueldo;
		this.contrasena = contrasena;
	}

	/**
	 * Devuelve el string dni
	 * @return String con el dni del empleado
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Permite cambiar el dni del empleado
	 * @param dni String con el nuevo dni de empleado
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Devuelve el string nombre
	 * @return String con el nombre del empleado
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Permite cambiar el nombre del cliente
	 * @param nombre String con el nuevo nombre de empleado
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve el string direccion
	 * @return String con la direccion del empleado
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Permite cambiar la direccion del empleado
	 * @param direccion String con la nueva direccion de empleado
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Devuelve el string email 
	 * @return String con el email del empleado
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Permite cambiar el email del empleado
	 * @param email String con el nuevo email de empleado
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Devuelve el string telefono
	 * @return String con el telefono del empleado
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Permite cambiar el telefono del empleado
	 * @param telefono String con el nuevo telefono de empleado
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Devuelve el EnumPuestoEmpleados puesto
	 * @return EnumPuestoEmpleados con el puesto del empleado
	 */
	public EnumPuestoEmpleados getPuesto() {
		return puesto;
	}
	
	/**
	 * Permite cambiar el puesto del empleado
	 * @param puesto EnumPuestoEmpleados con el puesto del empleado
	*/
	public void setPuesto(EnumPuestoEmpleados puesto) {
		this.puesto = puesto;
	}

	/**
	 * Devuelve la Date fcha_nacimiento
	 * @return Date con la fcha_nacimiento del empleado
	 */
	public Date getFcha_nacimiento() {
		return fcha_nacimiento;
	}

	/**
	 * Permite cambiar el fcha_nacimiento del empleado
	 * @param fcha_nacimiento Date con el fcha_nacimiento del empleado
	*/
	public void setFcha_nacimiento(Date fcha_nacimiento) {
		this.fcha_nacimiento = fcha_nacimiento;
	}

	/**
	 * Devuelve la Date fcha_empleado
	 * @return Date con la fcha_empleado del empleado
	 */
	public Date getFcha_empleado() {
		return fcha_empleado;
	}

	/**
	 * Permite cambiar el fcha_empleado del empleado
	 * @param fcha_empleado Date con el fcha_empleado del empleado
	*/
	public void setFcha_empleado(Date fcha_empleado) {
		this.fcha_empleado = fcha_empleado;
	}

	/**
	 * Devuelve el string sueldo
	 * @return String con el sueldo del empleado
	 */
	public double getSueldo() {
		return sueldo;
	}

	/**
	 * Permite cambiar el sueldo del empleado
	 * @param sueldo Double con el sueldo del empleado
	*/
	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	/**
	 * Devuelve el string contrasena
	 * @return String con la contrasena del empleado
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * Permite cambiar la contrasena del empleado
	 * @param contrasena String con la contrasena del empleado
	*/
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Override
	public String toString() {
		return "Empleado [dni=" + dni + ", nombre=" + nombre + ", direccion=" + direccion + ", email=" + email
				+ ", telefono=" + telefono + ", puesto=" + puesto + ", fcha_nacimiento=" + fcha_nacimiento
				+ ", fcha_empleado=" + fcha_empleado + ", sueldo=" + sueldo + ", contrasena=" + contrasena + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Empleado)) {
			return false;
		}
		Empleado empleado = (Empleado) o;
		return Objects.equals(dni, empleado.dni) && Objects.equals(nombre, empleado.nombre)
				&& Objects.equals(direccion, empleado.direccion) && Objects.equals(email, empleado.email)
				&& Objects.equals(telefono, empleado.telefono) && Objects.equals(puesto, empleado.puesto)
				&& Objects.equals(fcha_nacimiento, empleado.fcha_nacimiento)
				&& Objects.equals(fcha_empleado, empleado.fcha_empleado) && sueldo == empleado.sueldo
				&& Objects.equals(contrasena, empleado.contrasena);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni, nombre, direccion, email, telefono, puesto, fcha_nacimiento, fcha_empleado, sueldo,
				contrasena);
	}

}

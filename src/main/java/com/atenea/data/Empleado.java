package com.atenea.data;

import java.util.Date;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

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

	public Empleado(String dni, String nombre, String direccion, String email, String telefono,
			 String contrasena) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.direccion = direccion;
		this.email = email;
		this.telefono = telefono;
		/*
		this.fcha_nacimiento = fcha_nacimiento;
		this.fcha_empleado = fcha_empleado;*/
		this.contrasena = contrasena;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

	public EnumPuestoEmpleados getPuesto() {
		return puesto;
	}

	public void setPuesto(EnumPuestoEmpleados puesto) {
		this.puesto = puesto;
	}

	public Date getFcha_nacimiento() {
		return fcha_nacimiento;
	}

	public void setFcha_nacimiento(Date fcha_nacimiento) {
		this.fcha_nacimiento = fcha_nacimiento;
	}

	public Date getFcha_empleado() {
		return fcha_empleado;
	}

	public void setFcha_empleado(Date fcha_empleado) {
		this.fcha_empleado = fcha_empleado;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Override
	public String toString() {
		return "Empleado [dni=" + dni + ", nombre=" + nombre + ", direccion=" + direccion + ", email=" + email
				+ ", telefono=" + telefono + ", puesto=" + puesto + ", fcha_nacimiento=" + fcha_nacimiento
				+ ", fcha_empleado=" + fcha_empleado + ", sueldo=" + sueldo + ", contrasena=" + contrasena + "]";
	}

	

}

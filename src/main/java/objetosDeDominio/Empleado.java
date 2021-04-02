package objetosDeDominio;

import java.util.Date;

public class Empleado {
	
	private String dni;
	private String nombre;
	private String direccion;
	private String email;
	private String telefono;
	private String puesto;
	private Date fcha_nacimiento;
	private Date fcha_empleado;
	private double sueldo;
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
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
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
	
	
	

}

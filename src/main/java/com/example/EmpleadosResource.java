package com.example;

import java.util.Date;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.example.db.DBManager;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("empleados")
public class EmpleadosResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Empleado> getEmpleados() {
		
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		
		Query<Empleado> q = pm.newQuery(Empleado.class);
		q.setOrdering("nombre desc");
		
		List<Empleado> empleados = q.executeList();

		pm.close();
		
		return empleados;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public int diasEnEmpresa(Empleado empleado) {
		Date fechaActual = new Date();
		Date inicio = empleado.getFcha_empleado();
		
		int dias = (int) ((fechaActual.getTime() - inicio.getTime()) / 86400000);
		
		return dias;
		
	}
	public void eliminarEmpleado() {
		List<Empleado> empleados = DBManager.getInstance().getEmpleados();
		
		for (Empleado empleado : empleados) {
			DBManager.getInstance().delete(empleado);
		}	
		
	}
	public void modificarEmpleado() {
		
	}
	/*
	 * getEmpleados()
	 * eliminarEmpleado()
	 * crearEmpleado()
	 * modificarEmpleado()
	 * 
	 */
}

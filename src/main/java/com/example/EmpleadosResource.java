package com.example;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

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
	
	/*
	 * getEmpleados()
	 * eliminarEmpleado()
	 * crearEmpleado()
	 * modificarEmpleado()
	 * 
	 */
}

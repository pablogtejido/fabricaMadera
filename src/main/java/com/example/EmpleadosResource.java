package com.example;

import java.util.Date;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.example.db.DBManager;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.PUT;
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

	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String modificarEmpleado(Empleado empleado) {
		Empleado TMP = DBManager.getInstance().getEmpleado(empleado.getDni());
		DBManager.getInstance().delete(TMP);
		DBManager.getInstance().store(empleado);
		return "Done";
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String crearProducto(Empleado empleado) {
		DBManager.getInstance().store(empleado);
		return "Done";
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String eliminarProducto(Empleado empleado) {
		DBManager.getInstance().delete(empleado);
		return "Done";
	}
}

package com.atenea.resource;

import java.util.List;
import com.atenea.data.Empleado;
import com.atenea.db.DBManager;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("empleado")
public class EmpleadosResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Empleado> getEmpleados() {
		List<Empleado> empleados = DBManager.getInstance().getEmpleados();
		return empleados;
	}

	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String modificarEmpleado(Empleado empleado) {
		DBManager.getInstance().updateEmpleado(empleado);
		return "Done";
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String crearEmpleado(Empleado empleado) {
		DBManager.getInstance().store(empleado);
		return "Done";
	}

	@DELETE
	@Path("/ids/{empleadoId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String eliminarEmpleado(@PathParam("empleadoId") String id) {
		DBManager.getInstance().deleteEmpleadoById(id);
		return "Done";
	}
}

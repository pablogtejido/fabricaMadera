package com.atenea.resource;

import java.util.List;
import com.atenea.data.Administrador;
import com.atenea.db.DBException;
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

@Path("administrador")
public class AdministradorResource {

	/**
	 * Obtener administradores de la base de datos
	 * 
	 * @return lista de administradores
	 * 
	 */

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Administrador> getAdministrador() {
		List<Administrador> administradores = DBManager.getInstance().getAdministrador();
		return administradores;
	}

	/**
	 * Modificar un administrador de la base de datos
	 * 
	 * @param administrador
	 * 
	 * @return administrador
	 * 
	 */

	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Administrador modificarAdministrador(Administrador admin) {
		DBManager.getInstance().updateAdministrador(admin);
		return admin;
	}

	/**
	 * Almacenar un administrador de la base de datos
	 * 
	 * @param administrador
	 * 
	 * @return administrador
	 * 
	 */

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Administrador crearAdministrador(Administrador admin) {
		DBManager.getInstance().store(admin);
		return admin;
	}

	/**
	 * Eliminar un administrador de la base de datos
	 * 
	 * @param dni
	 * 
	 * @return "Done"
	 * 
	 */

	@DELETE
	@Path("/ids/{administradorId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String eliminarAdministrador(@PathParam("administradorId") String id) {
		DBManager.getInstance().deleteAdministradorById(id);
		return "Done";
	}

}

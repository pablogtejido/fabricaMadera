package com.atenea.resource;

import java.util.List;
import com.atenea.data.Administrador;
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

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Administrador> getAdministrador() {
		List<Administrador> administradores = DBManager.getInstance().getAdministrador();
		return administradores;
	}

	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Administrador modificarAdministrador(Administrador admin) {
		DBManager.getInstance().updateAdministrador(admin);
		return admin;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Administrador crearAdministrador(Administrador admin) {
		DBManager.getInstance().store(admin);
		return admin;
	}

	@DELETE
	@Path("/ids/{administradorId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String eliminarAdministrador(@PathParam("administradorId") int id) {
		DBManager.getInstance().deleteAdministradorById(id);
		return "Done";
	}

}

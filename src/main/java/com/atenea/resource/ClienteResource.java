package com.atenea.resource;

import java.util.List;

import com.atenea.data.Cliente;
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

@Path("cliente")
public class ClienteResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> getClientes() {
		List<Cliente> clientes = DBManager.getInstance().getClientes();
		return clientes;
	}

	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente modificarCliente(Cliente cliente) {
		DBManager.getInstance().updateCliente(cliente);
		return cliente;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente crearCliente(Cliente cliente) {
		DBManager.getInstance().store(cliente);
		return cliente;
	}

	@DELETE
	@Path("/ids/{clienteDni}")
	@Produces(MediaType.TEXT_PLAIN)
	public String eliminarCliente(@PathParam("clienteDni") String dni) {
		DBManager.getInstance().deleteClienteByDNI(dni);
		return "Done deleteCliente";
	}
}

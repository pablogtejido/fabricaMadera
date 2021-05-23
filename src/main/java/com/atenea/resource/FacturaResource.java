package com.atenea.resource;

import java.util.List;

import com.atenea.data.Factura;
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

@Path("factura")
public class FacturaResource {

	/**
	 * Obtener facturas de la base de datos
	 * 
	 * @return lista de facturas
	 * 
	 */

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Factura> getFactura() {
		return DBManager.getInstance().getFacturas();
	}

	/**
	 * Modificar una factura de la base de datos
	 * 
	 * @param factura
	 * 
	 * @return factura
	 * 
	 **/

	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Factura modificarFactura(Factura factura) {
		DBManager.getInstance().updateFactura(factura);
		return factura;
	}

	/**
	 * Almacenar una factura de la base de datos
	 * 
	 * @param factura
	 * 
	 * @return factura
	 * 
	 */

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Factura crearFactura(Factura factura) {
		DBManager.getInstance().store(factura);
		return factura;
	}

	/**
	 * Eliminar una factura de la base de datos
	 * 
	 * @param id
	 * 
	 * @return "Done"
	 * 
	 */

	@DELETE
	@Path("/ids/{facturaId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String eliminarFactura(@PathParam("facturaId") long id) {
		DBManager.getInstance().deleteFacturaById(id);
		return "Done";
	}

}

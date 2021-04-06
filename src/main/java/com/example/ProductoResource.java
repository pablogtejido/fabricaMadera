package com.example;

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

@Path("/producto")
public class ProductoResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Producto> getProductos() {

		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();

		Query<Producto> q = pm.newQuery(Producto.class);
		q.setOrdering("peso asc");

		List<Producto> productos = q.executeList();

		pm.close();

		return productos;
	}

	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String modificarProducto(Producto producto) {
		Producto TMP = DBManager.getInstance().getProducto(producto.getId());
		DBManager.getInstance().delete(TMP);
		DBManager.getInstance().store(producto);
		return "Done";
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String crearProducto(Producto producto) {
		DBManager.getInstance().store(producto);
		return "Done";
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String eliminarProducto(Producto producto) {
		DBManager.getInstance().delete(producto);
		return "Done";
	}
}